package com.andy.medicab.dao;

import com.andy.medicab.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorDao extends JpaRepository<Doctor,Long> {
}
