package space.peetseater.bot.tictac;

import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public record Move(int x, int y, int value) {

    public static final Pattern validMovePattern = Pattern.compile("([1-3])([1-3]) ([XxOo])");

    public final static Move INVALID = new Move(-1, -1, -1);

    public static Move parseMove(String noTT) {

        Scanner scanner = new Scanner(noTT);
        String found = scanner.findInLine(validMovePattern);
        if (found == null) {
            return INVALID;
        }

        MatchResult matchResult = scanner.match();

        String xString = matchResult.group(1);
        String yString = matchResult.group(2);
        String vString = matchResult.group(3);

        int x = Integer.parseInt(xString);
        int y = Integer.parseInt(yString);
        int v = switch (vString) {
            case "X", "x" -> TicTacGame.X;
            case "O", "o" -> TicTacGame.O;
            default -> TicTacGame.EMPTY;
        };

        return new Move(x, y, v);
    }
}
