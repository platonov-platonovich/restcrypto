package com.tolya.cryptocurrencies.repositories;

import com.tolya.cryptocurrencies.models.Cryptocurrency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CryptocurrencyRepository extends CrudRepository<Cryptocurrency, String> {

 @Override
List<Cryptocurrency> findAll();

 @Override
 Optional<Cryptocurrency> findById(String s);

 @Override
 Cryptocurrency save(Cryptocurrency entity);
}
