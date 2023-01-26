package com.tolya.cryptocurrencies.client;

import com.tolya.cryptocurrencies.dto.CoinloreTicker;
import com.tolya.cryptocurrencies.repositories.CryptoRepository;import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Component

public class CoinloreClient implements CryptocurrencyClient {
    @Value("${url.client}")
    private String url;
    private RestTemplate restTemplate;
    private CryptoRepository cryptoRepository;

    public CoinloreClient(RestTemplate restTemplate, CryptoRepository cryptoRepository) {
        this.restTemplate = restTemplate;
        this.cryptoRepository = cryptoRepository;
    }

    public CoinloreClient() {
    }

    @Override
    public CoinloreTicker getCoinloreTicker(String id) {
        //externalise url to application properties(@value)
        url = String.format(url, id);
        CoinloreTicker[] forObject = restTemplate.getForObject(url, CoinloreTicker[].class);
        for (CoinloreTicker userPriceDto : forObject) {
            if (userPriceDto.getId().equals(id)) {
                return userPriceDto;
            }
        }
        return null;
    }

    public Optional<CoinloreTicker> getCoinloreTickerOp(String id) {
        //externalise url to application properties(@value)
        url = String.format(url, id);
        CoinloreTicker[] forObject = restTemplate.getForObject(url, CoinloreTicker[].class);

        return Arrays.stream(forObject)
                .filter(el->el.getId().equals(id))
                .findFirst();
    }
}
