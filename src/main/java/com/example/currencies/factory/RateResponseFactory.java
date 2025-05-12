package com.example.currencies.factory;

import com.example.currencies.dto.GetExchangeRateRequest;
import com.example.currencies.dto.GetExchangeRateResponse;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.Instant;

@UtilityClass
public class RateResponseFactory {

    public static GetExchangeRateResponse getRateResponse(
            BigDecimal rate,
            BigDecimal reverseRate,
            GetExchangeRateRequest getExchangeRateRequest) {

        return GetExchangeRateResponse.builder()
                .source(getExchangeRateRequest.source())
                .target(getExchangeRateRequest.target())
                .rate(rate)
                .reverseRate(reverseRate)
                .time(Instant.now())
                .build();
    }
}
