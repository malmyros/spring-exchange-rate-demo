package com.example.currencies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GetExchangeRateRequest(

        @NotBlank(message = "Source currency cannot be empty")
        @Size(min = 3, max = 3, message = "Source needs to be a valid Alpha3 currency code")
        @Schema(description = "The source currency to convert the funds from", example = "GBP")
        String source,

        @NotBlank(message = "Target currency cannot be empty")
        @Size(min = 3, max = 3, message = "Target needs to be a valid Alpha3 currency code")
        @Schema(description = "The target currency to convert the funds to", example = "EUR")
        String target,

        @NotNull(message = "Fixed side cannot be empty")
        @Schema(description = "Defines whether the customer is buy or selling", example = "BUY")
        FixedSide fixedSide,

        @NotNull
        @Schema(name = "The amount to calculate the exchange rate", example = "10.00")
        BigDecimal amount
) {
}
