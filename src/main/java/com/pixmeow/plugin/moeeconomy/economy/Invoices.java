package com.pixmeow.plugin.moeeconomy.economy;

import java.util.UUID;

import static org.bukkit.ChatColor.*;

public class Invoices {
    enum InvoicesType{
        Outcome(RED + "- " + RESET),
        Income(GREEN + "+ " + RESET);

        private final String output;
        InvoicesType(String output){
            this.output = output;
        }

        public String getOutput() {
            return output;
        }
    }

    enum InvoicesStatus{
        Success(GREEN + "成功" + RESET),
        Fail(RED + "失败" + RESET);

        private final String status;
        InvoicesStatus(String status){
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    UUID id = UUID.randomUUID();
    Payable payer;
    Payable payee;
    InvoicesType type;
    double amount;
    InvoicesStatus status;

    public Invoices(Payable payer, Payable payee, InvoicesType type, double amount, InvoicesStatus status) {
        this.payer = payer;
        this.payee = payee;
        this.type = type;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        return "====== 收据 ======" +
                "id:" + id + "\n" +
                "付款人: " + payer + "\n" +
                "收款方: " + payee + "\n" +
                "金额: " + type + amount;
    }
}
