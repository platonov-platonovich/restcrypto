package app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cryptocurrency {
    private int id;
    private int price;
    private String symbol;

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
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

    public Cryptocurrency(int id, int price, String symbol) {
        this.id = id;
        this.price = price;
        this.symbol = symbol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
