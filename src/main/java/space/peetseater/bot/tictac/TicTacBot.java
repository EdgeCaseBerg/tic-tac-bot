package space.peetseater.bot.tictac;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import space.peetseater.bot.tictac.commands.*;

import java.util.LinkedList;

public class TicTacBot extends ListenerAdapter {

    TicTacGame ticTacGame;
    LinkedList<BotCommand> anyTimeCommands;
    LinkedList<BotCommand> gameInProgressCommands;

    public TicTacBot() {
        ticTacGame = new TicTacGame();
        HelpCommand helpCommand = new HelpCommand();
        ShowCommand showCommand = new ShowCommand(ticTacGame);
        NewGameCommand newGameCommand = new NewGameCommand(ticTacGame);
        MoveCommand moveCommand = new MoveCommand(ticTacGame);

        anyTimeCommands = new LinkedList<>();
        anyTimeCommands.add(helpCommand);
        anyTimeCommands.add(newGameCommand);

        gameInProgressCommands = new LinkedList<>();
        gameInProgressCommands.add(moveCommand);
        gameInProgressCommands.add(showCommand);

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
        for (BotCommand botCommand : anyTimeCommands) {
            if (botCommand.canHandle(command)) {
                String response = botCommand.handle(command);
                sendMessage(channel, response);
                return;
            }
        }

        if (gameAlreadyOver(channel)) return;

        for (BotCommand botCommand : gameInProgressCommands) {
            if (botCommand.canHandle(command)) {
                String response = botCommand.handle(command);
                sendMessage(channel, response);
                return;
            }
        }

        sendMessage(channel, "I didn't understand your message, try `!tt help` for help");
    }

    private boolean gameAlreadyOver(MessageChannel channel) {
        if (ticTacGame.hasWinner()) {
            sendMessage(channel, "The game has already ended, use `!tt new` to start again");
            return true;
        }

        if (ticTacGame.isBoardFilled()) {
            sendMessage(channel, "The board is full! use `!tt new` to start fresh");
            return true;
        }
        return false;
    }
}

