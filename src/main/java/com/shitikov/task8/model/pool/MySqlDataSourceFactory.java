package com.shitikov.task8.model.pool;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.shitikov.task8.model.exception.BookDAOException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlDataSourceFactory {

    private MySqlDataSourceFactory() {
    }

    public static MysqlDataSource createMySqlDataSource() throws BookDAOException {
        MysqlDataSource dataSource = null;
        Properties props = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream("src\\prop\\database.properties");
            props.load(inputStream);
            dataSource = new MysqlDataSource();
            dataSource.setURL(props.getProperty("db.url"));
            dataSource.setUser(props.getProperty("db.user"));
            dataSource.setPassword(props.getProperty("db.password"));
            dataSource.setUseSSL(Boolean.parseBoolean(props.getProperty("db.useSSL")));
            dataSource.setServerTimezone(props.getProperty("db.serverTimezone"));
            dataSource.setCharacterEncoding(props.getProperty("db.characterEncoding"));
        } catch (IOException ioe) {
            throw new BookDAOException("File access error. " + ioe.getMessage());
        } catch (SQLException se) {
            throw new BookDAOException("Connection error. " + se.getMessage());
        }
        return dataSource;
    }
}
