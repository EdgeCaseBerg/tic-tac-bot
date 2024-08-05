package space.peetseater.bot.tictac.commands;

import space.peetseater.bot.tictac.Move;
import space.peetseater.bot.tictac.TicTacGame;

import java.util.regex.Matcher;

public class MoveCommand implements BotCommand {

    private final TicTacGame game;

    public MoveCommand(TicTacGame ticTacGame) {
        this.game = ticTacGame;
    }

    @Override
    public boolean canHandle(String inputString) {
        Matcher matcher = Move.validMovePattern.matcher(inputString);
        return matcher.matches();
    }

    @Override
    public String handle(String inputString) {
        Move move = Move.parseMove(inputString);
        if (Move.INVALID.equals(move)) return "Invalid move";

        if (!game.canPlaceValue(move.x(), move.y(), move.value())) {
            return "You can't place that there, try again\n```" + game.boardString() + "```";
        }

        game.setBoxTo(move.x(), move.y(), move.value());

        String out = "```%s```".formatted(game.boardString());

        if (game.isBoardFilled()) {
            out = "No moves left! Draw!\n```%s```".formatted(game.boardString());
        }

        if (game.hasWinner()) {
            String winner = game.getWinnerName();
            out = "%s has won, use `!tt new` to start a new game\n```%s```".formatted(winner, game.boardString());
        }

        return out;
    }
}
