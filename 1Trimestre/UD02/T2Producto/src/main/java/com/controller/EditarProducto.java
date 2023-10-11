package com.controller;

import java.io.IOException;

import com.model.Producto;
import com.service.ProductoServicio;
import com.service.ProductoServicioImp;

import jakarta.servlet.RequestDispatcher;
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
				response.sendRedirect("JSP/error/error.jsp");
				return;
			}
			request.setAttribute("descripcion", p.getDescripcion());
			request.setAttribute("peso", p.getPeso());
			request.setAttribute("stock", p.getStock());
			request.setAttribute("producto", p);

			RequestDispatcher dispacher = request.getRequestDispatcher("JSP/editarProducto.jsp");
			dispacher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();

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
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			String pesoParam = request.getParameter("peso");
			String stockParam = request.getParameter("stock");

			// Comprueba si "peso" y "stock" no son nulos
			if (pesoParam != null && stockParam != null) {
				double peso = Double.parseDouble(pesoParam);
				int stock = Integer.parseInt(stockParam);

				Producto p = new Producto(nombre, descripcion, peso, stock);

				boolean exito = productoServicio.updateProducto(p);

				if (!exito) {
					request.setAttribute("mensaje", "Error al actualizar el producto.");
				} else {
					request.setAttribute("mensaje", "Producto actualizado con éxito.");
				}

				// Redirige a una página de confirmación (exito o error)
				RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/exito.jsp");
				dispatcher.forward(request, response);
			} else {
				// Manejar el caso en el que "peso" o "stock" son null, por ejemplo, mostrando
				// un mensaje de error.
				request.setAttribute("mensaje", "Error en los datos del formulario.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/exito.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("JSP/error/error.jsp");
		}
	}

}