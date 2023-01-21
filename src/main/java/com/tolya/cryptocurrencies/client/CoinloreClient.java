package com.tolya.cryptocurrencies.client;

import com.tolya.cryptocurrencies.dto.UserPrice_usdDto;
import com.tolya.cryptocurrencies.repositories.CryptoRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CoinloreClient {


    private RestTemplate restTemplate;

    public CoinloreClient(RestTemplate restTemplate, CryptoRepository cryptoRepository) {
        this.restTemplate = restTemplate;
        this.cryptoRepository = cryptoRepository;
    }

    private CryptoRepository cryptoRepository;

    public List<UserPrice_usdDto> getUserPrice_usdDto() {
        String[] cryptocurrenciesId = {"90", "80", "48543"};
        List<UserPrice_usdDto> userPrice_usdsServer = new ArrayList<>();
        for (String id : cryptocurrenciesId) {
            String url = "https://api.coinlore.net/api/ticker/?id=%s";
            url = String.format(url, id);
            UserPrice_usdDto[] forObject = restTemplate.getForObject(url, UserPrice_usdDto[].class);
            List<UserPrice_usdDto> userPrice_usds = Arrays.asList(forObject);
            for (UserPrice_usdDto userPriceDto : userPrice_usds
            ) {
                userPrice_usdsServer.add(userPriceDto);
            }
        }
        return userPrice_usdsServer;
    }
}
