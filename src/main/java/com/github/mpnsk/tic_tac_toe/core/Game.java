package com.github.mpnsk.tic_tac_toe.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Game {
    @Getter
    private String currentPlayer;
    private Iterator<String> players;
    @Getter
    private final List<String> playerPool;
    @Getter
    private int[][] board;
    private int tilesTaken = 0;
    @Getter
    private GameState gameState;

    public enum GameState {RUNNING, WON, DRAW}

    public Game(String... player) {
        playerPool = new ArrayList<>(Arrays.asList(player));
        players = playerPool.iterator();
        board = new int[3][3];
        gameState = GameState.RUNNING;
        nextPlayer();
    }

    public void markTile(int x, int y) throws IllegalStateException, IllegalArgumentException {
        testMoveForRange(x, y);
        testMoveForAlreadyTaken(board[x][y]);
        setTile(x, y);
        if (isWinningMove(x, y)) announceWinner();
        else nextPlayer();
    }

    private boolean isWinningMove(int x, int y) {
        return false;
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
        tilesTaken++;
    }

    private void nextPlayer() {
        if (!players.hasNext()) players = playerPool.iterator();
        currentPlayer = players.next();
    }

    private void announceWinner() {
        System.out.println("The winner is " + currentPlayer);
    }
}
