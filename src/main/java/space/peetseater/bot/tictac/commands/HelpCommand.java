package space.peetseater.bot.tictac.commands;

public class HelpCommand implements BotCommand {

    @Override
    public boolean canHandle(String inputString) {
        return "help".equals(inputString);
    }

    @Override
    public String handle(String inputString) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Use `!tt` to send commands");
        stringBuilder.append("\n");
        stringBuilder.append("\t `!tt show` Will show you the current board");
        stringBuilder.append("\n");
        stringBuilder.append("\t `!tt new` Will reset the board to a clean slate");
        stringBuilder.append("\n");
        stringBuilder.append("\t `!tt help` Will show this text");
        stringBuilder.append("\n");
        stringBuilder.append("\t `!tt 12 X`\t Will set the first row and second column value to X");
        stringBuilder.append(", ");
        stringBuilder.append("you may use numbers `1`, `2`, and `3` to address rows and columns");
        stringBuilder.append(", ");
        stringBuilder.append("you may use `X`,`x`,`O`, or `o` for the values to set on the board");
        return stringBuilder.toString();
    }
}
