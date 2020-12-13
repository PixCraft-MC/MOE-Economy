package com.pixmeow.plugin.moeeconomy.vault;

import com.pixmeow.plugin.moeeconomy.db.DBQuery;
import com.pixmeow.plugin.moeeconomy.db.MongoConnect;
import com.pixmeow.plugin.moeeconomy.economy.Fee;
import com.pixmeow.plugin.moeeconomy.economy.Interest;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.List;

/**
 * The type Vault adapter.
 */
public class VaultAdapter implements Economy {
    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double v) {
        return String.format("{%.2f} 円",v);
    }

    @Override
    public String currencyNamePlural() {
        return "yen";
    }

    @Override
    public String currencyNameSingular() {
        return "yen";
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public boolean hasAccount(String s) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return DBQuery.FindAccount(offlinePlayer.getUniqueId().toString());
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public boolean hasAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return DBQuery.FindAccount(offlinePlayer.getUniqueId().toString());
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public double getBalance(String s) {
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return DBQuery.balance(offlinePlayer);
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public double getBalance(String s, String s1) {
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return this.getBalance(offlinePlayer);
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public boolean has(String s, double v) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        if (v < 0) return false;
        return this.getBalance(offlinePlayer) == v;
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    @Override
    public boolean has(String s, String s1, double v) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return this.has(offlinePlayer, v);
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        if (v <= 1000) v *= Fee.I.getFee();
        else if (v <= 5000) v *= Fee.II.getFee();
        else if (v <= 10000) v *= Fee.III.getFee();
        else if (v <= 50000) v *= Fee.IV.getFee();
        else v *= Fee.V.getFee();
        return DBQuery.withdraw(offlinePlayer, v);
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return this.withdrawPlayer(offlinePlayer, v);
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        if (v <= 2000) v *= Fee.I.getFee();
        else if (v <= 6000) v *= Fee.II.getFee();
        else if (v <= 10000) v *= Fee.III.getFee();
        else if (v <= 50000) v *= Fee.IV.getFee();
        else v *= Fee.V.getFee();
        return DBQuery.deposit(offlinePlayer, v);
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return this.depositPlayer(offlinePlayer, v);
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        if (DBQuery.registerBank(offlinePlayer, s))
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.SUCCESS, "成功创建银行！");
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "银行创建失败！");
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        if (DBQuery.deleteBank(s))
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.SUCCESS, "成功注销银行！");
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "银行注销失败!");
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        double balance = DBQuery.bankBalance(s);
        return new EconomyResponse(0, balance,
                balance != -1 ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE,
                balance != -1 ? "" : "获取余额失败！");
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        double amount = DBQuery.bankBalance(s);
        return new EconomyResponse(v, amount, amount >= v ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE,
                amount >= v ? "" : "余额不足！");
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return DBQuery.bankWithdraw(s, v);
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return DBQuery.bankDeposit(s, v);
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        if (DBQuery.bankOwner(offlinePlayer, s)) return new EconomyResponse(0, bankBalance(s).balance, EconomyResponse.ResponseType.SUCCESS, "");
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "你不是 "+ s + " 银行的拥有者！");
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        if (DBQuery.bankMember(offlinePlayer, s)) return new EconomyResponse(0, bankBalance(s).balance, EconomyResponse.ResponseType.SUCCESS, "");
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "不是 "+ s + " 银行的会员");
    }

    @Override
    public List<String> getBanks() {
        return DBQuery.bankList();
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public boolean createPlayerAccount(String s) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return DBQuery.register(offlinePlayer);
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return this.createPlayerAccount(offlinePlayer);
    }
}
