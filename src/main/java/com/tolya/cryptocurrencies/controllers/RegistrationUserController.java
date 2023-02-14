package com.tolya.cryptocurrencies.controllers;


import com.tolya.cryptocurrencies.dto.RegisterUserRequest;
import com.tolya.cryptocurrencies.models.UserApp;
import com.tolya.cryptocurrencies.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Controller
@Log4j2
public class RegistrationUserController {
    private UserRepository userRepository;

    @GetMapping
    public String registration() {
        return "RegistrationUserPage.html";
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody RegisterUserRequest registerUserRequest) {
        // check if user exists -> return 400 HTTP if user exists already
        if (!userRepository.findByUsername(registerUserRequest.getUserName()).isPresent()) {
            UserApp userApp = new UserApp();
            userApp.setUsername(registerUserRequest.getUserName());
            userApp.setPassword(registerUserRequest.getPassword());
            userApp.setActive(true);
            userRepository.save(userApp);
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.ok("user exist");
        }
    }

    @PostMapping("/login")
    public String login(UserApp userApp, Model model) {

        log.info("");
        userRepository.findByUsername(userApp.getUsername());
        return "redirect:/login";
    }
}
