package com.tolya.cryptocurrencies.client;

import com.tolya.cryptocurrencies.dto.CoinloreTicker;

import java.util.Optional;

public interface CryptocurrencyClient {


    Optional<CoinloreTicker> getCoinloreTickerById(String id);
}
