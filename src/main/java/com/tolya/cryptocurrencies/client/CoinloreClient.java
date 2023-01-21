package com.tolya.cryptocurrencies.client;

import com.tolya.cryptocurrencies.dto.UserPrice_usdDto;
import com.tolya.cryptocurrencies.models.Cryptocurrency;
import com.tolya.cryptocurrencies.models.UserPrice_usd;
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

//    public Cryptocurrency getCryptocurrency(String id) {
//        List<Cryptocurrency> cryptocurrenciesServer = new ArrayList<>();
//        String url = "https://api.coinlore.net/api/ticker/?id=%s";
//        url = String.format(url, id);
//        Cryptocurrency[] forObject = restTemplate.getForObject(url, Cryptocurrency[].class);
//        List<Cryptocurrenc  y> cryptocurrencies = Arrays.asList(forObject);
//        Cryptocurrency cryptocurrency = cryptocurrencies.get(0);
//        return cryptocurrency;
//    }

    public List<UserPrice_usd> getUserPrice_usd() {
        String[] cryptocurrenciesId = {"90", "80", "48543"};
        List<UserPrice_usd> userPrice_usdsServer = new ArrayList<>();
        for (String id : cryptocurrenciesId) {
            String url = "https://api.coinlore.net/api/ticker/?id=%s";
            url = String.format(url, id);
            UserPrice_usd[] forObject = restTemplate.getForObject(url, UserPrice_usd[].class);
            List<UserPrice_usdDto> userPrice_usds = Arrays.asList(forObject);
            for (UserPrice_usdDto userPriceDto:userPrice_usds
                 ) {
                userPriceDto.setCryptocurrency(cryptoRepository.findById(id).orElseGet(() -> new Cryptocurrency()));
                UserPrice_usd userPrice_usd = userPrice;
                userPrice_usdsServer.add(userPrice_usd);
            }
            }
        return userPrice_usdsServer;
    }
}
