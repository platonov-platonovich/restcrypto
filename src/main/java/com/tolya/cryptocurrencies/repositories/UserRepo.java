package com.tolya.cryptocurrencies.repositories;


import com.tolya.cryptocurrencies.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserApp, Integer> {
    Optional <UserApp> findByUsername(String username);

    UserApp findById(int id);
}
