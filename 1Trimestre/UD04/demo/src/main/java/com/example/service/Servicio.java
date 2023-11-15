package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CuentasRepo;
import com.example.dao.EmpleadoRepo;
import com.example.model.CuentaUsuario;
import com.example.model.Empleado;

@Service
public class Servicio {
	@Autowired
	EmpleadoRepo repositorioEmpleado;
	@Autowired
	CuentasRepo repositorioCuentas;

	public Empleado crearEmpleado(Empleado e, CuentaUsuario cu) {

		e.setCuentaUsuario(cu);
		repositorioEmpleado.save(e);

		return repositorioEmpleado.save(e);
	}

	public List<Empleado> obtenerEmpleados() {

		return (List<Empleado>) repositorioEmpleado.findAll();
	}

}
