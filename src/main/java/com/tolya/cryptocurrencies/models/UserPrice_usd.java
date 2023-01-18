package com.tolya.cryptocurrencies.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "userPrice_usd")
public class UserPrice_usd implements Serializable {
    private String price_usd;
    @Id
    String id;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Cryptocurrency cryptocurrency;

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }
    @OneToMany(mappedBy = "userPrice_usd")
    private Set<UserApp> userApp;

    public UserPrice_usd() {
    }
}
