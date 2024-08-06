package space.peetseater.bot.tictac;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.ConcurrentHashMap;

public class BotRouter extends ListenerAdapter {

    ConcurrentHashMap<String, TicTacBot> serverGames;

    public BotRouter() {
        serverGames = new ConcurrentHashMap<>();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String gameId;
        if (event.isFromGuild()) {
            gameId = event.getGuild().getId();
        } else {
            gameId = event.getAuthor().getId();
        }

        TicTacBot ticTacBot = getBotForId(gameId);
        ticTacBot.onMessageReceived(event);
    }

    private TicTacBot getBotForId(String guildId) {
        if (serverGames.containsKey(guildId)) {
            return serverGames.get(guildId);
        }

        TicTacBot ticTacBot = new TicTacBot();
        serverGames.put(guildId, ticTacBot);
        return ticTacBot;
    }
}
