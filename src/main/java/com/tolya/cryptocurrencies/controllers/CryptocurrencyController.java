package com.tolya.cryptocurrencies.controllers;

import com.tolya.cryptocurrencies.models.Cryptocurrency;
import com.tolya.cryptocurrencies.repositories.CryptoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/cryptocurrency")
public class CryptocurrencyController {
    @Value("${url.client}")
    private String url;

    private CryptoRepository cryptoRepository;

    @GetMapping
    public List<Cryptocurrency> getAll() {
        return cryptoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cryptocurrency> getById(@PathVariable("id") String id) {
        return cryptoRepository.findById(id);
    }
    @GetMapping("/get")
    public String get(){
        return url;
    }
}



