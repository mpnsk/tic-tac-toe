package com.github.mpnsk.tic_tac_toe;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private String player1 = "Adam", player2 = "Bob";

    @Test
    public void currentPlayer() {
        Game game = new Game(player1, player2);
        assertEquals(player1, game.getCurrentPlayer());
        game.markTile(1, 1);
        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    public void markField() {
        Game game = new Game(player1, player2);
        game.markTile(1, 1);
    }

    @Test
    public void game_started_and_not_won() {
        Game game = new Game(player1, player2);
        assertFalse(game.isWon());
    }

    @Test
    public void test_board() {
        Game game = new Game(player1, player2);
        game.markTile(0, 0);
        int[][] firstMove = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertArrayEquals(firstMove, game.getBoard());

        game.markTile(1, 1);
        int[][] secondMove = {{1, 0, 0}, {0, 2, 0}, {0, 0, 0}};
        assertArrayEquals(secondMove, game.getBoard());
    }

    @Test
    public void game_played_and_won() {
        Game game = new Game(player1, player2);
        assertFalse(game.isWon());
        game.markTile(0, 0);
        assertFalse(game.isWon());
        game.markTile(1, 0);
        assertFalse(game.isWon());
        game.markTile(0, 1);
        assertFalse(game.isWon());
        game.markTile(1, 1);
        assertFalse(game.isWon());
        game.markTile(0, 2);
//        TODO check when game is actually won
//        assertTrue(game.isWon());
    }
}