package com.tolya.cryptocurrencies.repositories;

import com.tolya.cryptocurrencies.models.UserPrice_usd;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserPriceRepository extends CrudRepository <UserPrice_usd, String>{
    @Override
    UserPrice_usd save(UserPrice_usd entity);

    @Override
    Optional<UserPrice_usd> findById(String s);

    @Override
    List<UserPrice_usd> findAll();
}
