package com.andy.medicab.controller;


import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *
 * @author Ir Andy
 * @param <T> Entité
 * @param <I> Identifiant
 */
public interface CrudController<T,Id>{
    /**
     *
     * @return <Id>
     */
    ResponseEntity<Id> save(T t);

    /**
     *
     * @param t
     * @return <T>
     */
    ResponseEntity<T> update(T t,Id id);

    /**
     *@param id
     * @return <Void>
     */
    ResponseEntity<Void> delete(Id id);

    /**
     *
     * @param id
     * @return <T>
     */
    ResponseEntity<T> findById(Id id);
    /**
     *
     *
     * @return ResponseEntity<List<T>>
     */
    ResponseEntity<List<T>> getAll();
    /**
     *
     *
     * @return <Boolean>
     */
    ResponseEntity<Boolean> checkExist(Id id);
}
