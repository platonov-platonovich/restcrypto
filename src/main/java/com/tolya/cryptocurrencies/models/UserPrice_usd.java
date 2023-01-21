package com.tolya.cryptocurrencies.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "userPrice_usd")
public class UserPrice_usd implements Serializable {
    public UserPrice_usd(String price_usd, String id) {
        this.price_usd = price_usd;
        this.id = id;

    }

    private String price_usd;
    @Id
    String id;



    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Cryptocurrency cryptocurrency;

    public UserPrice_usd() {

    }

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }
}
