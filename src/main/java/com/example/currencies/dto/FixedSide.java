package com.example.currencies.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "The fixed side of the currency exchange.")
public enum FixedSide {

    @Schema(description = "The amount is how much currency is being bought")
    BUY,

    @Schema(description = "The amount is how much currency is being sold")
    SELL;

    public boolean isBuying() {
        return this == BUY;
    }

    public boolean isSelling() {
        return this == SELL;
    }
}
