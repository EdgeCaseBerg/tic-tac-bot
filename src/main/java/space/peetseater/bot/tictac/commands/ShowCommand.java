package space.peetseater.bot.tictac.commands;

import space.peetseater.bot.tictac.TicTacGame;

public class ShowCommand implements BotCommand {

    private final TicTacGame game;

    public ShowCommand(TicTacGame ticTacGame) {
        this.game = ticTacGame;
    }

    @Override
    public boolean canHandle(String inputString) {
        return "show".equals(inputString);
    }

    @Override
    public String handle(String inputString) {
        return "```" +  game.boardString() + "```";
    }
}
