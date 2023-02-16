package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.client.CoinloreClient;
import com.tolya.cryptocurrencies.models.UserApp;
import com.tolya.cryptocurrencies.models.UserCryptocurrency;
import com.tolya.cryptocurrencies.repositories.UserCryptocurrencyRepository;
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

    @Async
    @Scheduled(fixedRate = 15000)
    void update() {
        Set<String> idCryptocurrencies = new HashSet<>();
        List<UserCryptocurrency> userCryptocurrencies = userCryptocurrencyRepository
                .findAll();
        userCryptocurrencies.forEach(n -> idCryptocurrencies.add(n.getCryptocurrency().getId()));
        log.info("log work");
        for (String id : idCryptocurrencies
        ) {
            List<UserCryptocurrency> userCryptocurrenciesById = userCryptocurrencyRepository
                    .findAllByCryptocurrency_Id(id);
            for (UserCryptocurrency userCryptocurrency : userCryptocurrenciesById
            ) {
                double priceBD = Double.parseDouble(userCryptocurrency
                        .getCryptocurrencyPrice());
                double priceCoinlore = Double.parseDouble(coinloreClient
                        .getCoinloreTickerById(id)
                        .orElseThrow()
                        .getPrice_usd());
                if (priceBD != priceCoinlore) {
                    UserCryptocurrency userCryptocurrencyCoin = MappingUtils.mapToPriceUsdEntity(coinloreClient
                            .getCoinloreTickerById(id)
                            .orElseThrow());
                    userCryptocurrencyCoin.setId(userCryptocurrency.getId());
                    userCryptocurrencyCoin.setCryptocurrency(userCryptocurrency.getCryptocurrency());
                    userCryptocurrencyCoin.setUserApp(userCryptocurrency.getUserApp());
                    userCryptocurrencyRepository
                            .save(userCryptocurrencyCoin);

                if(Math.abs(priceBD-priceCoinlore)>priceBD/100){
                    UserApp userApp = userCryptocurrency.getUserApp();
                    log.warn(""+100*Math.abs(priceBD-priceCoinlore)/priceBD+userApp.getUsername());
                }
                }

            }

        }
    }
}




