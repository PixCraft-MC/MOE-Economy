package com.pixmeow.plugin.moeeconomy.economy;

import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public abstract class ICard {
    enum CardStatus{
        InActive,
        Active,
        Invalid
    }

    UUID id = UUID.randomUUID();
    Payable owner = null;
    Date releaseDate = new Date();
    CardStatus status = CardStatus.InActive;
    double balance = 0;

    public ICard(Payable owner) {
        this.owner = owner;
    }

    /**
     * Recharge invoices.
     *
     * @param amount the amount
     * @return the invoices
     */
    Invoices recharge(double amount){
        balance += amount;
        return null;
    }

    /**
     * Cost invoices.
     *
     * @param amount the amount
     * @return the invoices
     */
    Invoices cost(double amount, Payable target){
        if (balance < amount)
            return new Invoices(this.owner, target, Invoices.InvoicesType.Outcome, amount, Invoices.InvoicesStatus.Fail);
        balance -= amount;
        return new Invoices(this.owner, target, Invoices.InvoicesType.Outcome, amount, Invoices.InvoicesStatus.Success);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ICard)) return false;
        ICard iCard = (ICard) o;
        return id.equals(iCard.id) &&
                owner.equals(iCard.owner) &&
                releaseDate.equals(iCard.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, releaseDate);
    }
}
