package com.pixmeow.plugin.moeeconomy.economy;

public enum Fee {
    I(0.01),
    II(0.02),
    III(0.03),
    IV(0.035),
    V(0.04);
    private final double fee;

    Fee(double fee) {
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }
}
