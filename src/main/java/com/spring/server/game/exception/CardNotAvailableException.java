package com.spring.server.game.exception;

import com.spring.server.game.entity.BusinessCardEntity;
import com.spring.server.game.entity.ProjectCardEntity;

public class CardNotAvailableException extends IllegalArgumentException {
    public CardNotAvailableException(BusinessCardEntity card, String reason) {
        super("Card not available: '" + card + "' because: " + reason);
    }

    public CardNotAvailableException(ProjectCardEntity card, String reason) {
        super("Card not available: '" + card + "' because: " + reason);
    }
}
