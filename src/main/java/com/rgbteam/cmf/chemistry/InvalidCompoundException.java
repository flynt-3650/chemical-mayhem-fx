package com.rgbteam.cmf.chemistry;

public class InvalidCompoundException extends RuntimeException {
    public InvalidCompoundException() {
    }

    public InvalidCompoundException(String message) {
        super(message);
    }

    public InvalidCompoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCompoundException(Throwable cause) {
        super(cause);
    }
}
