<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Producto</title>
</head>
<body>
	<!-- Formulario para editar un producto existente -->
	<form action="${pageContext.request.contextPath}/EditarProducto"
		method="post">
		<!-- Campo oculto para el nombre del producto -->
		<input type="hidden" name="nombre" value="${producto.nombre}" />
		<!-- Campo para la descripción del producto -->
		<label for="descripcion">Descripción:</label> <input type="text"
			name="descripcion" value="${producto.descripcion}" /><br>
		<!-- Campo para el peso del producto -->
		<label for="peso">Peso:</label> <input type="text" name="peso"
			value="${producto.peso}" /><br>
		<!-- Campo para el stock del producto -->
		<label for="stock">Stock:</label> <input type="text" name="stock"
			value="${producto.stock}" required /><br>
		<!-- Botón para actualizar el producto -->
		<input type="submit" value="Actualizar" />
	</form>
</body>
</html>
