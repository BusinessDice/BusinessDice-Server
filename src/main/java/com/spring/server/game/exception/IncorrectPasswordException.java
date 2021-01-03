package com.spring.server.game.exception;

public class IncorrectPasswordException extends IllegalArgumentException {
    public IncorrectPasswordException(String password) {
        super("Incorrect password: " + password);
    }
}
