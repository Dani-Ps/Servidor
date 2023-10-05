<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model.Producto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Incluir el archivo CSS de Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Crear Producto</title>
</head>
<body>
	<!-- Título de la página -->
	<h1>Crear Producto</h1>
	<!-- Formulario para crear un nuevo producto -->
	<form action="${pageContext.request.contextPath}/CrearProducto"
		method="post">
		<!-- Campo para el nombre del producto -->
		<label for="nombre">Nombre:</label> <input type="text" id="nombre"
			name="nombre" required><br>
		<!-- Campo para la descripción del producto -->
		<label for="descripcion">Descripción:</label> <input type="text"
			id="descripcion" name="descripcion"><br>
		<!-- Campo para el peso del producto -->
		<label for="peso">Peso:</label> <input type="text" id="peso"
			name="peso"><br>
		<!-- Campo para el stock del producto -->
		<label for="stock">Stock:</label> <input type="text" id="stock"
			name="stock" required><br>
		<!-- Botón para guardar el producto -->
		<input type="submit" value="Guardar">
	</form>
</body>
</html>
