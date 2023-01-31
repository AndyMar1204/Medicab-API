package com.andy.medicab.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Urgence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private  Urgences typeUrgences;
    @Enumerated(EnumType.STRING)
    private Transport typeTransport;
    @ManyToOne
   private User user ;
    @ManyToOne
    private Hopital hopital;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private EmergencyState etat;

    public Urgence() {
        setEtat(EmergencyState.ENCOURS);
        setDate(LocalDateTime.now());
    }



}
