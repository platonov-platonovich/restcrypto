package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.client.CoinloreClient;
import com.tolya.cryptocurrencies.repositories.CryptocurrencyRepository;
import com.tolya.cryptocurrencies.repositories.UserCryptocurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class ScheduledUpdate {
    private final MappingUtils mappingUtils;
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final UserCryptocurrencyRepository userCryptocurrencyRepository;
    private final CoinloreClient coinloreClient;

    @Async
    @Scheduled(fixedRate = 15000)
    public void update()  {
        Set<String> idCryptocurrencies = new HashSet<>();
        userCryptocurrencyRepository.findAll().forEach(n -> idCryptocurrencies.add(n.getId()));
        System.out.println(idCryptocurrencies);
        for (String s:idCryptocurrencies
             ) {
            if (!userCryptocurrencyRepository.findById(s).get().getCryptocurrencyPrice().equals(coinloreClient.getCoinloreTickerById(s).get().getPrice_usd())){
                userCryptocurrencyRepository.save(mappingUtils.mapToPrice_usdEntity(coinloreClient.getCoinloreTickerById(s).get()));

            }

        }
    }



//        for ()
//        CoinloreTicker userPrice_usdServer = coinloreClient.getCoinloreTickerById();
//        System.out.println(userPrice_usdServer);
//
//        for (CoinloreTicker priceDto : userPrice_usdServer
//        ) {
//            UserCryptocurrency userPrice_usdBD = userCryptocurrencyRepository.findById(priceDto.getId()).orElseThrow(()-> new NoEntityException());
//            if(priceDto.getPrice_usd()!=userPrice_usdBD.getCryptocurrencyPrice()) {
//                userCryptocurrencyRepository.save(mappingUtils.mapToPrice_usdEntity(priceDto));
//            }
//            userPrice_usdServer.stream()
//                    .filter(x-> !(x.getPrice_usd().equals(userCryptocurrencyRepository.findById(x.getId()))))
//                    .forEach(n->userCryptocurrencyRepository.save((mappingUtils.mapToPrice_usdEntity(n))));
//        }
}


