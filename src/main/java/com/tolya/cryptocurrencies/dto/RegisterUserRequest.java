package com.tolya.cryptocurrencies.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterUserRequest {
    private String userName;
    private String password;
}
