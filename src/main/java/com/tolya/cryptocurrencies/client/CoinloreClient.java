package com.tolya.cryptocurrencies.client;

import com.tolya.cryptocurrencies.models.Cryptocurrency;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CoinloreClient {
    public CoinloreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate;

//    public Cryptocurrency getCryptocurrency(String id) {
//        List<Cryptocurrency> cryptocurrenciesServer = new ArrayList<>();
//        String url = "https://api.coinlore.net/api/ticker/?id=%s";
//        url = String.format(url, id);
//        Cryptocurrency[] forObject = restTemplate.getForObject(url, Cryptocurrency[].class);
//        List<Cryptocurrenc  y> cryptocurrencies = Arrays.asList(forObject);
//        Cryptocurrency cryptocurrency = cryptocurrencies.get(0);
//        return cryptocurrency;
//    }

    public List<Cryptocurrency> getCryptocurrencies() {
        String[] cryptocurrenciesId = {"90", "80", "48543"};
        List<Cryptocurrency> cryptocurrenciesServer = new ArrayList<>();
        for (String id : cryptocurrenciesId) {
            String url = "https://api.coinlore.net/api/ticker/?id=%s";
            url = String.format(url, id);
            Cryptocurrency[] forObject = restTemplate.getForObject(url, Cryptocurrency[].class);
            List<Cryptocurrency> cryptocurrencies = Arrays.asList(forObject);
            for (Cryptocurrency cryptocurrency : cryptocurrencies) {
                if (cryptocurrency.getId().equals(id)) {
                    cryptocurrenciesServer.add(cryptocurrency);
                }
            }
        }
        return cryptocurrenciesServer;
    }
}
