package com.tolya.cryptocurrencies.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table
public class UserCryptocurrency {

    @Id
    private String id;
    private String cryptocurrencyPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn
    private Cryptocurrency cryptocurrency;
    @OneToOne
    @JoinColumn(name = "userApp_id")
    private UserApp userApp;
}

