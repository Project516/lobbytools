package io.github.project516.joinleave;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final Message message = new Message();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String joinMessage = message.join(event);
        event.joinMessage(Component.text(joinMessage));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String quitMessage = message.quit(event);
        event.quitMessage(Component.text(quitMessage));
    }
}
