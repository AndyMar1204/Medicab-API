/**
 * 
 */
package com.andy.medicab.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.andy.medicab.model.Adresse;

/**
 * @author Andy
 *
 */
public interface IAdresse  extends JpaRepository<Adresse,Long> {

}
