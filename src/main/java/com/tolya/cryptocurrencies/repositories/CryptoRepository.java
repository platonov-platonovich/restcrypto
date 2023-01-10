package com.tolya.cryptocurrencies.repositories;

import com.tolya.cryptocurrencies.models.Cryptocurrency;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CryptoRepository extends CrudRepository<Cryptocurrency, String> {

 @Override
List<Cryptocurrency> findAll();


 @Override
 Cryptocurrency save(Cryptocurrency entity);
}
