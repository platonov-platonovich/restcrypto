package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.client.CoinloreClient;
import com.tolya.cryptocurrencies.dto.UserPrice_usdDto;
import com.tolya.cryptocurrencies.models.UserPrice_usd;
import com.tolya.cryptocurrencies.myException.NoEntityException;
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
    private  final  MappingUtils mappingUtils;
    private final CryptoRepository cryptoRepository;
    private final UserPriceRepository userPriceRepository;
    private final CoinloreClient coinloreClient;

    @Async
    @Scheduled(fixedRate = 15000)
    public void update() throws NoEntityException {
        List<UserPrice_usdDto> userPrice_usdsServer = coinloreClient.getUserPrice_usdDto();

        for (UserPrice_usdDto priceDto : userPrice_usdsServer
        ) {
            UserPrice_usd userPrice_usdBD = userPriceRepository.findById(priceDto.getId()).orElseThrow(()-> new NoEntityException());
            if(priceDto.getPrice_usd()!=userPrice_usdBD.getPrice_usd()) {
                userPriceRepository.save(mappingUtils.mapToPrice_usdEntity(priceDto));
            }
        }
    }
}

