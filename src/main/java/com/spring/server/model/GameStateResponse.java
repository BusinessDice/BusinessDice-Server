package com.spring.server.model;

import com.spring.server.game.Player;
import com.spring.server.game.entity.BusinessCardEntity;
import com.spring.server.game.entity.GamePhase;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@ApiModel(description = "All information about the state of a game.")
public class GameStateResponse {

    private final String gameName;
    private final GamePhase gamePhase;
    private final String activePlayerName;
    private final Player[] players;
    private final Map<BusinessCardEntity, Integer> board;
}
