package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.dto.UserPrice_usdDto;
import com.tolya.cryptocurrencies.models.UserPrice_usd;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public UserPrice_usd mapToPrice_usdEntity(UserPrice_usdDto dto){
      UserPrice_usd entity = new UserPrice_usd();
      entity.setPrice_usd(dto.getPrice_usd());
      entity.setId(dto.getId());
      return entity;
    }
}

