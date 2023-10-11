<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model.Producto"%>

<!DOCTYPE html>
<html>
<head>
<!-- Incluir el archivo CSS de Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Listado de Productos</title>
</head>
<body>
	<!-- Contenedor principal con margen -->
	<div class="container mt-4">
		<!-- Título de la página -->
		<h1 class="mb-4">Listado de Productos</h1>
		<!-- Enlace para agregar un nuevo producto -->
		<a class="btn btn-primary mb-3" href="CrearProducto">Nuevo</a>
		<!-- Tabla para mostrar la lista de productos -->
		<table class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Descripción</th>
					<th>Peso</th>
					<th>Stock</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<!-- Iterar a través de la lista de productos usando JSTL -->
				<c:forEach var="producto" items="${PRODUCTOS}">
					<tr>
						<td>${producto.nombre}</td>
						<td>${producto.descripcion}</td>
						<td>${producto.peso}</td>
						<td>${producto.stock}</td>
						<td><jsp:include page="elementos/botonModificar.jsp">
								<jsp:param name="nombre" value="${producto.nombre}" />
							</jsp:include> <jsp:include page="elementos/botonEliminar.jsp">
								<jsp:param name="nombre" value="${producto.nombre}" />
							</jsp:include></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- Formulario para filtrar productos sin stock -->
		<form action="ListarProducto" method="post">
			<div class="form-check mb-3">
				<input type="checkbox" class="form-check-input" id="filter"
					name="filter" value="true"> <label class="form-check-label"
					for="filter">Sin Stock</label>
			</div>
			<button type="submit" class="btn btn-primary">Filtrar</button>
		</form>
	</div>
</body>
</html>
