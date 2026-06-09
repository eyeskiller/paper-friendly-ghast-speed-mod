package com.example.happyghast;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpeedCommand implements CommandExecutor {

    private final HappyGhastSpeedPlugin plugin;

    public SpeedCommand(HappyGhastSpeedPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("happyghast.speed")) {
            sender.sendMessage(Component.text("You don't have permission to use this command.").color(NamedTextColor.RED));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(
                    Component.text("Current Happy Ghast speed: ").color(NamedTextColor.YELLOW)
                            .append(Component.text(plugin.getGlobalSpeed()).color(NamedTextColor.WHITE)));
            sender.sendMessage(
                    Component.text("Usage: /" + label + " <number> OR /" + label + " <increase|decrease> <number>").color(NamedTextColor.YELLOW));
            return true;
        }

        try {
            if (args.length == 1) {
                // /happyghastspeed <number>
                double newSpeed = Double.parseDouble(args[0]);
                plugin.setGlobalSpeed(newSpeed);
                sender.sendMessage(Component.text("Happy Ghast speed set to: " + newSpeed).color(NamedTextColor.GREEN));
            } else if (args.length == 2) {
                // /happyghastspeed <increase|decrease> <number>
                String action = args[0].toLowerCase();
                double amount = Double.parseDouble(args[1]);
                double currentSpeed = plugin.getGlobalSpeed();

                if (action.equals("increase") || action.equals("add")) {
                    plugin.setGlobalSpeed(currentSpeed + amount);
                    sender.sendMessage(Component.text("Happy Ghast speed increased by " + amount + " to: " + plugin.getGlobalSpeed()).color(NamedTextColor.GREEN));
                } else if (action.equals("decrease") || action.equals("reduce")) {
                    plugin.setGlobalSpeed(Math.max(0, currentSpeed - amount));
                    sender.sendMessage(Component.text("Happy Ghast speed decreased by " + amount + " to: " + plugin.getGlobalSpeed()).color(NamedTextColor.GREEN));
                } else {
                    sender.sendMessage(Component.text("Unknown action. Use 'increase' or 'decrease'.").color(NamedTextColor.RED));
                }
            } else {
                sender.sendMessage(Component.text("Too many arguments. Usage: /" + label + " <number> OR /" + label + " <increase|decrease> <number>").color(NamedTextColor.RED));
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(Component.text("Invalid number provided.").color(NamedTextColor.RED));
        }

        return true;
    }
}
