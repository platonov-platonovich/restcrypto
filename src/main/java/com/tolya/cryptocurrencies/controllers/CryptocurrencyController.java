package com.tolya.cryptocurrencies.controllers;

import com.tolya.cryptocurrencies.dto.CryptocurrencyUserRequest;
import com.tolya.cryptocurrencies.models.Cryptocurrency;
import com.tolya.cryptocurrencies.models.UserCryptocurrency;
import com.tolya.cryptocurrencies.repositories.CryptocurrencyRepository;
import com.tolya.cryptocurrencies.repositories.UserCryptocurrencyRepository;
import com.tolya.cryptocurrencies.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/cryptocurrency")
public class CryptocurrencyController {
    @Value("${url.client}")
    private UserCryptocurrencyRepository userCryptocurrencyRepository;
    private CryptocurrencyRepository cryptocurrencyRepository;
    private UserRepository userRepository;

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
    public void notify(@RequestBody CryptocurrencyUserRequest cryptocurrencyUserRequest) {
        UserCryptocurrency userCryptocurrency = new UserCryptocurrency();
        cryptocurrencyRepository
                .findBySymbol(cryptocurrencyUserRequest.getSymbol())
                .ifPresent(n -> userCryptocurrency.setCryptocurrency(n));
        userRepository
                .findByUsername(cryptocurrencyUserRequest.getUserName())
                .ifPresent(n->userCryptocurrency.setUserApp(List.of(n)));
        userCryptocurrencyRepository.save(userCryptocurrency);
    }
}
//userCryptocurency(notify) post new object userCryptorrency



