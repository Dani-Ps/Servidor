package com.example.demo.unitarias.controlador;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.controlador.EstudianteControlador;
import com.example.demo.entidad.Curso;
import com.example.demo.entidad.Estudiante;
import com.example.demo.servicio.CursoServicio;
import com.example.demo.servicio.EstudianteServicio;

@WebMvcTest(EstudianteControlador.class)
class EstudianteControladorTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private EstudianteServicio estudianteServicio;

	    @MockBean
	    private CursoServicio cursoServicio;

	     // Crear una lista ficticia de estudiantes
        List<Estudiante> estudiantesMock = new ArrayList<>();
	    
	    @BeforeEach
	    public void setUp() {

	        Estudiante estudiante1 = new Estudiante();
	        estudiante1.setID(1L);
	        estudiante1.setNombre("Estudiante 1");
	        estudiantesMock.add(estudiante1);

	        Estudiante estudiante2 = new Estudiante();
	        estudiante2.setID(2L);
	        estudiante2.setNombre("Estudiante 2");
	        estudiantesMock.add(estudiante2);

	  	    }

	    @Test
	    public void testListarEstudiantes() throws Exception {
	    	 // Configura el comportamiento simulado del servicio de estudiantes
	        when(estudianteServicio.listarTodosLosEstudiantes()).thenReturn(estudiantesMock);

	        // Realiza una solicitud GET a la ruta "/estudiantes" y espera un código de respuesta 200 (OK)
	        mockMvc.perform(get("/estudiantes"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("estudiantes/lista")) // Verifica que se muestre la vista "lista-estudiantes"
	                .andExpect(model().attributeExists("estudiantes"));
	
	    }
	    
	    @Test
	    public void testEliminarEstudiante() throws Exception {
	        // ID del estudiante a eliminar
	        Long estudianteId = 1L;

	        // Configura el comportamiento simulado del servicio para eliminar el estudiante
	        doNothing().when(estudianteServicio).eliminarEstudiante(estudianteId);

	        // Realiza una solicitud POST a la ruta "/estudiantes/eliminar/{id}" para eliminar el estudiante
	        mockMvc.perform(get("/estudiantes/eliminar/{id}", estudianteId))
	                .andExpect(status().is3xxRedirection()) // Espera una redirección después de eliminar
	                .andExpect(redirectedUrl("/estudiantes")); // Espera que se redireccione a la lista de estudiantes
	    }
	    
	    @Test
	    public void testMostrarFormularioEditar() throws Exception {
	        // Configurar el comportamiento simulado del servicio
	        Estudiante estudianteMock = new Estudiante();
	        estudianteMock.setID(1L);
	        estudianteMock.setNombre("Estudiante de Prueba");
	        when(estudianteServicio.buscarEstudiantePorId(1L)).thenReturn(estudianteMock);

	        // Realizar una solicitud GET a la ruta "/estudiantes/editar/1" (donde 1 es el ID del estudiante a editar)
	        mockMvc.perform(MockMvcRequestBuilders.get("/estudiantes/editar/1"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.view().name("estudiantes/formulario")) // Verificar que se muestra la vista "formulario"
	            .andExpect(MockMvcResultMatchers.model().attributeExists("estudiante")); // Verificar que existe el atributo "estudiante" en el modelo
	    }

	    @Test
	    public void testGuardarEstudianteExitoso() throws Exception {
	        // Crea un objeto de tipo Estudiante para la solicitud
	        Estudiante estudiante = new Estudiante();
	        estudiante.setNombre("Nombre de ejemplo");
	        estudiante.setEmail("correo@example.com");
	        estudiante.setFechaNacimiento(LocalDate.of(2000, 1, 1)); // Ajusta la fecha según tus necesidades

	        // Simula el comportamiento del servicio para guardar el estudiante
	        when(estudianteServicio.guardarEstudiante(any(Estudiante.class))).thenReturn(estudiante);

	        // Realiza una solicitud POST a la ruta "/estudiantes/nuevo" con los datos del estudiante
	        mockMvc.perform(post("/estudiantes/nuevo")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("nombre", "Nombre de ejemplo")
	                .param("email", "correo@example.com")
	                .param("fechaNacimiento", "2000-01-01")) // Ajusta la fecha de acuerdo a tu formato
	                .andExpect(status().is3xxRedirection()) // Espera una redirección
	                .andExpect(redirectedUrl("/estudiantes")); // Espera una redirección a la lista de estudiantes
	    }

	    @Test
	    public void testGuardarEstudianteConErroresDeValidacion() throws Exception {
	        // Crea un objeto de tipo Estudiante con datos inválidos para la solicitud
	        Estudiante estudiante = new Estudiante();
	        estudiante.setNombre(""); // Nombre en blanco (error de validación)

	        // Realiza una solicitud POST a la ruta "/estudiantes/nuevo" con los datos del estudiante inválidos
	        mockMvc.perform(post("/estudiantes/nuevo")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("nombre", "")) // Nombre en blanco (error de validación)
	                .andExpect(status().isOk()) // Espera un código de respuesta OK
	                .andExpect(view().name("estudiantes/formulario")); // Espera que se muestre el formulario nuevamente
	    }
}
