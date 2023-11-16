package com.example.mvc_coches.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mvc_coches.modelo.Coche;

@Repository
public interface CocheRepositorio extends CrudRepository<Coche, Integer>{

}
 