package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.service.ProductoServicio;
import com.service.ProductoServicioImp;

/**
 * Servlet implementation class EliminarProduct
 */
public class EliminarProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductoServicio producto = ProductoServicioImp.obtenerInstancia();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String nombre = request.getParameter("nombre");
		System.out.println("Eliminar producto.doPost nombre:" + nombre);
		try {
			if (producto.deleteProducto(nombre)) {
				response.sendRedirect("JSP/exito.jsp");
			} else {
				response.sendRedirect("JSP/error/error.jsp");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			response.sendRedirect("JSP/error.jsp");
		}
	}

}
