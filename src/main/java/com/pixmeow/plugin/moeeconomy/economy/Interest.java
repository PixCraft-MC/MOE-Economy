package com.pixmeow.plugin.moeeconomy.economy;

public enum Interest {
    I(0.01),
    II(0.02),
    III(0.25),
    IV(0.03),
    V(0.04);
    private final double interest;

    Interest(double interest) {
        this.interest = interest;
    }

    public double getInterest() {
        return interest;
    }
}
