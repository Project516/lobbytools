package io.github.project516.joinleave;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

class Message {
    // Lists of possible messages
    private static final List<String> JOIN_MESSAGES =
            Arrays.asList(
                    "Welcome %s!",
                    "Hey %s, welcome back!",
                    "Glad to see you, %s!",
                    "%s joined the game.",
                    "Hello %s, enjoy your stay!");

    private static final List<String> QUIT_MESSAGES =
            Arrays.asList(
                    "%s left the game.",
                    "See you later, %s!",
                    "%s has logged off.",
                    "Goodbye %s!",
                    "%s disconnected. Come back soon!");

    String join(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        String randomMessage =
                JOIN_MESSAGES.get(ThreadLocalRandom.current().nextInt(JOIN_MESSAGES.size()));
        return String.format(randomMessage, playerName);
    }

    String quit(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();
        String randomMessage =
                QUIT_MESSAGES.get(ThreadLocalRandom.current().nextInt(QUIT_MESSAGES.size()));
        return String.format(randomMessage, playerName);
    }
}
