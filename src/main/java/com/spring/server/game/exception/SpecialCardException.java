package com.spring.server.game.exception;

import com.spring.server.game.entity.BusinessCardEntity;
import lombok.Getter;

public class SpecialCardException extends InterruptedException {

    @Getter
    private final BusinessCardEntity cardEntity;

    public SpecialCardException(BusinessCardEntity cardEntity) {
        super("SpecialCard interrupted the process: " + cardEntity.toString());
        this.cardEntity = cardEntity;
    }
}
