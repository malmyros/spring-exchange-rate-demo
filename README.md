# Spring Exchange Rate

This demo was created to investigate how exchange rate 
calculating is working for buying and selling currencies.

## Functionality

The service exposes a simple endpoint to calculate the
exchange range for a given source and target currency pair.

```
curl --location 'http://localhost:8080/api/v1/exchange-rates/calculate' \
--header 'Content-Type: application/json' \
--data '{
    "amount": 10.00,
    "fixedSide": "BUY",
    "source": "GBP",
    "target": "EUR"
}'
```

The user can specify whether he is buying or selling by 
specifying a fixed side.

If the currency pair doesn't exist in the local cache
then the rate would be zero.