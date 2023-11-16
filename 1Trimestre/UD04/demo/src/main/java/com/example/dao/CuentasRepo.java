package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.CuentaUsuario;

@Repository
public interface CuentasRepo extends JpaRepository<CuentaUsuario, Long>{
	
	

}
