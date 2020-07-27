package com.shitikov.task8.model.exception;

public class LibraryServiceException extends Exception{
    public LibraryServiceException() {
        super();
    }

    public LibraryServiceException(String message) {
        super(message);
    }

    public LibraryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryServiceException(Throwable cause) {
        super(cause);
    }
}
