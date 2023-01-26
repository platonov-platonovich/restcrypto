package com.tolya.cryptocurrencies.controllers;


import com.tolya.cryptocurrencies.dto.RegisterUserRequest;
import com.tolya.cryptocurrencies.models.Role;
import com.tolya.cryptocurrencies.models.UserApp;
import com.tolya.cryptocurrencies.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
@AllArgsConstructor
@Controller
public class RegistrationUserController {
    private UserRepo userRepo;

    @GetMapping
    public String registration() {
        return "RegistrationUserPage.html";
    }

    @PostMapping("/registration")
    public ResponseEntity <String> register(RegisterUserRequest registerUserRequest){
        // check if user exists -> return 400 HTTP if user exists already
        UserApp userApp = new UserApp();
        userApp.setUsername(registerUserRequest.getUserName());
        userApp.setPassword(registerUserRequest.getPassword());
        userApp.setActive(true);
        userApp.setRoles(Collections.singleton(Role.USER));
        userRepo.save(userApp);
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/login")
    public String login(UserApp userApp, Model model) {
        userRepo.findByUsername(userApp.getUsername());
        return "redirect:/login";
    }
}
