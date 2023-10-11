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
 * Servlet implementation class EditarProducto Esta clase representa un servlet
 * para editar un producto existente.
 */
public class EditarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoServicio productoServicio = ProductoServicioImp.obtenerInstancia();

	/**
	 * Constructor por defecto de la clase EditarProducto.
	 */
	public EditarProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Método para manejar las solicitudes GET.
	 *
	 * @param request  El objeto HttpServletRequest que representa la solicitud del
	 *                 cliente.
	 * @param response El objeto HttpServletResponse que representa la respuesta al
	 *                 cliente.
	 * @throws ServletException Si ocurre una excepción de tipo ServletException.
	 * @throws IOException      Si ocurre una excepción de tipo IOException.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String nombre = request.getParameter("nombre");
			Producto p = productoServicio.readProducto(nombre);

			if (p == null) {
				response.sendRedirect("error.jsp");
				return;
			}

			request.setAttribute("producto", p);
			request.getRequestDispatcher("editarProducto.jsp").forward(request, response);

		} catch (Exception e) {
			// Manejar excepciones adecuadamente, por ejemplo, enviar a una página de error
			// específica
		}

	}

	/**
	 * Método para manejar las solicitudes POST.
	 *
	 * @param request  El objeto HttpServletRequest que representa la solicitud del
	 *                 cliente.
	 * @param response El objeto HttpServletResponse que representa la respuesta al
	 *                 cliente.
	 * @throws ServletException Si ocurre una excepción de tipo ServletException.
	 * @throws IOException      Si ocurre una excepción de tipo IOException.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nombre = request.getParameter("nombre");
			System.out.println("editar doPost " + nombre);
			String descripcion = request.getParameter("descripcion");
			double peso = Double.parseDouble(request.getParameter("peso"));
			int stock = Integer.parseInt(request.getParameter("stock"));

			Producto p = new Producto(nombre, descripcion, peso, stock);
			System.out.println("editar doPost creado el nuevo producto");

			boolean exito = productoServicio.updateProducto(p);

			if (!exito) {
				response.sendRedirect("JSP/error/error.jsp");
			} else {
				response.sendRedirect("JSP/exito.jsp");
			}
		} catch (Exception e) {
			response.sendRedirect("JSP/error/error.jsp");
		}
	}
}