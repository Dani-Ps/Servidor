package com.example.mvc_coches.modelo;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.mvc_coches.controlador.CocheControlador;
import com.example.mvc_coches.servicio.CocheServicio;


@WebMvcTest(CocheControlador.class)
public class ControladorCocheUnitTest {
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private CocheServicio servicio;

	    Coche coche = new Coche();
	    
	    @BeforeEach
	    public void setUp() {
		
		 coche.setId(1);
		 coche.setMarca("Toyota");
		 coche.setMatricula("1234ABC");
		 coche.setColor("Rojo");
	
	    }

	@Test
	public void agregar_CuandoCocheEsValido_DeberiaRedirigirACoches() throws Exception {
	   
	    mockMvc.perform(post("/agregar")
	            .flashAttr("coche", coche))
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrl("/coches"));
	}

	@Test
	public void agregar_CuandoCocheNoEsValido_DeberiaVolverAlFormulario() throws Exception {
	    Coche coche = new Coche(); // Coche sin datos para forzar errores de validación
	    mockMvc.perform(post("/agregar")
	            .flashAttr("coche", coche))
	            .andExpect(status().isOk())
	            .andExpect(model().attributeHasErrors("coche"))
	            .andExpect(view().name("form"));
	}

	@Test
	public void mostrarFormularioEditar_DeberiaRetornarVistaConCoche() throws Exception {
	   
	    when(servicio.obtenerCochePorId(1)).thenReturn(coche);
	    mockMvc.perform(get("/coche/editar/{id}", 1))
	            .andExpect(status().isOk())
	            .andExpect(model().attributeExists("coche"))
	            .andExpect(view().name("form"));
	}

	@Test
	public void eliminarCoche_DeberiaRedirigirACoches() throws Exception {
	    doNothing().when(servicio).eliminarProducto(1);
	    mockMvc.perform(get("/coche/eliminar/{id}", 1))
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrl("/coches"));
	}
}
