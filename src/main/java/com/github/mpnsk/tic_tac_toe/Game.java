package com.github.mpnsk.tic_tac_toe;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

class Game {
    @Getter
    private String currentPlayer;
    private Iterator<String> players;
    @Getter
    private final List<String> playerPool;
    @Getter
    private int[][] board;

    Game(String... player) {
        playerPool = new ArrayList<>(Arrays.asList(player));
        players = playerPool.iterator();
        board = new int[3][3];
        nextTurn();
    }

    void markTile(int x, int y) throws IllegalStateException, IllegalArgumentException {
        testMoveForRange(x, y);
        testMoveForAlreadyTaken(board[x][y]);
        setTile(x, y);
        nextTurn();
    }

    private void testMoveForAlreadyTaken(int playerNumber) {
        if (playerNumber != 0)
            throw new IllegalStateException("Impossible move, tile already taken! Or badly initialized?");
    }

    private void testMoveForRange(int x, int y) {
        int lower = 0, upper = 2;
        Predicate<Integer> inRange = integer -> lower <= integer && integer <= upper;
        if (!inRange.test(x) || !inRange.test(y))
            throw new IllegalArgumentException("Move Argument needs to be between " + lower + " and " + upper);
    }

    private void setTile(int x, int y) {
        int currentPlayerNumber = playerPool.indexOf(currentPlayer) + 1;
        board[x][y] = currentPlayerNumber;
    }

    private void nextTurn() {
        if (!players.hasNext()) players = playerPool.iterator();
        currentPlayer = players.next();
    }

    boolean isWon() {
        return false;
    }
}
