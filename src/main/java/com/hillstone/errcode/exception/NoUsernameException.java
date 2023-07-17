package com.hillstone.errcode.exception;

public class NoUsernameException extends RuntimeException{

    public NoUsernameException() {
    }

    public NoUsernameException(String message) {
        super(message);
    }
}
