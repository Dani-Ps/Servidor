package com.example.libros.controlador;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorError implements ErrorController  {
    @RequestMapping("/error")
    public String handleError() {
    	 //@TODO
    	
    	 //Plantilla (error.html)
         return "error"; 
    }

}
