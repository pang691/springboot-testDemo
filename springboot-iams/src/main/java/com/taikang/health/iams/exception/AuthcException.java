package com.taikang.health.iams.exception;

public class AuthcException extends RuntimeException {
    private static final long serialVersionUID = -795542791928644578L;
    private int status;

    public AuthcException() {
        this("{authenticate fail!}");
    }

    public AuthcException(String message) {
        this(message, 401);
    }

    public AuthcException(String message, int status) {
        super(message);
        this.status = 401;
        this.status = status;
    }

    public AuthcException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = 401;
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}