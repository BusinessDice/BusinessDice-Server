package com.spring.server.controller;

import com.spring.server.game.Game;
import com.spring.server.game.GameManagement;
import com.spring.server.model.CreateGameRequest;
import com.spring.server.model.GameStateResponse;
import com.spring.server.model.JoinGameRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GameController {

    private final GameManagement gameManager = new GameManagement();

    @ApiOperation("Creates a new Game.")
    @RequestMapping(value = "/game/create",
            method = RequestMethod.POST)
    public HttpStatus createGame(@RequestBody CreateGameRequest request) {
        gameManager.createGame(request.getGameName(), request.getPassword(), request.getPlayerCount(), request.getPlayerName());
        return HttpStatus.OK;
    }

    @ApiOperation("Return a Food-Object by given a name.")
    @RequestMapping(value = "/game/join", method = RequestMethod.POST)
    public HttpStatus joinGame(@RequestBody JoinGameRequest request) {
        gameManager.getGame(request.getGameName(), request.getPassword()).join(request.getPlayerName());
        return HttpStatus.OK;
    }

    @ApiOperation("Returns the information about a Project Card.")
    @RequestMapping(value = "/game/state", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameStateResponse> getGameState(
            @RequestParam(value = "gameName", defaultValue = "game123") String gameName,
            @RequestParam(value = "password", defaultValue = "password123") String password) {
        Game game = gameManager.getGame(gameName, password);
        return ResponseEntity.ok(new GameStateResponse(
                game.getName(),
                game.getGamePhase(),
                game.getActivePlayer(),
                game.getPlayers(),
                game.getBoard()));
    }
}
