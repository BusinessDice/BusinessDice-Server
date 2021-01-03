package com.spring.server.game;

import com.spring.server.game.entity.BusinessCardEntity;
import com.spring.server.game.entity.GamePhase;
import com.spring.server.game.entity.ProjectCardEntity;
import com.spring.server.game.exception.FullGameException;
import com.spring.server.game.exception.GameOverException;
import com.spring.server.game.exception.IncorrectPasswordException;
import com.spring.server.game.exception.TurnNotPossibleException;

import java.util.Map;

import static com.spring.server.game.entity.GamePhase.*;

public class Game {

    private final String name;
    private final String password;

    private final Player[] players;
    private final Board board;

    private int pointer;
    private GamePhase phase;

    public Game(String name, String password, int playerCount) {
        this.name = name;
        this.password = password;
        this.players = new Player[playerCount];
        this.board = new Board(playerCount);
        this.pointer = 0;
        this.phase = GamePhase.VORBEREITUNG;
    }

    public void join(String playerName) throws IncorrectPasswordException, FullGameException {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = new Player(playerName);
                if (i == players.length - 1) {
                    phase = GamePhase.WUERFELN;
                }
                return;
            } else if (players[i].getName().equals(playerName)) {
                return;
            }
        }
        throw new FullGameException(this.name, this.players.length);
    }

    public void executeRoll(String playerName, boolean rollTwoDices) throws TurnNotPossibleException {
        checkPossibleTurn(playerName, WUERFELN);
        Player player = players[pointer];
        if (rollTwoDices && !player.hasProject(ProjectCardEntity.BAHNHOF)) {
            throw new TurnNotPossibleException("Player (" + players[pointer].getName() + ") can not roll with two dices.");
        }
        Dice dice = new Dice(rollTwoDices);
        if (player.hasProject(ProjectCardEntity.FUNKTURM)) {
            phase = GamePhase.GEWUERFELT;
        } else {
            phase = GamePhase.VERARBEITUNG;
            submitRoll(playerName, dice);
        }
    }

    public void submitRoll(String playerName, Dice dice) throws TurnNotPossibleException {
        checkPossibleTurn(playerName, WUERFELN);
        Player player = players[pointer];
        if (player.hasProject(ProjectCardEntity.FREIZEITPARK) && dice.isDouble()) {
            phase = GamePhase.WUERFELN;
        } else {

        }
    }

    public void executeBuy(String playerName, BusinessCardEntity card) throws TurnNotPossibleException, GameOverException {
        checkPossibleTurn(playerName, KAUFEN);

        try {
            checkIfGameIsOver();
        } catch (GameOverException e) {
            phase = BEENDET;
            throw e;
        }
        pointer = pointer == players.length ? 0 : pointer++;
    }

    /*
    Validation methods
     */

    private void checkIfGameIsOver() throws GameOverException {
        for (boolean ownedProject :
                players[pointer].getAllProjects().values()) {
            if (!ownedProject) {
                return;
            }
        }
        throw new GameOverException(players[pointer]);
    }

    private void checkPossibleTurn(String playerName, GamePhase phase) {
        if (!playerName.equals(this.players[pointer].getName())) {
            throw new TurnNotPossibleException("Only '" + players[pointer].getName() + "' can do a turn.");
        }
        if (!this.phase.equals(phase)) {
            throw new TurnNotPossibleException("Wrong game phase. Found: '" + phase + "'. Expected: '" + this.phase + "'.");
        }
    }

    public boolean checkPassword(String password) throws IncorrectPasswordException {
        if (this.password.equals(password)) {
            return true;
        } else {
            throw new IncorrectPasswordException(password);
        }
    }

    /*
    Getter methods to create the game state
     */

    public String getName() {
        return this.name;
    }

    public GamePhase getGamePhase() {
        return this.phase;
    }

    public String getActivePlayer() {
        return this.players[pointer].getName();
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Map<BusinessCardEntity, Integer> getBoard() {
        return this.board.getBoard();
    }
}
