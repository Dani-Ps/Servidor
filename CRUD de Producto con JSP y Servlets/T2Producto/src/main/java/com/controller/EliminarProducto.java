package com.controller;

import com.service.ProductoServicio;
import com.service.ProductoServicioImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class EliminarProducto
 * Esta clase representa un servlet para eliminar un producto.
 */
@WebServlet("/EliminarProducto")
public class EliminarProducto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductoServicio productoServicio = ProductoServicioImp.obtenerInstancia();

    /**
     * Constructor por defecto de la clase EliminarProducto.
     */
    public EliminarProducto() {
        super();
    }

    /**
     * Método para manejar las solicitudes POST.
     *
     * @param request  El objeto HttpServletRequest que representa la solicitud del cliente.
     * @param response El objeto HttpServletResponse que representa la respuesta al cliente.
     * @throws ServletException Si ocurre una excepción de tipo ServletException.
     * @throws IOException      Si ocurre una excepción de tipo IOException.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el nombre del producto a eliminar desde los parámetros de la solicitud
        String nombre = request.getParameter("nombre");

        if (nombre != null) {
            // Llamar al servicio para eliminar el producto
            boolean exito = productoServicio.deleteProducto(nombre);

            if (exito) {
                // Redirigir a una página de éxito si la eliminación fue exitosa
                response.sendRedirect("exito.jsp");
            } else {
                // Redirigir a una página de error si la eliminación falló
                response.sendRedirect("error.jsp");
            }
        } else {
            // Manejar el caso en el que no se proporcionó un nombre de producto válido
            response.sendRedirect("error.jsp");
        }
    }
}
