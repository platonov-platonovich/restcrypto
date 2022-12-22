package app.dao;

import app.models.Cryptocurrency;

import java.util.List;
public interface CryptoDao {
    List<Cryptocurrency> getAll();


//    boolean equals(List<Cryptocurrency> cryptocurrencies);

    void update();
}
