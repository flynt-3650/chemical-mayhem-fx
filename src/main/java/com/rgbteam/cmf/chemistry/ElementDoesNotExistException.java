package com.rgbteam.cmf.chemistry;

public class ElementDoesNotExistException extends Exception {
    public ElementDoesNotExistException() {
    }

    public ElementDoesNotExistException(String message) {
        super(message);
    }

    public ElementDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
