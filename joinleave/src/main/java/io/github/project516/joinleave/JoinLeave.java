package io.github.project516.joinleave;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinLeave extends JavaPlugin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Broadcast a join message to all players
        Component joinMessage =
                Component.text()
                        .append(Component.text("[+]").color(NamedTextColor.GREEN))
                        .append(Component.text(" "))
                        .append(Component.text(event.getPlayer().getName()))
                        .append(Component.text(" joined the game!"))
                        .color(NamedTextColor.YELLOW)
                        .build();

        event.joinMessage(joinMessage);

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Broadcast a leave message to all players
        Component leaveMessage =
                Component.text()
                        .append(Component.text("[-]").color(NamedTextColor.RED))
                        .append(Component.text(" "))
                        .append(Component.text(event.getPlayer().getName()))
                        .append(Component.text(" left the game!"))
                        .color(NamedTextColor.YELLOW)
                        .build();

        event.joinMessage(leaveMessage);
    }
}
