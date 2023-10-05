package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcesarFormServlet
 */
public class ProcesarFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcesarFormServlet() {
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
		// Obtiene los parámetros enviados desde el formulario
		String nombre = request.getParameter("Nombre");
		String apellidos = request.getParameter("apellidos");
		String mail = request.getParameter("mail");
		if(mail != null) {
			request.setAttribute("mail", mail);
		}
		String genero = request.getParameter("genero");
		List<String> conocimientos = new ArrayList<>();
		conocimientos.add(request.getParameter("java"));
		conocimientos.add(request.getParameter("c"));
		conocimientos.add(request.getParameter("basic"));
		conocimientos.add(request.getParameter("HTML"));
		String[] idiomas;
		idiomas = (request.getParameterValues("idiomas"));
		String comentario = request.getParameter("comentario");

		// Guarda los datos en atributos del request
		request.setAttribute("nombre", nombre);
		request.setAttribute("apellidos", apellidos);
		
		request.setAttribute("genero", genero);
		request.setAttribute("conocimientos", conocimientos);
		request.setAttribute("idiomas", idiomas);
		request.setAttribute("comentario", comentario);

		// Redirige la solicitud al archivo JSP para mostrar los datos

		RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/mostrar.jsp");
		dispatcher.forward(request, response);

	}
}
