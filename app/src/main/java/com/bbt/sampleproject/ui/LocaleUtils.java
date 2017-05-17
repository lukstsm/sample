package com.bbt.sampleproject.ui;


import java.text.NumberFormat;

public class LocaleUtils {

    public static String getLocalisedCurrency(double amount) {
        return getLocalisedCurrency() + getLocalisedNumber(amount);
    }

    private static String getLocalisedCurrency() {
        return NumberFormat.getInstance().getCurrency().getSymbol();
    }

    private static String getLocalisedNumber(double amount) {
        return NumberFormat.getInstance().format(amount);
    }

}
