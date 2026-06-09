package com.example.happyghast;

import org.bukkit.ChatColor;
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
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Current Happy Ghast speed: " + ChatColor.WHITE + plugin.getGlobalSpeed());
            sender.sendMessage(ChatColor.YELLOW + "Usage: /" + label + " <number> OR /" + label + " <increase|decrease> <number>");
            return true;
        }

        try {
            if (args.length == 1) {
                // /happyghastspeed <number>
                double newSpeed = Double.parseDouble(args[0]);
                plugin.setGlobalSpeed(newSpeed);
                sender.sendMessage(ChatColor.GREEN + "Happy Ghast speed set to: " + newSpeed);
            } else if (args.length == 2) {
                // /happyghastspeed <increase|decrease> <number>
                String action = args[0].toLowerCase();
                double amount = Double.parseDouble(args[1]);
                double currentSpeed = plugin.getGlobalSpeed();

                if (action.equals("increase") || action.equals("add")) {
                    plugin.setGlobalSpeed(currentSpeed + amount);
                    sender.sendMessage(ChatColor.GREEN + "Happy Ghast speed increased by " + amount + " to: " + plugin.getGlobalSpeed());
                } else if (action.equals("decrease") || action.equals("reduce")) {
                    plugin.setGlobalSpeed(Math.max(0, currentSpeed - amount));
                    sender.sendMessage(ChatColor.GREEN + "Happy Ghast speed decreased by " + amount + " to: " + plugin.getGlobalSpeed());
                } else {
                    sender.sendMessage(ChatColor.RED + "Unknown action. Use 'increase' or 'decrease'.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Too many arguments. Usage: /" + label + " <number> OR /" + label + " <increase|decrease> <number>");
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Invalid number provided.");
        }

        return true;
    }
}
