package com.example.happyghast;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class HappyGhastSpawnListener implements Listener {

    private final HappyGhastSpeedPlugin plugin;

    public HappyGhastSpawnListener(HappyGhastSpeedPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntitySpawn(EntitySpawnEvent event) {
        // Apply speed to newly spawned happy ghasts
        plugin.applySpeed(event.getEntity());
    }
}
