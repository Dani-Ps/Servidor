package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Empleado;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Long>{

}
