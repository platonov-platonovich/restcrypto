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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String cryptocurrencyPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Cryptocurrency cryptocurrency;
    @OneToOne
    @JoinColumn(name = "userId")
    private UserApp userApp;
}

