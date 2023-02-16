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
    public List<UserCryptocurrency> getAll() {
        return userCryptocurrencyRepository.findAllByCryptocurrency_Id("80");
    }

    @GetMapping("/{id}")
    public Optional<Cryptocurrency> getById(@PathVariable("id") String id) {
        return cryptocurrencyRepository.findById(id);
    }


    @PostMapping("/notify")
    public ResponseEntity<?> notify(@RequestBody CryptocurrencyUserRequest cryptocurrencyUserRequest) {
        UserCryptocurrency userCryptocurrency = new UserCryptocurrency();
        Optional<Cryptocurrency> cryptocurrencyOptional = cryptocurrencyRepository
                .findBySymbol(cryptocurrencyUserRequest.getSymbol());

        userCryptocurrency
                .setCryptocurrency(
                        cryptocurrencyOptional
                                .orElseThrow());

        Optional<UserApp> appUserOptional = userRepository
                .findByUsername(cryptocurrencyUserRequest.getUserName());
        appUserOptional.ifPresent(n -> {
            userCryptocurrency
                    .setCryptocurrencyPrice(
                            coinloreClient
                                    .getCoinloreTickerById(userCryptocurrency.getCryptocurrency().getId())
                                    .orElseThrow()
                                    .getPrice_usd());

            if (
                    cryptocurrencyUserRequest
                            .getPassword()
                            .equals(n.getPassword())) {
                userCryptocurrency
                        .setUserApp(n);
            }
        });
        if (!appUserOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userCryptocurrencyRepository.save(userCryptocurrency);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}

//userCryptocurrency(notify) post new object userCryptocurrency

// read about method reference


