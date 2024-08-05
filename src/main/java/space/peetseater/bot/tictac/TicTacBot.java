package space.peetseater.bot.tictac;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.regex.Matcher;

public class TicTacBot extends ListenerAdapter {

    TicTacGame ticTacGame;

    public TicTacBot() {
        ticTacGame = new TicTacGame();
    }

    public boolean isValidMoveInput(String raw) {
        Matcher matcher = Move.validMovePattern.matcher(raw);
        return matcher.matches();
    }

    public void sendMessage(MessageChannel channel, String message) {
        channel.sendMessage(message).queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String rawMessageContent = message.getContentRaw();

        if (!rawMessageContent.startsWith("!tt ")) {
            return;
        }

        MessageChannel channel = event.getChannel();
        String noTT = rawMessageContent.substring(4).trim();

        handleMessage(noTT, channel);
    }

    private void handleMessage(String command, MessageChannel channel) {
        if (command.equals("show")) {
            sendBoardStatus(channel);
            return;
        }

        if (command.equals("new")) {
            ticTacGame.reset();
            sendMessage(channel, "New game started! Take your first move!");
            return;
        }

        if (command.equals("help")) {
            sendHelp(channel);
            return;
        }

        if (ticTacGame.hasWinner()) {
            sendMessage(channel, "The game has already ended, use `!tt new` to start again");
            return;
        }

        if (ticTacGame.isBoardFilled()) {
            sendMessage(channel, "The board is full! use !tt new to start fresh");
            return;
        }

        if (isValidMoveInput(command)) {
            Move move = Move.parseMove(command);
            if (!ticTacGame.canPlaceValue(move.x(), move.y(), move.value())) {
                sendMessage(channel, "You can't place that there, try again\n" + getFormattedBoardString());
                return;
            }

            ticTacGame.setBoxTo(move.x(), move.y(), move.value());

            if (ticTacGame.hasWinner()) {
                String winner = ticTacGame.getWinnerName();
                sendMessage(channel,"%s has won, use `!tt new` to start a new game\n%s".formatted(winner, getFormattedBoardString()));
            } else {
                sendBoardStatus(channel);
            }

            return;
        }

        sendMessage(channel, "I didn't understand your message, try !tt help");
    }

    private void sendHelp(MessageChannel channel) {
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
        sendMessage(channel, stringBuilder.toString());
    }

    private void sendBoardStatus(MessageChannel channel) {
        sendMessage(channel, getFormattedBoardString());
    }

    private String getFormattedBoardString() {
        String currentBoard = ticTacGame.boardString();
        String codeTag = "```";
        return codeTag + currentBoard + codeTag;
    }
}

