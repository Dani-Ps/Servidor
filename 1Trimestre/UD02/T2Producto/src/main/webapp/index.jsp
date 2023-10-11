<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>App</title>
</head>
<body>
	<div class="container">
		<!-- Encabezado con Bootstrap Navbar -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">Mi Aplicación</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="ListarProducto">Listado
							de Productos</a></li>
				</ul>
			</div>
		</nav>
		<!-- Contenido principal -->
		<div class="jumbotron">
			<h1 class="display-4">Bienvenido a mi aplicación</h1>
			<p class="lead">Ejercicio de gestión de producto con crud.</p>
			<hr class="my-4">
			<a class="btn btn-primary btn-lg" href="ListarProducto" role="button">Ver
				listado y opciones</a>
		</div>
	</div>

</body>
</html>
