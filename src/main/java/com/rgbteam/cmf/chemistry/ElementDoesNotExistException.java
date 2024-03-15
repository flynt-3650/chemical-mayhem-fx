package com.rgbteam.cmf.chemistry;

class ElementDoesNotExistException extends RuntimeException {
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

    public ElementDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
