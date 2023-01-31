package com.andy.medicab.services;

import com.andy.medicab.dao.IUrgenceDao;
import com.andy.medicab.model.Urgence;
import com.andy.medicab.model.Urgences;
import com.andy.medicab.model.User;
import com.andy.medicab.services.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("urgenceService")
@Transactional
public class UrgenceService implements GenericService<Urgence,Long> {
    @Autowired
    IUrgenceDao dao;
    @Override
    public Long save(Urgence urgence) {
        return dao.save(urgence).getId();
    }

    @Override
    public Urgence update(Urgence urgence) {
        if (dao.existsById(urgence.getId()))
            return dao.save(urgence);
        return null;
    }

    @Override
    public Urgence findById(Long id) {
        return dao.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        if (dao.existsById(id))
            dao.deleteById(id);
    }

    @Override
    public List<Urgence> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return dao.existsById(id);
    }

    public List<Urgence> findAllUserUrgence(User user){
        return  dao.findAllByUser(user);
    }
}
