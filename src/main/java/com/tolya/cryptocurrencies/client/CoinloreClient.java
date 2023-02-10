package com.tolya.cryptocurrencies.client;

import com.tolya.cryptocurrencies.dto.CoinloreTicker;
import com.tolya.cryptocurrencies.repositories.CryptocurrencyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;


@Component
public class CoinloreClient implements CryptocurrencyClient {

    @Value("${url.client}")
    private String url;

    public CoinloreClient(RestTemplate restTemplate, CryptocurrencyRepository cryptocurrencyRepository) {
        this.restTemplate = restTemplate;
        this.cryptocurrencyRepository = cryptocurrencyRepository;
    }

    private RestTemplate restTemplate;
    private CryptocurrencyRepository cryptocurrencyRepository;

    @Override
    public Optional<CoinloreTicker> getCoinloreTickerById(String id) {
        //externalise url to application properties(@value)
        String getByIdUrl = String.format(url, id);
        CoinloreTicker[] forObject = restTemplate.getForObject(getByIdUrl, CoinloreTicker[].class);
        return Optional.ofNullable(forObject)
                .flatMap(tickers -> Arrays.stream(tickers)
                        .filter(el -> el.getId().equals(id))
                        .findFirst());

    }
}
