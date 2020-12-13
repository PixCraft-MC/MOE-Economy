package com.pixmeow.plugin.moeeconomy.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;
import static org.bukkit.ChatColor.GRAY;

public class MessageManager {
    // Console part
    public static void consoleGood(String message){
        Bukkit.getConsoleSender().sendMessage(GREEN + message);
    }

    public static void consoleWarning(String message){
        Bukkit.getConsoleSender().sendMessage(GOLD + message);
    }

    public static void consoleBad(String message){
        Bukkit.getConsoleSender().sendMessage(RED + message);
    }

    public static void consoleInfo(String message){
        Bukkit.getConsoleSender().sendMessage(GRAY + message);
    }

    // Player part
    public static void PlayerGood(Player player, String message){
        player.sendMessage(GREEN + message);
    }

    public static void PlayerWarning(Player player, String message){
        player.sendMessage(GOLD + message);
    }

    public static void PlayerBad(Player player, String message){
        player.sendMessage(RED + message);
    }

    public static void PlayerInfo(Player player, String message){
        player.sendMessage(GRAY + message);
    }
}
