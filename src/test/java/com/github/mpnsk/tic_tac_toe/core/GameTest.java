package com.github.mpnsk.tic_tac_toe.core;

import com.github.mpnsk.tic_tac_toe.core.Game.GameState;
import lombok.experimental.var;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameTest {
    private final String player1 = "Adam", player2 = "Bob";
    private final List<String> players = new ArrayList<>(Arrays.asList(player1, player2));

    @Test
    public void marking_a_tile_changes_player() {
        var game = new Game(players);
        assertEquals(player1, game.getCurrentPlayer());
        game.markTile(1, 1);
        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    public void moves_mark_field_with_player_number() {
        var game = new Game(players);
        game.markTile(1, 1);
        assertThat(game.getBoard()[1][1], is(1));
        game.markTile(0, 0);
        assertThat(game.getBoard()[0][0], is(2));
    }

    @Test
    public void a_started_game_is_not_won() {
        var game = new Game(players);
        assertThat(game.getGameState(), is(GameState.RUNNING));
    }

    @Test
    public void player_moves_actually_set_the_board() {
        var game = new Game(players);
        game.markTile(0, 0);
        int[][] firstState = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertArrayEquals(firstState, game.getBoard());

        game.markTile(1, 1);
        int[][] secondState = {{1, 0, 0}, {0, 2, 0}, {0, 0, 0}};
        assertArrayEquals(secondState, game.getBoard());
    }

    @Test
    public void is_winning_move() {
        var game = new Game(players);
        int[][] moves = {
                {0, 0}, {0, 1},
                {1, 0}, {0, 2}
        };
        for (var move : moves) {
            game.markTile(move[0], move[1]);
            assertThat(game.getGameState(), is(GameState.RUNNING));
        }
        game.markTile(2,0);
        assertThat(game.getGameState(), is(GameState.WON));
    }

    @Test
    public void invalid_input_throws_exception() {
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

    @Test(expected = IllegalStateException.class)
    public void invalid_move_throws_exception() {
        var game = new Game(players);
        game.markTile(1, 2);
        game.markTile(1, 2);
    }
}