package com.shitikov.task6.model.exception;

public class BookDAOException extends Exception{
    public BookDAOException() {
        super();
    }

    public BookDAOException(String message) {
        super(message);
    }

    public BookDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDAOException(Throwable cause) {
        super(cause);
    }
}
