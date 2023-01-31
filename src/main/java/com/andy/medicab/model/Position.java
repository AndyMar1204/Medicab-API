package com.andy.medicab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@Table(name = "position")
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double longitude;
    private Double latitude;
  public Position() {
        this.setLongitude(0.0);
        this.setLatitude(0.0);
    }
}
