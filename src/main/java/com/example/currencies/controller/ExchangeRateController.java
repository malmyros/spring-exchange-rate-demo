package com.example.currencies.controller;

import com.example.currencies.dto.GetExchangeRateRequest;
import com.example.currencies.dto.GetExchangeRateResponse;
import com.example.currencies.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/exchange-rates")
@Tag(name = "Exchange Rate", description = "Exchange Rate Controller")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @PostMapping("/calculate")
    @Operation(summary = "Calculates the exchange rate for a given amount")
    public ResponseEntity<GetExchangeRateResponse> calculateExchangeRange(
            @Valid @RequestBody GetExchangeRateRequest getExchangeRateRequest) {

        log.info("Received request to calculate the exchange rate from {} to {}",
                getExchangeRateRequest.source(), getExchangeRateRequest.target());
        return ResponseEntity.ok(exchangeRateService.getExchangeRate(getExchangeRateRequest));
    }
}
