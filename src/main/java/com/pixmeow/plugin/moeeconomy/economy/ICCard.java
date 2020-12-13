package com.pixmeow.plugin.moeeconomy.economy;

public class ICCard extends ICard {
    public ICCard(Payable owner) {
        super(owner);
    }

    /**
     * Recharge invoices.
     *
     * @param amount the amount
     * @return the invoices
     */
    @Override
    public Invoices recharge(double amount) {
        return null;
    }

    /**
     * Cost invoices.
     *
     * @param amount the amount
     * @return the invoices
     */
    @Override
    public Invoices cost(double amount, Payable target) {
        return null;
    }
}
