package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.client.CoinloreClient;
import com.tolya.cryptocurrencies.models.UserPrice_usd;
import com.tolya.cryptocurrencies.repositories.CryptoRepository;
import com.tolya.cryptocurrencies.repositories.UserPriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ScheduledUpdate {
    private final CryptoRepository cryptoRepository;
    private final UserPriceRepository userPriceRepository;
    private final CoinloreClient coinloreClient;

    @Async
    @Scheduled(fixedRate = 15000)
    public void update() {
        List<UserPrice_usd> userPrice_usdsServer = coinloreClient.getUserPrice_usd();
        List<UserPrice_usd> userPrice_usdsBD = userPriceRepository.findAll();
        for (UserPrice_usd user : userPrice_usdsServer
        ) {userPriceRepository.save(user);

        }


//        if (!userPrice_usdsBD.equals(userPrice_usdsServer)) {
//            for (UserPrice_usd userPrice_usd : userPrice_usdsServer
//            ) {
//                userPriceRepository.save(userPrice_usd);
//            }

//        }
    }
}

