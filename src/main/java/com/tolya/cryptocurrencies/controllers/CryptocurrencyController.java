package com.tolya.cryptocurrencies.controllers;

import com.tolya.cryptocurrencies.models.Cryptocurrency;
import com.tolya.cryptocurrencies.repositories.CryptocurrencyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cryptocurrency")
public class CryptocurrencyController {
    @Value("${url.client}")
    private String url;

    private CryptocurrencyRepository cryptocurrencyRepository;

    public CryptocurrencyController(CryptocurrencyRepository cryptocurrencyRepository) {
        this.cryptocurrencyRepository = cryptocurrencyRepository;
    }


    @GetMapping
    public List<Cryptocurrency> getAll() {
        return cryptocurrencyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cryptocurrency> getById(@PathVariable("id") String id) {
        return cryptocurrencyRepository.findById(id);
    }
    @GetMapping("/get")
    public Optional<Cryptocurrency> get(){
        return cryptocurrencyRepository.findById("90");
    }

    @PostMapping("/notify")
}
//userCryptocurency(notify) post new object userCryptorrency



