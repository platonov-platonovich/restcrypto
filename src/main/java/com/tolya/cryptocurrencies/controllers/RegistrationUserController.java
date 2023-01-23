package com.tolya.cryptocurrencies.controllers;


import com.tolya.cryptocurrencies.dto.RegisterUserRequest;
import com.tolya.cryptocurrencies.models.Role;
import com.tolya.cryptocurrencies.models.User;
import com.tolya.cryptocurrencies.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
@AllArgsConstructor
@Controller
@RequestMapping("/registration")
public class RegistrationUserController {
    private UserRepo userRepo;

    @GetMapping
    public String registration() {
        return "RegistrationUserPage.html";
    }

    @PostMapping("/register")
    public ResponseEntity <String> register(RegisterUserRequest registerUserRequest){
        // check if user exists -> return 400 HTTP if user exists already
        User user = new User();
        user.setUsername(registerUserRequest.getUserName());
        user.setPassword(registerUserRequest.getPassword());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return ResponseEntity.ok("ok");
    }
    @PostMapping
    public String addUser(User user, Model model) {
        userRepo.findByUsername(user.getUsername());
        return "redirect:/login";
    }
}
