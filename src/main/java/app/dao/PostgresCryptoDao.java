package app.dao;

import app.models.Cryptocurrency;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PostgresCryptoDao implements CryptoDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/crypto";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty";
    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Cryptocurrency> getAll() {
        List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM cryptocurrency";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Cryptocurrency cryptocurrency = new Cryptocurrency();
                cryptocurrency.setId(resultSet.getString("id"));
                cryptocurrency.setSymbol(resultSet.getString("symbol"));
                cryptocurrencies.add(cryptocurrency);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cryptocurrencies;
    }


    public List<Cryptocurrency> getRestTemplate() {
        List<Cryptocurrency> cryptocurrencies1 = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String[] cryptocurrenciesId = {"90", "80", "48543"};
        for (String i:cryptocurrenciesId) {
            String url = "https://api.coinlore.net/api/ticker/?id=%s";
            url = String.format(url, i);
            Cryptocurrency myCryptocurrency = null;
            Cryptocurrency[] forObject = restTemplate.getForObject(url, Cryptocurrency[].class);
            List<Cryptocurrency> cryptocurrencies = Arrays.asList(forObject);
            for (Cryptocurrency cryptocurrency : cryptocurrencies) {
                if (cryptocurrency.getId().equals(i)) {
                     cryptocurrencies1.add(cryptocurrency);
                }
            }
        }
        return cryptocurrencies1;
    }

}
//    @Override
//    public void update() {
//        try {
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//}


