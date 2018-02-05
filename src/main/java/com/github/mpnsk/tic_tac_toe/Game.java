package com.github.mpnsk.tic_tac_toe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

class Game {

    private String currentPlayer;
    private Iterator<String> players;
    private final List<String> playerPool;
    private int[][] board;

    Game(String... player) {
        playerPool = new ArrayList<>(Arrays.asList(player));
        players = playerPool.iterator();
        endTurn();
        board = new int[3][3];
    }

    String currentPlayer() {
        return currentPlayer;
    }

    void markField(int x, int y) throws IllegalStateException, IllegalArgumentException {
        testMoveForRange(x, y);
        x--;
        y--;
        testMoveForAlreadyTaken(board[x][y]);
        board[x][y] = playerPool.indexOf(currentPlayer);

        endTurn();
    }

    private void testMoveForAlreadyTaken(int playerNumber) {
        if (playerNumber != 0)
            throw new IllegalStateException("Impossible move, field already taken! Or badly initialized?");
    }

    private void testMoveForRange(int x, int y) {
        int lower = 1, upper = 3;
        Predicate<Integer> notInRange = integer -> integer < lower || integer > upper;
        if (notInRange.test(x) || notInRange.test(y))
            throw new IllegalArgumentException("Argument needs to be between " + lower + " and " + upper);
    }

    private void endTurn() {
        if (!players.hasNext()) players = playerPool.iterator();
        currentPlayer = players.next();
    }

    boolean isWon() {
        return false;
    }
}
