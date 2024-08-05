package space.peetseater.bot;

import org.junit.jupiter.api.Test;
import space.peetseater.bot.tictac.Move;
import space.peetseater.bot.tictac.TicTacGame;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    public void testParse11x() {
        Move move = Move.parseMove("12 x");

        assertNotEquals(null, move);
        assertEquals(1, move.x());
        assertEquals(2, move.y());
        assertEquals(TicTacGame.X, move.value());
    }

    @Test
    public void testParse11X() {
        Move move = Move.parseMove("12 X");

        assertNotEquals(null, move);
        assertEquals(1, move.x());
        assertEquals(2, move.y());
        assertEquals(TicTacGame.X, move.value());
    }

    @Test
    public void testParse33O() {
        Move move = Move.parseMove("33 o");

        assertNotEquals(null, move);
        assertEquals(3, move.x());
        assertEquals(3, move.y());
        assertEquals(TicTacGame.O, move.value());
    }
}