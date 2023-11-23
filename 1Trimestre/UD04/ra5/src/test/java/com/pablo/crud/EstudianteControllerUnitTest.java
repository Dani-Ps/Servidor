package com.pablo.crud;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pablo.crud.controller.EstudianteController;
import com.pablo.crud.model.Estudiante;
import com.pablo.crud.service.EstudianteService;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EstudianteService estudianteService;
	
	private Estudiante estudiante;
	
	@BeforeEach
	public void setup() {
		estudiante = new Estudiante();
		estudiante.setId(1);
		estudiante.setNombre("Pablo");
		estudiante.setEdad(20);
		estudiante.setCurso("2DAW");
		estudianteService.guardarEstudiante(estudiante);
	}

	@Test
	void whenGoToForm_AttributeExistsAndCorrectView() throws Exception {
		mockMvc.perform(get("/estudiante/nuevo")
			.flashAttr("estudiante", estudiante))
			.andExpect(model().attributeExists("estudiante"))
			.andExpect(view().name("form_estudiante"));
	}
	
	@Test
	void whenCreateEstudent_redirectToEstudiantesAndFlashAttribute() throws Exception {
		mockMvc.perform(post("/agregar")
			.flashAttr("estudiante", estudiante))
			.andExpect(redirectedUrl("/estudiantes"));
	}
	
	
	
	@Test
	void whenDeleteEstudent_redirectToEstudiantes() throws Exception {
		when(estudianteService.encontrarPorId(1)).thenReturn(estudiante);
		
		mockMvc.perform(get("/estudiante/eliminar/{id}", estudiante.getId()))
			.andExpect(redirectedUrl("/estudiantes"));		
		
	}
	
	@Test
	void whenEditEstudent_formToEdit() throws Exception {
		when(estudianteService.encontrarPorId(1)).thenReturn(estudiante);
		
		mockMvc.perform(get("/estudiante/editar/{id}", estudiante.getId()))
			.andExpect(view().name("form_estudiante"));		
			
	}
	

}
