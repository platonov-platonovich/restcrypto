package com.tolya.cryptocurrencies.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
    private String userName;
    private String password;
}
