package com.andy.medicab.dao;

import com.andy.medicab.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementDao extends JpaRepository<Departement, Long> {
}