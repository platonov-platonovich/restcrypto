package com.tolya.cryptocurrencies.controllers;

import com.tolya.cryptocurrencies.client.CoinloreClient;
import com.tolya.cryptocurrencies.dto.CryptocurrencyUserRequest;
import com.tolya.cryptocurrencies.models.Cryptocurrency;
import com.tolya.cryptocurrencies.models.UserApp;
import com.tolya.cryptocurrencies.models.UserCryptocurrency;
import com.tolya.cryptocurrencies.repositories.CryptocurrencyRepository;
import com.tolya.cryptocurrencies.repositories.UserCryptocurrencyRepository;
import com.tolya.cryptocurrencies.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/cryptocurrency")
public class CryptocurrencyController {


    private UserCryptocurrencyRepository userCryptocurrencyRepository;
    private CryptocurrencyRepository cryptocurrencyRepository;
    private UserRepository userRepository;
    private CoinloreClient coinloreClient;

    @GetMapping
    public List<Cryptocurrency> getAll() {
        return cryptocurrencyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cryptocurrency> getById(@PathVariable("id") String id) {
        return cryptocurrencyRepository.findById(id);
    }

    @GetMapping("/get")
    public Optional<Cryptocurrency> get() {
        return cryptocurrencyRepository.findById("90");
    }

    @PostMapping("/notify")
    public ResponseEntity<?> notify(@RequestBody CryptocurrencyUserRequest cryptocurrencyUserRequest) {
        UserCryptocurrency userCryptocurrency = new UserCryptocurrency();
        cryptocurrencyRepository
                .findBySymbol(cryptocurrencyUserRequest.getSymbol())
                // read about method reference
                .ifPresent(userCryptocurrency::setCryptocurrency);
        userCryptocurrency.setId(userCryptocurrency.getCryptocurrency().getId());
        cryptocurrencyRepository
                .findBySymbol(cryptocurrencyUserRequest.getSymbol())
                        .ifPresent(n->userCryptocurrency.setCryptocurrencyPrice(coinloreClient.getCoinloreTickerById(userCryptocurrency.getId()).get().getPrice_usd()));
        Optional<UserApp> appUserOptional = userRepository
                .findByUsername(cryptocurrencyUserRequest.getUsername());
        if (!appUserOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }



        userRepository
                .findByUsername(cryptocurrencyUserRequest.getUsername())
                .ifPresent(m-> {
                            if (cryptocurrencyUserRequest.getPassword().equals(m.getPassword())) {
                                userCryptocurrency.setUserApp(userRepository
                                        .findByUsername(cryptocurrencyUserRequest.getUsername()).get());
                            }
                        });
        System.out.println(userCryptocurrency);
        userCryptocurrencyRepository.save(userCryptocurrency);
        return new ResponseEntity <>(HttpStatus.ACCEPTED);

    }
}

//userCryptocurrency(notify) post new object userCryptocurrency



