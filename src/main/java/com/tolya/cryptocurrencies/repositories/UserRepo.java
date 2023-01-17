package com.tolya.cryptocurrencies.repositories;


import com.tolya.cryptocurrencies.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserApp, Integer> {
    UserApp findByUsername(String username);

    UserApp findById(int id);
}
