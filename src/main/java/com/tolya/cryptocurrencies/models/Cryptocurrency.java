package com.tolya.cryptocurrencies.models;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Cryptocurrency")
public class Cryptocurrency {
    @Id
    private String id;
    @Column
    private String symbol;
}
