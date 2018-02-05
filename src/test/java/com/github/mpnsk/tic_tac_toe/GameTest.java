package com.github.mpnsk.tic_tac_toe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GameTest {
    private String player1 = "Adam", player2 = "Bob";

    @Test
    public void currentPlayer() {
        Game game = new Game(player1, player2);
        assertEquals(player1, game.currentPlayer());
        game.markField(1,1);
        assertEquals(player2, game.currentPlayer());
    }

    @Test
    public void markField() {
        Game game = new Game(player1, player2);
        game.markField(1,1);
    }

    @Test
    public void game_started_and_not_won() {
        Game game = new Game(player1, player2);
        assertFalse(game.isWon());
    }

    @Test
    public void game_played_and_won() {
        Game game = new Game(player1, player2);
        assertFalse(game.isWon());
        game.markField(1,1);
        assertFalse(game.isWon());
        game.markField(2,1);
        assertFalse(game.isWon());
        game.markField(1,2);
        assertFalse(game.isWon());
        game.markField(2,2);
        assertFalse(game.isWon());
        game.markField(1,3);
//        TODO check when game is actually won
//        assertTrue(game.isWon());
    }
}