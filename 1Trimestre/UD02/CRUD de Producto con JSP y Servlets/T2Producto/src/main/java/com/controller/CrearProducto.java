package com.controller;

import java.io.IOException;

import com.model.Producto;
import com.service.ProductoServicio;
import com.service.ProductoServicioImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CrearProducto
 * Esta clase representa un servlet para crear un nuevo producto.
 */
public class CrearProducto extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ProductoServicio servicio = ProductoServicioImp.obtenerInstancia();

    /**
     * Constructor por defecto de la clase CrearProducto.
     */
    public CrearProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Método para manejar las solicitudes GET.
     *
     * @param request  El objeto HttpServletRequest que representa la solicitud del cliente.
     * @param response El objeto HttpServletResponse que representa la respuesta al cliente.
     * @throws ServletException Si ocurre una excepción de tipo ServletException.
     * @throws IOException      Si ocurre una excepción de tipo IOException.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // En el método doGet, configurar el formulario para la creación de productos
        request.getRequestDispatcher("JSP/crearProducto.jsp").forward(request, response);
    }

    /**
     * Método para manejar las solicitudes POST.
     *
     * @param request  El objeto HttpServletRequest que representa la solicitud del cliente.
     * @param response El objeto HttpServletResponse que representa la respuesta al cliente.
     * @throws ServletException Si ocurre una excepción de tipo ServletException.
     * @throws IOException      Si ocurre una excepción de tipo IOException.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Double peso = Double.parseDouble(request.getParameter("peso"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        try {
            Producto nuevoProducto = new Producto(nombre, descripcion, peso, stock);
            servicio.createProducto(nuevoProducto);
            response.sendRedirect("JSP/exito.jsp");
        } catch (Exception e) {
            // Manejar excepciones adecuadamente, por ejemplo, enviar a una página de error
            // específica
            response.sendRedirect("JSP/error.jsp");
        }
    }
}
