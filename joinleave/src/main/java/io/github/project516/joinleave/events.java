package io.github.project516.joinleave;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class events implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer()
                .sendMessage(Component.text("Welcome, " + event.getPlayer().getName() + "!"));
    }
}
