package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.client.CoinloreClient;
import com.tolya.cryptocurrencies.models.UserCryptocurrency;
import com.tolya.cryptocurrencies.repositories.UserCryptocurrencyRepository;
import com.tolya.cryptocurrencies.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@Component
@AllArgsConstructor
public class ScheduledUpdate {
    private final UserCryptocurrencyRepository userCryptocurrencyRepository;
    private final CoinloreClient coinloreClient;
    private final UserRepository userRepository;

    @Async
    @Scheduled(fixedRate = 15000)
    void update() {
        Set<String> idCryptocurrencies = new HashSet<>();
        List<UserCryptocurrency> userCryptocurrencies = userCryptocurrencyRepository
                .findAll();
        userCryptocurrencies.forEach(n -> idCryptocurrencies.add(n.getId()));

        for (String id : idCryptocurrencies
        ) {
            UserCryptocurrency userCryptocurrency = userCryptocurrencyRepository
                    .findById(id)
                    .orElseThrow();
            double priceBD = Double.parseDouble(userCryptocurrency
                    .getCryptocurrencyPrice());
            double priceCoinlore = Double.parseDouble(coinloreClient
                    .getCoinloreTickerById(id)
                    .orElseThrow()
                    .getPrice_usd());
            if (priceBD != priceCoinlore) {
                userCryptocurrencyRepository
                        .save(MappingUtils.mapToPriceUsdEntity(
                                coinloreClient
                                        .getCoinloreTickerById(id)
                                        .orElseThrow()));

//                if(Math.abs(priceBD-priceCoinlore)>priceBD/10){
//                    log.warn(""+100*Math.abs(priceBD-priceCoinlore)/priceBD+userRepository.g);
//                }
            }
        }
    }
}




