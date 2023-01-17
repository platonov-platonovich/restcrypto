package com.tolya.cryptocurrencies.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Cryptocurrency")
public class Cryptocurrency {
    @Id
    private String id;
    @Column
    private String symbol;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_usd")

    private Price_usd price_usd;

    public Price_usd getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(Price_usd price_usd) {
        this.price_usd = price_usd;
    }

    public Cryptocurrency() {
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public Cryptocurrency(String id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
//    public String toString() {
//        return String.format("\n id: %s \n symbol: %s \n price_usd: %s \n", id, symbol, price_usd);
//    }
}
