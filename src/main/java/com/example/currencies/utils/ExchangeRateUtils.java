package com.example.currencies.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExchangeRateUtils {

    public static final int CURRENCY_RATE_SCALE = 6;

    public static String getCurrencyPair(String source, String target) {
        return source + target;
    }
}
