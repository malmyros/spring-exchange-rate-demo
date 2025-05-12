package com.example.currencies.service;

import com.example.currencies.component.ExchangeRateCalculator;
import com.example.currencies.dto.CurrencyRate;
import com.example.currencies.dto.FixedSide;
import com.example.currencies.dto.GetExchangeRateRequest;
import com.example.currencies.dto.GetExchangeRateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.currencies.factory.RateResponseFactory.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final CurrencyRatesService currencyRatesService;
    private final ExchangeRateCalculator exchangeRateCalculator;

    public GetExchangeRateResponse getExchangeRate(
            @Valid GetExchangeRateRequest getExchangeRateRequest) {

        BigDecimal amount = getExchangeRateRequest.amount();
        String source = getExchangeRateRequest.source();
        String target = getExchangeRateRequest.target();

        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            return getZeroAmountExchangeRate(getExchangeRateRequest);
        }

        if (source.equalsIgnoreCase(target)) {
            return getSameCurrencyExchangeRateResponse(getExchangeRateRequest);
        }

        Optional<CurrencyRate> currencyRateOptional = currencyRatesService.getCurrencyRate(source, target);
        if (currencyRateOptional.isEmpty()) {
            return getZeroAmountExchangeRate(getExchangeRateRequest);
        }

        CurrencyRate currencyRate = currencyRateOptional.get();
        FixedSide fixedSide = getExchangeRateRequest.fixedSide();

        BigDecimal exchangeRate = exchangeRateCalculator.getExchangeRate(fixedSide, amount, currencyRate);
        BigDecimal reverseExchangeRate = exchangeRateCalculator.getReverseExchangeRate(exchangeRate);
        return getRateResponse(exchangeRate, reverseExchangeRate, getExchangeRateRequest);
    }


}
