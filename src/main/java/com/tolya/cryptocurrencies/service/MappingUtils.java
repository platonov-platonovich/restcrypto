package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.dto.CoinloreTicker;
import com.tolya.cryptocurrencies.models.UserCryptocurrency;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public static UserCryptocurrency mapToPriceUsdEntity(CoinloreTicker dto){
      UserCryptocurrency entity = new UserCryptocurrency();
      entity.setCryptocurrencyPrice(dto.getPrice_usd());
      entity.setId(dto.getId());
      return entity;
    }
}

