package space.peetseater.bot.tictac.commands;

import space.peetseater.bot.tictac.TicTacGame;

public class NewGameCommand implements BotCommand {

    private final TicTacGame game;

    public NewGameCommand(TicTacGame ticTacGame) {
        this.game = ticTacGame;
    }

    @Override
    public boolean canHandle(String inputString) {
        return "new".equals(inputString);
    }

    @Override
    public String handle(String inputString) {
        game.reset();
        return "New game started! Take your first move!";
    }
}
