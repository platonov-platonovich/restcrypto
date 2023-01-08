package app.service;

import app.models.Cryptocurrency;
import app.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component

public class WorkWithApi {
    @Autowired
    private CryptoRepository cryptoRepository;



    public List<Cryptocurrency> getActualCryptocurrencies() {
        RestTemplate restTemplate = new RestTemplate();
        String[] cryptocurrenciesId = {"90", "80", "48543"};
        List<Cryptocurrency> cryptocurrenciesServer = new ArrayList<>();
        for (String id : cryptocurrenciesId) {
            String url = "https://api.coinlore.net/api/ticker/?id=%s";
            url = String.format(url, id);
            Cryptocurrency[] forObject = restTemplate.getForObject(url, Cryptocurrency[].class);
            List<Cryptocurrency> cryptocurrencies = Arrays.asList(forObject);
            for (Cryptocurrency cryptocurrency : cryptocurrencies) {
                if (cryptocurrency.getId().equals(id)) {
                    cryptocurrenciesServer.add(cryptocurrency);
                }
            }
        }
        return cryptocurrenciesServer;
    }

    public boolean comparison() {

        boolean result = true;
        List<Cryptocurrency> cryptocurrenciesBD = cryptoRepository.findAll();
        List<Cryptocurrency> cryptocurrenciesServer = getActualCryptocurrencies();
        for (Cryptocurrency cryptocurrencyBD : cryptocurrenciesBD) {
            for (Cryptocurrency cryptocurrencyServer : cryptocurrenciesServer) {
                if (cryptocurrencyServer.getId().equals(cryptocurrencyBD.getId())) {
                    if (!(cryptocurrencyBD.getPrice_usd().equals(cryptocurrencyServer.getPrice_usd()))) {
                        result= false;
                    }

                }
            }

        }
        return result;
    }


    public void update() {
        if (comparison()) {
            RestTemplate restTemplate = new RestTemplate();
            String[] cryptocurrenciesId = {"90", "80", "48543"};
            for (String id : cryptocurrenciesId) {
                String url = "https://api.coinlore.net/api/ticker/?id=%s";
                url = String.format(url, id);
                Cryptocurrency[] forObject = restTemplate.getForObject(url, Cryptocurrency[].class);
                List<Cryptocurrency> cryptocurrencies = Arrays.asList(forObject);
                for (Cryptocurrency cryptocurrency : cryptocurrencies) {
                    if (cryptocurrency.getId().equals(id)) {
                       cryptoRepository.save(cryptocurrency);
                    }
                }
            }
        }
    }
}
