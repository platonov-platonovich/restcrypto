package com.tolya.cryptocurrencies.controllers;

import com.tolya.cryptocurrencies.models.Cryptocurrency;
import com.tolya.cryptocurrencies.repositories.CryptoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
public class FirstController {
private  CryptoRepository cryptoRepository;


    @RequestMapping("/cryptocurrencies")
    public List<Cryptocurrency> getAll() {

       return cryptoRepository.findAll();

    }
@RequestMapping("/cryptocurrency/{id}")
public Optional<Cryptocurrency> getById(@PathVariable("id") String id) {
     return  cryptoRepository.findById(id);
}
}



