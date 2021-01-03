package com.spring.server.game;

import com.spring.server.game.entity.BusinessCardEntity;
import com.spring.server.game.entity.GamePhase;
import com.spring.server.game.entity.ProjectCardEntity;
import com.spring.server.game.exception.FullGameException;
import com.spring.server.game.exception.IncorrectPasswordException;
import com.spring.server.game.exception.TurnNotPossibleException;

import java.util.Map;

public class Game {

    private final String name;
    private final String password;

    private final Player[] players;
    private final Board board;

    private final int pointer;
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
        checkPhase(GamePhase.VERARBEITUNG);
        checkPlayer(playerName);
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
        checkPhase(GamePhase.GEWUERFELT);
        checkPlayer(playerName);
        Player player = players[pointer];
        if (player.hasProject(ProjectCardEntity.FREIZEITPARK) && dice.isDouble()) {
            phase = GamePhase.WUERFELN;
        } else {

        }
    }

    public void executeBuy(String playerName, BusinessCardEntity card) throws TurnNotPossibleException {
        checkPhase(GamePhase.KAUFEN);
        checkPlayer(playerName);
    }

    private void updatePlayers(Dice dice) {

    }

    public boolean checkPassword(String password) throws IncorrectPasswordException {
        if (this.password.equals(password)) {
            return true;
        } else {
            throw new IncorrectPasswordException(password);
        }
    }

    private void checkPhase(GamePhase phase) throws TurnNotPossibleException {
        if (!this.phase.equals(phase)) {
            throw new TurnNotPossibleException("Wrong game phase (" + this.phase + ")");
        }
    }

    private void checkPlayer(String name) throws TurnNotPossibleException {
        if (!name.equals(this.players[pointer].getName())) {
            throw new TurnNotPossibleException("Only '" + players[pointer].getName() + "' can do a turn.");
        }
    }

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
