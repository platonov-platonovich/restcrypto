package com.tolya.cryptocurrencies.controllers;


import com.tolya.cryptocurrencies.models.Role;
import com.tolya.cryptocurrencies.models.UserApp;
import com.tolya.cryptocurrencies.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationUserController {
    public RegistrationUserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "RegistrationUserPage.html";
    }

    @PostMapping("/registration")
    public String addUser(UserApp userApp, Model model) {
        userRepo.findByUsername(userApp.getUsername());
        userApp.setActive(true);
        userApp.setRoles(Collections.singleton(Role.USER));
        userRepo.save(userApp);
        return "redirect:/login";
    }
}
