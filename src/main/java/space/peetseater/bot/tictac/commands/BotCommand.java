package space.peetseater.bot.tictac.commands;

public interface BotCommand {
    boolean canHandle(String inputString);

    /** Returns any string that should be send back to the caller */
    String handle(String inputString);
}
