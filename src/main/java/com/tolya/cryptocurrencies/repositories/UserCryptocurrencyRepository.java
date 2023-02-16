package com.tolya.cryptocurrencies.repositories;

import com.tolya.cryptocurrencies.models.UserCryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCryptocurrencyRepository extends JpaRepository<UserCryptocurrency, String> {
    @Override
    UserCryptocurrency save(UserCryptocurrency entity);

    @Override
    Optional<UserCryptocurrency> findById(String s);

    @Override
    List<UserCryptocurrency> findAll();
    List<UserCryptocurrency> findAllByCryptocurrency_Id(String id);
}
