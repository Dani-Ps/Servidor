package com.pablo.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pablo.crud.model.Estudiante;
import com.pablo.crud.service.EstudianteService;

@Component
public class InicializarDatos implements CommandLineRunner{
	
	@Autowired
	private EstudianteService estudianteService;

	@Override
	public void run(String... args) throws Exception {
		Estudiante estudiante1 = new Estudiante();
		estudiante1.setId(1);
		estudiante1.setNombre("Pablo");
		estudiante1.setEdad(20);
		estudiante1.setCurso("2DAW");
		estudianteService.guardarEstudiante(estudiante1);
		Estudiante estudiante2 = new Estudiante();
		estudiante2.setId(2);
		estudiante2.setNombre("Cinta");
		estudiante2.setEdad(18);
		estudiante2.setCurso("1DER");
		estudianteService.guardarEstudiante(estudiante2);
	}

}
