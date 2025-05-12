package com.example.currencies.service;

import com.example.currencies.dto.CurrencyRate;
import com.example.currencies.utils.ExchangeRateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyRatesService {

    private static final Map<String, CurrencyRate> currencyRates;

    static {
        currencyRates = Map.of(
                "GBPEUR", new CurrencyRate(new BigDecimal("1.4079"), new BigDecimal("1.4081")),
                "EURGBP", new CurrencyRate(new BigDecimal("1.4081"), new BigDecimal("1.4079"))
        );
    }

    public Optional<CurrencyRate> getCurrencyRate(String source, String target) {
        String currencyPair = ExchangeRateUtils.getCurrencyPair(source, target);
        return Optional.ofNullable(currencyRates.get(currencyPair));
    }
}
