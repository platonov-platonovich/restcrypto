package com.tolya.cryptocurrencies.client;

import com.tolya.cryptocurrencies.dto.CoinloreTicker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoinloreClient implements CryptocurrencyClient {

    @Value("${coinlore.url}")
    private String coinloreApiUrl;

    private final RestTemplate restTemplate;

    @Override
    public Optional<CoinloreTicker> getCoinloreTickerById(String id) {
        String getByIdUrl = String.format(coinloreApiUrl, id);
        CoinloreTicker[] forObject = restTemplate.getForObject(getByIdUrl, CoinloreTicker[].class);
        return Optional.ofNullable(forObject)
                .flatMap(tickers -> Arrays.stream(tickers)
                        .filter(el -> el.getId().equals(id))
                        .findFirst());

    }
}
junit 5, mockito