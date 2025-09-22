package io.github.project516;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class joinleave extends JavaPlugin implements Listener {
  
  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    getLogger().info("JoinLeave plugin has been enabled!");
  }

  @Override
  public void onDisable() {
    getLogger().info("JoinLeave plugin has been disabled!");
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    // Broadcast a join message to all players
    Component joinMessage = Component.text()
        .append(Component.text("[+]").color(NamedTextColor.GREEN))
        .append(Component.text(" "))
        .append(Component.text(event.getPlayer().getName()))
        .append(Component.text(" joined the game!"))
        .color(NamedTextColor.YELLOW)
        .build();
    
    Bukkit.broadcast(joinMessage);
    
    // Send a private welcome message to the joining player
    event.getPlayer().sendMessage(Component.text()
        .append(Component.text("Welcome to the server, "))
        .append(Component.text(event.getPlayer().getName()))
        .append(Component.text("!"))
        .color(NamedTextColor.AQUA)
        .build());
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    // Broadcast a leave message to all players
    Component leaveMessage = Component.text()
        .append(Component.text("[-]").color(NamedTextColor.RED))
        .append(Component.text(" "))
        .append(Component.text(event.getPlayer().getName()))
        .append(Component.text(" left the game!"))
        .color(NamedTextColor.YELLOW)
        .build();
    
    Bukkit.broadcast(leaveMessage);
  }
}