package com.tolya.cryptocurrencies.client;

import com.tolya.cryptocurrencies.dto.CoinloreTicker;

public interface CryptocurrencyClient {

    CoinloreTicker getCoinloreTicker(String id);
}
