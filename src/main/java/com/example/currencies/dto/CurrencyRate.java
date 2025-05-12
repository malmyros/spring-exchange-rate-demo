package com.example.currencies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.example.currencies.utils.ExchangeRateUtils.CURRENCY_RATE_SCALE;

@Builder
public record CurrencyRate(

        @Schema(description = "The buy or bid SPOT rate")
        BigDecimal buyRate,

        @Schema(description = "The sell or ask SPOT rate")
        BigDecimal sellRate) {

        public BigDecimal getRoundedRate(FixedSide fixedSide) {
                BigDecimal rate = fixedSide.isBuying() ? buyRate : sellRate;
                return rate.setScale(CURRENCY_RATE_SCALE, RoundingMode.HALF_UP);
        }
}
