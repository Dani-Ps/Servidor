package com.example.demo.unitaria;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.demo.controlador.EstudianteControlador;
import com.example.demo.entidad.Curso;
import com.example.demo.entidad.Estudiante;
import com.example.demo.servicio.EstudianteServicio;

@WebMvcTest(EstudianteControlador.class)
public class EstudianteControladorTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private EstudianteServicio estudianteServicio;


	    private List<Estudiante> estudiantes;

	    @BeforeEach
	    void setUp() {
	        this.estudiantes = new ArrayList<Estudiante>();
	        // Aquí agregarías algunos estudiantes de prueba a la lista
	    }
	    @Test
	    void verTodosLosEstudiantes() throws Exception {
	        given(estudianteServicio.obtenerTodos()).willReturn(estudiantes);

	        mockMvc.perform(get("/estudiantes"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("estudiantes/lista"))
	                .andExpect(model().attribute("estudiantes", estudiantes));
	    }
	    
	    
	    
}