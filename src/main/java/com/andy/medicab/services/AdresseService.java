package com.andy.medicab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andy.medicab.dao.IAdresse;
import com.andy.medicab.model.Adresse;
import com.andy.medicab.services.interfaces.GenericService;

/**
 * @author Andy
 *
 */
@Service("adresseService")
@Transactional
public class AdresseService implements GenericService<Adresse,Long> {
	@Autowired
	IAdresse dao;

	@Override
	public Long save(Adresse e) {
		// TODO Auto-generated method stub
		return dao.save(e).getId();
	}

	@Override
	public Adresse update(Adresse e) {
		// TODO Auto-generated method stub
		return dao.save(e);
	}

	@Override
	public Adresse findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
		
	}

	@Override
	public List<Adresse> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean existById(Long id) {
		// TODO Auto-generated method stub
		return dao.existsById(id);
	}

}
