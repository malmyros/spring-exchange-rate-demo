package com.example.currencies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public record GetExchangeRateResponse(

        @NotBlank(message = "Source currency cannot be empty")
        @Size(min = 3, max = 3, message = "Source needs to be a valid Alpha3 currency code")
        @Schema(description = "The source currency to convert the funds from", example = "GBP")
        String source,

        @NotBlank(message = "Target currency cannot be empty")
        @Size(min = 3, max = 3, message = "Target needs to be a valid Alpha3 currency code")
        @Schema(description = "The target currency to convert the funds to", example = "EUR")
        String target,

        @NotNull(message = "Rate cannot be empty")
        @Schema(description = "The calculated exchange rate", example = "1.10")
        BigDecimal rate,

        @NotNull(message = "Reverse rate cannot be empty")
        @Schema(description = "The calculated reverse rate", example = "1.10")
        BigDecimal reverseRate,

        @NotNull
        @Schema(description = "The time the exchange rate was calculated")
        Instant time
) {
}
