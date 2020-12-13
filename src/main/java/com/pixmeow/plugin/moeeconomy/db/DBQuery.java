package com.pixmeow.plugin.moeeconomy.db;


import com.mongodb.client.DistinctIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bson.Document;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.List;

public class DBQuery extends MongoConnect{
    public static boolean register(OfflinePlayer player){
        accounts.insertOne(new Document().append("uid", player.getUniqueId().toString())
                .append("balance", (double)0));
        return false;
    }

    public static boolean registerBank(OfflinePlayer player, String s){
        if (bankAccounts.find(new Document().append("identity", s)).first() != null) return false;
        InsertOneResult result = bankAccounts.insertOne(new Document().append("uid", player.getUniqueId().toString())
                                             .append("balance", (double)0)
                                             .append("identity", s));
        return result.wasAcknowledged();
    }

    public static boolean deleteBank(String s){
        DeleteResult result = bankAccounts.deleteOne(new Document().append("identity", s));
        return result.wasAcknowledged();
    }

    public static boolean FindAccount(String id){
        return accounts.find(new Document("uid", id)).first() != null;
    }

    public static double balance(OfflinePlayer player){
        Document result = accounts.find(new Document("uid", player.getUniqueId().toString())).first();
        return result != null ? (double) result.get("balance") : -1;
    }

    public static double bankBalance(String s){
        Document result = bankAccounts.find(new Document("identity", s)).first();
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

    public static EconomyResponse bankWithdraw(String s, double amount){
        if (amount < 0) return new EconomyResponse(amount, bankBalance(s), EconomyResponse.ResponseType.FAILURE, "无法取出负数金额！");

        return  null;
    }

    public static EconomyResponse bankDeposit(String s, double amount){
        if (amount < 0) return new EconomyResponse(amount, bankBalance(s), EconomyResponse.ResponseType.FAILURE, "无法存入负数金额！");

        return  null;
    }

    public static boolean bankOwner(OfflinePlayer owner, String bankName){
        Document result = bankAccounts.find(new Document().append("identity", bankName)).first();
        if (result == null) return false;
        return result.get("uid").equals(owner.getUniqueId().toString());
    }

    public static boolean bankMember(OfflinePlayer player, String s){
        Document result = bankAccounts.find(new Document().append("uid", player)).first();
        if (result == null) return false;
        return result.get("identity").equals(s);
    }

    public static List<String> bankList(){
        ArrayList<String> bankList = new ArrayList<>();
        DistinctIterable<String> result = bankAccounts.distinct("identity", String.class);
        result.forEach(bankList::add);
        return bankList;
    }

    private Document accountTemp(OfflinePlayer player){
        return new Document()
                .append("uid", player.getUniqueId().toString())
                .append("balance", (double) 0);
    }

    private Document bankAccountTemp(OfflinePlayer player, String s, boolean isOwner){
        return new Document()
                .append("uid", player.getUniqueId().toString())
                .append("identity", s)
                .append("isOwner", isOwner)
                .append("balance", (double) 0);
    }
}
