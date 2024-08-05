package space.peetseater.bot.tictac;

import java.util.HashMap;

public class TicTacGame {

    int[][] board = new int[][] {
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };

    final static public int EMPTY = 0;
    final static public int X = 1;
    final static public int O = 2;
    private final HashMap<Integer, String> values = new HashMap<>();
    public TicTacGame() {
        values.put(EMPTY, " ");
        values.put(X, "X");
        values.put(O, "O");
    }

    public boolean validMoveInputs(int x, int y, int value) {
        if (x < 1 || x > 3) return false;
        if (y < 1 || y > 3) return false;
        if (!values.containsKey(value)) return false;
        return true;
    }

    public boolean canPlaceValue(int x, int y, int value) {
        if (!validMoveInputs(x, y, value)) return false;

        int xSubscript = x - 1;
        int ySubscript = y - 1;

        return board[xSubscript][ySubscript] == EMPTY;
    }

    public int getWinner() {
        for (int i = 0; i < 3; i++) {
            boolean sameInRow = board[i][0] == board[i][1] && board[i][0] == board[i][2];
            boolean sameInCol = board[0][i] == board[1][i] && board[0][i] == board[2][i];
            if (sameInCol || sameInRow) {
                if (board[i][0] != EMPTY) {
                    return board[i][0];
                }
                if (board[0][i] != EMPTY) {
                    return board[0][i];
                }
            }
        }

        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return board[0][0];
        }

        return EMPTY;
    }

    public void setBoxTo(int x, int y, int value) {
        if (!validMoveInputs(x,y,value)) return;

        int xSubscript = x - 1;
        int ySubscript = y - 1;

        board[xSubscript][ySubscript] = value;
    }

    public String boardString() {
        StringBuilder stringBuilder = new StringBuilder(12);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int v = board[i][j];
                stringBuilder.append('[').append(values.get(v)).append(']');
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public boolean hasWinner() {
        return getWinner() != EMPTY;
    }

    public String getWinnerName() {
        return values.get(getWinner());
    }
}

