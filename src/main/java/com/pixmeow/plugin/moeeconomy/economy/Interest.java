package com.pixmeow.plugin.moeeconomy.economy;

public enum Interest {
    I(0.02),
    II(0.04),
    III(0.6),
    IV(0.65),
    V(0.7);
    private final double interest;

    Interest(double interest) {
        this.interest = interest;
    }

    public double getInterest() {
        return interest;
    }
}
