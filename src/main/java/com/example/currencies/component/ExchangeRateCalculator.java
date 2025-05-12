package com.example.currencies.component;

import com.example.currencies.dto.CurrencyRate;
import com.example.currencies.dto.FixedSide;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ExchangeRateCalculator {

    public BigDecimal getExchangeRate(
            FixedSide fixedSide,
            BigDecimal amount,
            CurrencyRate currencyRate) {

        BigDecimal rate = currencyRate.getRoundedRate(fixedSide);
        return fixedSide.isSelling()
                ? amount.multiply(rate).setScale(2, RoundingMode.FLOOR)
                : amount.divide(rate, 2, RoundingMode.CEILING);
    }

    public BigDecimal getReverseExchangeRate(BigDecimal currencyRate) {
        return BigDecimal.ONE.divide(currencyRate, 6, RoundingMode.HALF_UP);
    }
}
