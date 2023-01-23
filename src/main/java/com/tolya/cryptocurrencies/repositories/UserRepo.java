package com.tolya.cryptocurrencies.repositories;


import com.tolya.cryptocurrencies.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findById(int id);
}
