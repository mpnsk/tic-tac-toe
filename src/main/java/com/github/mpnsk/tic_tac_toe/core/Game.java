package com.github.mpnsk.tic_tac_toe.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    @Getter
    private String currentPlayer;
    private Iterator<String> players;
    @Getter
    private final List<String> playerPool;
    @Getter
    private int[][] board;
    @Getter
    private GameState gameState;

    public enum GameState {RUNNING, WON, DRAW}

    public Game(List<String> players) {
        playerPool = new ArrayList<>(players);
        this.players = playerPool.iterator();
        board = new int[3][3];
        gameState = GameState.RUNNING;
        nextPlayer();
    }

    public void markTile(int x, int y) throws IllegalStateException, IllegalArgumentException {
        testMoveForRange(x, y);
        testMoveForAlreadyTaken(board[x][y]);
        setTile(x, y);
        if (isWinningMove()) announceWinner();
        else nextPlayer();
    }

    private boolean isWinningMove() {
        int p = playerPool.indexOf(currentPlayer) + 1;
        for (int x = 0; x < 2; x++)
            if (board[x][0] == p && board[x][1] == p && board[x][2] == p)
                return true;
        for (int y = 0; y < 2; y++)
            if (board[0][y] == p && board[1][y] == p && board[2][y] == p)
                return true;
        if (board[0][0] == p && board[1][1] == p && board[2][2] == p)
            return true;
        if (board[2][0] == p && board[1][1] == p && board[0][2] == p)
            return true;
        return false;
    }

    private void testMoveForAlreadyTaken(int playerNumber) {
        if (playerNumber != 0)
            throw new IllegalStateException("Impossible move, tile already taken! Or badly initialized?");
    }

    private void testMoveForRange(int x, int y) {
        int lower = 0, upper = 2;
        if (x < lower || x > upper)
            throw new IllegalArgumentException("Argument x needs to be between " + lower + " and " + upper);
        if (y < lower || y > upper)
            throw new IllegalArgumentException("Argument y needs to be between " + lower + " and " + upper);
    }

    private void setTile(int x, int y) {
        int currentPlayerNumber = playerPool.indexOf(currentPlayer) + 1;
        board[x][y] = currentPlayerNumber;
    }

    private void nextPlayer() {
        if (!players.hasNext()) players = playerPool.iterator();
        currentPlayer = players.next();
    }

    private void announceWinner() {
        gameState = GameState.WON;
        System.out.println("The winner is " + currentPlayer);
    }
}
