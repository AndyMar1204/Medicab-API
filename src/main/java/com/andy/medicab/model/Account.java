package com.andy.medicab.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    @Column(unique = true, nullable = false)
    private String number;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToOne(cascade = { CascadeType.ALL })
    private Position position;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;
    @Lob
    private String infos;
    @OneToOne(cascade = { CascadeType.ALL })
    private Adresse adresse;
    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn
    private FileInfo profil;

    public Account() {
        this.setDateCreated(LocalDateTime.now());
        this.setPosition(new Position());
        this.setAdresse(new Adresse());
    }
}