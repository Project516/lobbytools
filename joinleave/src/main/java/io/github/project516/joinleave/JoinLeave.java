package io.github.project516.joinleave;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinLeave extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new events(), this);
    }

    @Override
    public void onDisable() {
        // Shutdown
    }
}
