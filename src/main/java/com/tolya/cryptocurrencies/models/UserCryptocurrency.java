package com.tolya.cryptocurrencies.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class UserCryptocurrency {

    @Id
    private String id;

    private String  cryptocurrencyPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private Cryptocurrency cryptocurrency;
}

