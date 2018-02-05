package com.github.mpnsk.tic_tac_toe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Game {

    private final String player1;
    private final String player2;
    private String currentPlayer;
    private Iterator<String> players;
    private final HashSet<String> playerPool;

    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        playerPool = new HashSet<>(Arrays.asList(player1, player2));
        players = playerPool.iterator();
        endTurn();
    }

    public String currentPlayer() {
        return currentPlayer;
    }

    void markField(int x, int y) {

        endTurn();
    }

    private void endTurn() {
        if (!players.hasNext()) players = playerPool.iterator();
        currentPlayer = players.next();
    }

    boolean isWon() {
        return false;
    }
}
