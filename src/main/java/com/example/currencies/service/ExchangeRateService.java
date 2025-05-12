package com.example.currencies.service;

import com.example.currencies.dto.CurrencyRate;
import com.example.currencies.dto.FixedSide;
import com.example.currencies.dto.GetExchangeRateRequest;
import com.example.currencies.dto.GetExchangeRateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static com.example.currencies.factory.RateResponseFactory.getRateResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final CurrencyRatesService currencyRatesService;

    public GetExchangeRateResponse getExchangeRate(
            @Valid GetExchangeRateRequest getExchangeRateRequest) {

        BigDecimal amount = getExchangeRateRequest.amount();
        String source = getExchangeRateRequest.source();
        String target = getExchangeRateRequest.target();

        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            return getRateResponse(BigDecimal.ZERO, BigDecimal.ZERO, getExchangeRateRequest);
        }

        if (source.equalsIgnoreCase(target)) {
            return getRateResponse(BigDecimal.ONE, BigDecimal.ONE, getExchangeRateRequest);
        }

        Optional<CurrencyRate> currencyRateOptional = currencyRatesService.getCurrencyRate(source, target);
        if (currencyRateOptional.isEmpty()) {
            return getRateResponse(BigDecimal.ZERO, BigDecimal.ZERO, getExchangeRateRequest);
        }

        CurrencyRate currencyRate = currencyRateOptional.get();
        FixedSide fixedSide = getExchangeRateRequest.fixedSide();

        BigDecimal exchangeRate = getExchangeRate(fixedSide, amount, currencyRate);
        BigDecimal reverseExchangeRate = getReverseExchangeRate(exchangeRate);
        return getRateResponse(exchangeRate, reverseExchangeRate, getExchangeRateRequest);
    }

    public BigDecimal getExchangeRate(
            FixedSide fixedSide,
            BigDecimal amount,
            CurrencyRate currencyRate) {

        BigDecimal rate = currencyRate.getRoundedRate(fixedSide);
        return fixedSide.isSelling()
                ? amount.multiply(rate).setScale(2, RoundingMode.FLOOR)
                : amount.divide(rate, 2, RoundingMode.CEILING);
    }

    private BigDecimal getReverseExchangeRate(BigDecimal currencyRate) {
        return BigDecimal.ONE.divide(currencyRate, 6, RoundingMode.HALF_UP);
    }
}
