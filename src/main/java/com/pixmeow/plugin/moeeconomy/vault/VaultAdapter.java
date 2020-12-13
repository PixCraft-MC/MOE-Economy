package com.pixmeow.plugin.moeeconomy.vault;

import com.pixmeow.plugin.moeeconomy.db.DBQuery;
import com.pixmeow.plugin.moeeconomy.db.MongoConnect;
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
        if (v <= 1000) v *= Interest.I.getInterest();
        else if (v <= 5000) v *= Interest.II.getInterest();
        else if (v <= 10000) v *= Interest.III.getInterest();
        else if (v <= 50000) v *= Interest.IV.getInterest();
        else v *= Interest.V.getInterest();
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
        if (v <= 2000) v *= Interest.I.getInterest();
        else if (v <= 6000) v *= Interest.II.getInterest();
        else if (v <= 10000) v *= Interest.III.getInterest();
        else if (v <= 50000) v *= Interest.IV.getInterest();
        else v *= Interest.V.getInterest();
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
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
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
        return null;
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
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
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
        return false;
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
