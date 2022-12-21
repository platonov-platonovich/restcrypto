package app.dao;

import app.models.Cryptocurrency;

import java.util.List;
public interface CryptoDao {
    List<Cryptocurrency> getAll();


    void update();
}
