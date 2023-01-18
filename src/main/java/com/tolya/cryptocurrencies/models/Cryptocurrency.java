package com.tolya.cryptocurrencies.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Cryptocurrency")
public class Cryptocurrency {
    @Id
    private String id;
    @Column
    private String symbol;


    public Cryptocurrency() {

    }
}
