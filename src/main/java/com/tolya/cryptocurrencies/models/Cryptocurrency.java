package com.tolya.cryptocurrencies.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Cryptocurrency")
public class Cryptocurrency {
    @Id
    private String id;
    @Column
    private String price_usd;
    @Column
    private String symbol;

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_usd() {
        return price_usd;
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

    public Cryptocurrency(String id, String price_usd, String symbol) {
        this.id = id;
        this.price_usd = price_usd;
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
