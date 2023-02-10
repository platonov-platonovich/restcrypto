package com.tolya.cryptocurrencies.repositories;

import com.tolya.cryptocurrencies.models.UserCryptocurrency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserCryptocurrencyRepository extends CrudRepository <UserCryptocurrency, String>{
    @Override
    UserCryptocurrency save(UserCryptocurrency entity);

    @Override
    Optional<UserCryptocurrency> findById(String s);

    @Override
    List<UserCryptocurrency> findAll();
}
