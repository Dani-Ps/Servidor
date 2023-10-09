package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.model.Producto;
import com.service.ProductoServicio;
import com.service.ProductoServicioImp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListarProducto
 * Esta clase representa un servlet para listar productos, con opción de filtrar por stock.
 */
public class ListarProducto extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ProductoServicio producto = ProductoServicioImp.obtenerInstancia();

    /**
     * Constructor por defecto de la clase ListarProducto.
     */
    public ListarProducto() {
        super();
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

        List<Producto> productos = producto.getAllProductos();

        request.setAttribute("PRODUCTO", productos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/ListarProducto.jsp");
        dispatcher.forward(request, response);
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

        boolean filtrarStock = "true".equals(request.getParameter("filter"));

        List<Producto> productos = producto.getAllProductos();

        if (filtrarStock) {
            // Filtrar productos por stock igual a cero
            productos = productos.stream().filter(producto -> producto.getStock() == 0).collect(Collectors.toList());
        }

        request.setAttribute("PRODUCTO", productos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/ListarProducto.jsp");
        dispatcher.forward(request, response);
    }
}
