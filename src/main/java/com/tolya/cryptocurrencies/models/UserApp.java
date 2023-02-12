package com.tolya.cryptocurrencies.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "userApp")
public class UserApp  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String username;
    private String password;
    private boolean active;
    @OneToOne
    @JoinColumn(name = "user_cryptocurrency_id", insertable = false, updatable = false)
    private UserCryptocurrency userCryptocurrency;

}
