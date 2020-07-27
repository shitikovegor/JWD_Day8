package com.shitikov.task8.model.pool;

import com.shitikov.task8.model.exception.PoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {
    private static final int POOL_SIZE = 32;
    private static Logger logger = Logger.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool instance;
    private static boolean isInstanceCreated;
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;

    ConnectionPool() throws PoolException {
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("property.database");
        Properties properties = convertResourceBundleToProperties(resourceBundle);

        try {
            Class.forName(resourceBundle.getString("driver"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Driver not found. ", e);
        }
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.offer(new ProxyConnection(DriverManager.getConnection(
                        resourceBundle.getString("url"), properties)));
            }
        } catch (SQLException e) {
            throw new PoolException("Connection creating error", e);
        }
    }

    public static ConnectionPool getInstance() throws PoolException {
        if (!isInstanceCreated) {
            synchronized (ConnectionPool.class) {
                if (!isInstanceCreated) {
                    instance = new ConnectionPool();
                    isInstanceCreated = true;
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer((ProxyConnection) connection);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Error during getting connection. ", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws PoolException {
        if (connection.getClass().getSimpleName().equals("ProxyConnection")) {
            givenAwayConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            throw new PoolException("Closing connection is incorrect.");
        }
    }

    public void destroyPool() throws PoolException {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                throw new PoolException("Pool destroy error. ", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error during deregistration of drivers. ", e);
            }
        });
    }

    private Properties convertResourceBundleToProperties(ResourceBundle resourceBundle) {
        Properties properties = new Properties();
        Enumeration<String> keys = resourceBundle.getKeys();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            properties.put(key, resourceBundle.getString(key));
        }
        return properties;
    }
}
