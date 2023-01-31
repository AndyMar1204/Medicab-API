package com.andy.medicab.dao;

import com.andy.medicab.model.Hopital;
import com.andy.medicab.model.Urgence;
import com.andy.medicab.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUrgenceDao extends JpaRepository<Urgence, Long> {
    public List<Urgence> findAllByUser(User uSER); 
    public List<Urgence> findAllByHopital(Hopital hOPITAL);
}
