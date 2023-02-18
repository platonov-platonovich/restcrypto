package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.client.CoinloreClient;
import com.tolya.cryptocurrencies.dto.CoinloreTicker;
import com.tolya.cryptocurrencies.models.Cryptocurrency;
import com.tolya.cryptocurrencies.models.UserApp;
import com.tolya.cryptocurrencies.models.UserCryptocurrency;
import com.tolya.cryptocurrencies.repositories.UserCryptocurrencyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@AllArgsConstructor
public class ScheduledUpdate {

    private final UserCryptocurrencyRepository userCryptocurrencyRepository;
    private final CoinloreClient coinloreClient;

    @Async
    @Scheduled(fixedRate = 15000)
    void update() {
        List<UserCryptocurrency> userCryptocurrencies = userCryptocurrencyRepository.findAll();

        userCryptocurrencyRepository.findAll()
                .stream()
                .map(UserCryptocurrency::getCryptocurrency)
                .map(Cryptocurrency::getId)
                .distinct()
                .map(coinloreClient::getCoinloreTickerById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(coinloreTicker -> updatePriceForAllUsers(userCryptocurrencies, coinloreTicker));
    }

    private void updatePriceForAllUsers(List<UserCryptocurrency> userCryptocurrencies, CoinloreTicker coinloreTicker) {
        userCryptocurrencies
                .stream()
                .filter(userCryptocurrency -> userCryptocurrency.getCryptocurrency().getId().equals(coinloreTicker.getId()))
                .filter(userCryptocurrency -> !userCryptocurrency.getCryptocurrencyPrice().equals(coinloreTicker.getPriceUsd()))
                .forEach(userCryptocurrency -> {
                    userCryptocurrency.setCryptocurrencyPrice(coinloreTicker.getPriceUsd());
                    userCryptocurrencyRepository.save(userCryptocurrency);
                    logPriceChange(coinloreTicker, userCryptocurrency);
                });
    }

    private void logPriceChange(CoinloreTicker coinloreTicker, UserCryptocurrency userCryptocurrency) {
        double priceBD = Double.parseDouble(userCryptocurrency
                .getCryptocurrencyPrice());
        double priceCoinloreById = Double.parseDouble(coinloreTicker.getPriceUsd());
        int differencePercentage = (int) (100 * (Math.abs(priceBD - priceCoinloreById) / priceBD));
        if (differencePercentage > 1) {
            UserApp userApp = userCryptocurrency.getUserApp();
            log.warn("user: {}, cryptocurrency id: {}, differences: {}", userApp.getUsername(), coinloreTicker.getId(), differencePercentage);
        }
    }
}




