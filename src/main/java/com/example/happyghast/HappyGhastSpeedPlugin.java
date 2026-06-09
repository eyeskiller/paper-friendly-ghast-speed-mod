package com.example.happyghast;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

public class HappyGhastSpeedPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Save default config if not exists
        saveDefaultConfig();

        // Register event listener
        getServer().getPluginManager().registerEvents(new HappyGhastSpawnListener(this), this);

        // Register command
        getCommand("happyghastspeed").setExecutor(new SpeedCommand(this));

        getLogger().info("HappyGhastSpeed plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("HappyGhastSpeed plugin disabled!");
    }

    public double getGlobalSpeed() {
        return getConfig().getDouble("global-happy-ghast-speed", 0.3);
    }

    public void setGlobalSpeed(double speed) {
        getConfig().set("global-happy-ghast-speed", speed);
        saveConfig();
        updateAllExistingHappyGhasts();
    }

    public void applySpeed(Entity entity) {
        if (!(entity instanceof LivingEntity)) return;
        LivingEntity livingEntity = (LivingEntity) entity;

        // Check if it's a happy ghast by name to support multiple versions safely
        if (entity.getType().name().equals("HAPPY_GHAST")) {
            double speed = getGlobalSpeed();
            
            try {
                // Try applying flying speed
                AttributeInstance flyingSpeed = livingEntity.getAttribute(Attribute.valueOf("GENERIC_FLYING_SPEED"));
                if (flyingSpeed != null) {
                    flyingSpeed.setBaseValue(speed);
                }
            } catch (IllegalArgumentException e) {
                // Attribute not found, ignore or try new naming
                try {
                    AttributeInstance flyingSpeed = livingEntity.getAttribute(Attribute.valueOf("FLYING_SPEED"));
                    if (flyingSpeed != null) {
                        flyingSpeed.setBaseValue(speed);
                    }
                } catch (IllegalArgumentException ignored) {}
            }

            try {
                // Try applying movement speed
                AttributeInstance moveSpeed = livingEntity.getAttribute(Attribute.valueOf("GENERIC_MOVEMENT_SPEED"));
                if (moveSpeed != null) {
                    moveSpeed.setBaseValue(speed);
                }
            } catch (IllegalArgumentException e) {
                try {
                    AttributeInstance moveSpeed = livingEntity.getAttribute(Attribute.valueOf("MOVEMENT_SPEED"));
                    if (moveSpeed != null) {
                        moveSpeed.setBaseValue(speed);
                    }
                } catch (IllegalArgumentException ignored) {}
            }
        }
    }

    private void updateAllExistingHappyGhasts() {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                applySpeed(entity);
            }
        }
    }
}
