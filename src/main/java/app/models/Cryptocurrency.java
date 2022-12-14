package app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cryptocurrency {
    private int id;
    private int price_usd;
    private String symbol;

    public void setPrice_usd(int price_usd) {
        this.price_usd = price_usd;
    }

    public int getPrice_usd() {
        return price_usd;
    }

    public Cryptocurrency() {
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public Cryptocurrency(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public Cryptocurrency(int id, int price_usd, String symbol) {
        this.id = id;
        this.price_usd = price_usd;
        this.symbol = symbol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
