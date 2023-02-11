package com.tolya.cryptocurrencies.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn
    private Cryptocurrency cryptocurrency;
    @OneToMany
    @JoinColumn(referencedColumnName = "userName")
    private List<UserApp> userApp;
}

