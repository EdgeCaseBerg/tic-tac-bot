package space.peetseater.bot.tictac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacGameTest  {

    TicTacGame ticTacGame;

    @BeforeEach
    void setup() {
        ticTacGame = new TicTacGame();
    }

    @Test
    void getWinnerRow() {
        ticTacGame.setBoxTo(1,1, TicTacGame.X);
        ticTacGame.setBoxTo(1,2, TicTacGame.X);
        ticTacGame.setBoxTo(1,3, TicTacGame.X);
        assertEquals(TicTacGame.X, ticTacGame.getWinner());
    }

    @Test
    void getWinnerCol() {
        ticTacGame.setBoxTo(1,1, TicTacGame.X);
        ticTacGame.setBoxTo(2,1, TicTacGame.X);
        ticTacGame.setBoxTo(3,1, TicTacGame.X);
        assertEquals(TicTacGame.X, ticTacGame.getWinner());
    }

    @Test
    void getWinnerDiagonal() {
        ticTacGame.setBoxTo(1,1, TicTacGame.X);
        ticTacGame.setBoxTo(2,2, TicTacGame.X);
        ticTacGame.setBoxTo(3,3, TicTacGame.X);
        assertEquals(TicTacGame.X, ticTacGame.getWinner());
    }

    @Test
    void getNoWinner() {
        ticTacGame.setBoxTo(1,1, TicTacGame.X);
        ticTacGame.setBoxTo(2,2, TicTacGame.O);
        ticTacGame.setBoxTo(3,3, TicTacGame.X);
        assertEquals(TicTacGame.EMPTY, ticTacGame.getWinner());
    }

    @Test
    void regressionTest() {
        ticTacGame.setBoxTo(2,2, TicTacGame.X);
        ticTacGame.setBoxTo(1,1, TicTacGame.O);
        ticTacGame.setBoxTo(2,1, TicTacGame.X);
        ticTacGame.setBoxTo(2,3, TicTacGame.O);
        assertEquals(TicTacGame.EMPTY, ticTacGame.getWinner());
    }
}