package com.pixmeow.plugin.moeeconomy.db;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pixmeow.plugin.moeeconomy.MOEEconomy;
import com.pixmeow.plugin.moeeconomy.manager.MessageManager;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.configuration.file.FileConfiguration;

public class MongoConnect {
    protected final MOEEconomy plugin = MOEEconomy.getInstance();
    protected MongoDatabase database;
    protected static MongoCollection<Document> accounts;
    protected static MongoCollection<Document> bankAccounts;

    public void connect() {
        MongoClient client = MongoClients.create(ConnectURL());
        // TODO Check whether the database connect successfully

        setDatabase(client.getDatabase(plugin.getConfig().getString("DB.database")));
        setCollection(database.getCollection(Collections.Account.collectionName), accounts);
        setCollection(database.getCollection(Collections.BankAccount.collectionName), bankAccounts);
        MessageManager.consoleGood("MongoDB Connected!");
    }

    ConnectionString ConnectURL(){
        FileConfiguration config = plugin.getConfig();
        return new ConnectionString("mongodb://" +
                config.getString("DB.username") +
                ":" + config.getString("DB.password") +
                "@" + config.getString("DB.host") +
                ":" + config.getString("DB.port")
        );
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setCollection(MongoCollection<Document> origin, MongoCollection<Document> target) {
        target = origin;
    }

    private void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    private void createCollection(String c) {
        database.createCollection(c);
    }

    public enum Collections {
        Account("accounts"),
        BankAccount("bank_accounts");

        private final String collectionName;
        Collections(String name) {
            collectionName = name;
        }
        public String getCollectionName() {
            return collectionName;
        }
    }
}
