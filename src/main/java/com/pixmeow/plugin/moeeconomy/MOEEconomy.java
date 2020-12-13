package com.pixmeow.plugin.moeeconomy;

import com.pixmeow.plugin.moeeconomy.manager.MessageManager;
import com.pixmeow.plugin.moeeconomy.vault.VaultAdapter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class MOEEconomy extends JavaPlugin {
    private VaultAdapter vaultAdapter;
    private static MOEEconomy instance;

    @Override
    public void onEnable() {
        if (!dependency())
            this.setEnabled(false);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    boolean dependency() {
        // ===================
        // Vault
        // ===================

        // 经济
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            MessageManager.consoleGood("Miss Vault plugin!");
            return false;
        }

        if (!getServer().getServicesManager().isProvidedFor(Economy.class))
            getServer().getServicesManager().register(Economy.class, vaultAdapter, this, ServicePriority.Highest);
        else
            MessageManager.consoleWarning("经济服务已被其他插件注册: " + getServer().getServicesManager().getRegistration(Economy.class).getProvider().getName());
        // 权限

        // 聊天

        // ===================
        // ProtocolLib
        // ===================

        MessageManager.consoleGood("Dependency Check Complete");
        return true;
    }

    public VaultAdapter getVaultAdapter() {
        return vaultAdapter;
    }

    public static MOEEconomy getInstance() {
        return instance;
    }
}
