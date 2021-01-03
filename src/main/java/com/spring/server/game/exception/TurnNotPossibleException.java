package com.spring.server.game.exception;

public class TurnNotPossibleException extends ArithmeticException {
    public TurnNotPossibleException(String reason) {
        super("Reason: " + reason);
    }
}
