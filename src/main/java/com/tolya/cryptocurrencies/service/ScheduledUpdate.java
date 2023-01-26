//package com.tolya.cryptocurrencies.service;
//
//import com.tolya.cryptocurrencies.client.CoinloreClient;
//import com.tolya.cryptocurrencies.dto.CoinloreTicker;
//import com.tolya.cryptocurrencies.models.UserCryptocurrency;
//import com.tolya.cryptocurrencies.myException.NoEntityException;
//import com.tolya.cryptocurrencies.repositories.CryptoRepository;
//import com.tolya.cryptocurrencies.repositories.UserPriceRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@AllArgsConstructor
//public class ScheduledUpdate {
//    private  final  MappingUtils mappingUtils;
//    private final CryptoRepository cryptoRepository;
//    private final UserPriceRepository userPriceRepository;
//    private final CoinloreClient coinloreClient;
//
//    @Async
//    @Scheduled(fixedRate = 15000)
//    public void update() throws NoEntityException {
//        List<CoinloreTicker> userPrice_usdsServer = coinloreClient.getCoinloreTicker("90");
//
//        for (CoinloreTicker priceDto : userPrice_usdsServer
//        ) {
//            UserCryptocurrency userPrice_usdBD = userPriceRepository.findById(priceDto.getId()).orElseThrow(()-> new NoEntityException());
//            if(priceDto.getPrice_usd()!=userPrice_usdBD.getCryptocurrencyPrice()) {
//                userPriceRepository.save(mappingUtils.mapToPrice_usdEntity(priceDto));
//            }
//        }
//    }
//}
//
