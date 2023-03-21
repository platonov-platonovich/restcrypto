package com.tolya.cryptocurrencies.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public UserApp(String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public UserApp() {
    }

    private String username;
    private String password;
    private boolean active;
}
