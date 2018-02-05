package com.github.mpnsk.tic_tac_toe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {
    String player1 = "Adam", player2 = "Bob";

    @Test
    public void currentPlayer() {
        Game game = new Game(player1, player2);
        assertEquals(player1, game.currentPlayer());
        game.markField(1,1);
        assertEquals(player2, game.currentPlayer());
    }

    @Test
    public void markField() {
    }

    @Test
    public void isWon() {
        Game game = new Game("Adam", "Bob");
        assertEquals(false, game.isWon());
    }
}