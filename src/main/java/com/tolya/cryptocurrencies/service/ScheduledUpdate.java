package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.client.CoinloreClient;
import com.tolya.cryptocurrencies.models.Cryptocurrency;
import com.tolya.cryptocurrencies.repositories.CryptoRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ScheduledUpdate {
    private final CryptoRepository cryptoRepository;
    private final CoinloreClient coinloreClient;

    @Async
    @Scheduled(fixedRate = 15000)
    public void update() {
        List<Cryptocurrency> cryptocurrenciesBD = cryptoRepository.findAll();
        List<Cryptocurrency> cryptocurrenciesServer = coinloreClient.getCryptocurrencies();
        for (Cryptocurrency cryptocurrencyBD : cryptocurrenciesBD) {
            for (Cryptocurrency cryptocurrencyServer : cryptocurrenciesServer) {
                if (cryptocurrencyServer.getId().equals(cryptocurrencyBD.getId())) {
                    if (!(cryptocurrencyBD.getPrice_usd().equals(cryptocurrencyServer.getPrice_usd()))) {
                        cryptocurrencyBD.setPrice_usd(cryptocurrencyServer.getPrice_usd());
                    }
                }
            }
        }
    }
}
