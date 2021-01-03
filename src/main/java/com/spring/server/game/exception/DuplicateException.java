package com.spring.server.game.exception;

public class DuplicateException extends IllegalArgumentException {
    public DuplicateException(String identifier) {
        super("'" + identifier + "' already exists.");
    }
}
