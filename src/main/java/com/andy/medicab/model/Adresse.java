package com.andy.medicab.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name="adresse")
@Getter
@Setter
@ToString
public class Adresse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String pays,province, ville, commune,quartier, avenue;
    private Integer numero;

}
