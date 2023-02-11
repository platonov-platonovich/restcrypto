package com.tolya.cryptocurrencies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CryptocurrencyUserRequest {
    private String userName;
    private String symbol;
}
