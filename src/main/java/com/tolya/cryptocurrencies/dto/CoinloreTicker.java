package com.tolya.cryptocurrencies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CoinloreTicker {
   private String id;
   @JsonProperty("price_usd")
   private String priceUsd;
}
