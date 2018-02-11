package com.github.mpnsk.tic_tac_toe.core;

import lombok.experimental.var;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private final String player1 = "Adam", player2 = "Bob";
    private final String[] players = {player1, player2};

    @Test
    public void currentPlayer() {
        var game = new Game(players);
        assertEquals(player1, game.getCurrentPlayer());
        game.markTile(1, 1);
        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    public void markField() {
        var game = new Game(players);
        game.markTile(1, 1);
    }

    @Test
    public void game_started_and_not_won() {
        var game = new Game(players);
        assertEquals(Game.GameState.RUNNING, game.getGameState());
    }

    @Test
    public void test_board() {
        var game = new Game(players);
        game.markTile(0, 0);
        int[][] firstState = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertArrayEquals(firstState, game.getBoard());

        game.markTile(1, 1);
        int[][] secondState = {{1, 0, 0}, {0, 2, 0}, {0, 0, 0}};
        assertArrayEquals(secondState, game.getBoard());
    }

    @Test
    public void game_played_and_won() {
        var game = new Game(players);
        int[][] moves = {{0, 0}, {1, 0}, {1, 1}, {0, 2}};

        for (var move : moves) {
            assertEquals(Game.GameState.RUNNING, game.getGameState());
            game.markTile(move[0], move[1]);
        }
//        TODO check when game is actually won
    }

    @Test
    public void is_winning_move_Test() {
        var game = new Game(players);
        int[][] moves = {
                {0, 0}, {0, 1},
                {1, 0}, {0, 2},
                {2, 0}, {1, 1}
        };

        for (var move : moves) {
            assertEquals(Game.GameState.RUNNING, game.getGameState());
            game.markTile(move[0], move[1]);
        }

    }

    @Test
    public void invalid_input_throws_exception_Test() {
        var game = new Game(players);
        int[][] inputs = {{-1, 0}, {0, -1}, {3, 0}, {0, 3}};
        for (int[] input : inputs) {
            try {
                game.markTile(input[0], input[1]);
                fail("expected exception to be thrown!");
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    @Test
    public void invalid_move_throws_exception_Test() {
        var game = new Game(players);
        try {
            game.markTile(1, 2);
            game.markTile(1, 2);
            fail("moved to same tile twice, expected exception to be thrown!");
        } catch (IllegalStateException ignored) {
        }
    }
}