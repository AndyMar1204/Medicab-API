package com.andy.medicab.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Hopital")
public class Hopital extends Account {

    private String nom;
    private LocalTime heureOuverture;
    private LocalTime heureFermeture;
    private Boolean ouvert;
    @OneToMany
    private Set<Departement> departements = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Urgence> urgences = new ArrayList<>();


    public List<Urgence> getUrgences() {
        return urgences;
    }

    public void addUrgence(Urgence urgence) {
        urgences.add(urgence);
        urgence.setHopital(this);
    }

    public void removeUrgence(Urgence urgence) {
        urgences.remove(urgence);
        urgence.setHopital(null);
    }

    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public LocalTime getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(LocalTime heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public LocalTime getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(LocalTime heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public Boolean isOuvert() {
        return checkOpen();
    }

    public void setOuvert(Boolean ouvert) {
        this.ouvert = ouvert;
    }

    

    public Hopital() {
setHeureOuverture(LocalTime.of(0,00,00,000));
setHeureFermeture(LocalTime.of(23,59,00,00));
    }
    public boolean checkOpen(){
        LocalDate today = LocalDate.now();
        LocalDateTime start = LocalDateTime.of(today,heureOuverture);
        LocalDateTime end = LocalDateTime.of(today,heureFermeture);
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(start)&&now.isBefore(end)){
            return true;
        }else
            return false;
    }

}
