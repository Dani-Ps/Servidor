package com.example.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Empleado;

@Repository
public interface EmpleadoRepo extends CrudRepository<Empleado, Integer>{

}
