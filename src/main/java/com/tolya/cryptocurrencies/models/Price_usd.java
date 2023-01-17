package com.tolya.cryptocurrencies.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Price_usd")
public class Price_usd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    private String price_usd;

    public Price_usd(String price_usd, Cryptocurrency cryptocurrency) {
        this.price_usd = price_usd;
        this.cryptocurrency = cryptocurrency;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cryptocurrency_id")
    private Cryptocurrency cryptocurrency;

    public Price_usd() {

    }
}
