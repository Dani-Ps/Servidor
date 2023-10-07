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
	<!-- Obtener la lista de productos del atributo request -->
	<%
	List<Producto> productos =(List<Producto>) request.getAttribute("PRODUCTOS");
	%>
	<!-- Título de la página -->
	<h1>Listado de Productos</h1>
	<!-- Enlace para agregar un nuevo producto -->
	<ul>
		<li><a href="AddProducto">Nuevo</a></li>
	</ul>
	<!-- Tabla para mostrar la lista de productos -->
	<table class="table">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Peso</th>
				<th>Stock</th>
			</tr>
		</thead>
		<tbody>
			<!-- Iterar a través de la lista de productos usando JSTL -->
			<c:forEach var="producto" items="${productos}">
				<tr>
					<td>${producto.nombre}</td>
					<td>${producto.descripcion}</td>
					<td>${producto.peso}</td>
					<td>${producto.stock}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- Formulario para filtrar productos sin stock -->
	<label for="filter">Sin Stock</label>
	<form action="ListProducto" method="post">
		<input type="checkbox" id="filter" name="filter" value="true">
		<input type="submit" value="Filtrar">
	</form>
</body>
</html>
