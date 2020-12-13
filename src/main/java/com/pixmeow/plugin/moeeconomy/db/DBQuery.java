package com.pixmeow.plugin.moeeconomy.db;


import net.milkbowl.vault.economy.EconomyResponse;
import org.bson.Document;
import org.bukkit.OfflinePlayer;

public class DBQuery extends MongoConnect{
    public static boolean register(OfflinePlayer player){
        return false;
    }

    public static boolean registerBank(OfflinePlayer player){
        return false;
    }

    public static boolean deleteBank(){
        return false;
    }

    public static boolean FindAccount(String id){
        return accounts.find(new Document("uid", id)).first() != null;
    }

    public static double balance(OfflinePlayer player){
        Document result = accounts.find(new Document("uid", player.getUniqueId().toString())).first();
        return result != null ? (double) result.get("balance") : -1;
    }

    public static double bankBalance(OfflinePlayer player){
        Document result = bankAccounts.find(new Document("uid", player.getUniqueId().toString())).first();
        return result != null ? (double) result.get("balance") : -1;
    }

    public static EconomyResponse withdraw(OfflinePlayer executor, double amount){
        if (amount < 0) return new EconomyResponse(amount, balance(executor), EconomyResponse.ResponseType.FAILURE, "无法取出负数金额！");

        return  null;
    }

    public static EconomyResponse deposit(OfflinePlayer executor, double amount){
        if (amount < 0) return new EconomyResponse(amount, balance(executor), EconomyResponse.ResponseType.FAILURE, "无法存入负数金额！");

        return  null;
    }
}
