<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

.error-container {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

h1 {
	color: #f00;
}

ul {
	list-style: none;
	padding: 0;
}

li {
	margin-bottom: 10px;
}

a {
	text-decoration: none;
	color: #007bff;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="error-container">
		<h1>¡Oops! Algo no funcionó como esperábamos</h1>
		<p>Lamentablemente, hemos encontrado un problema al procesar tu
			solicitud.</p>
		<p>Detalles del error:</p>
		<ul>
			<li><strong>Código de Error:</strong>
				${pageContext.errorData.statusCode}</li>
			<li><strong>Mensaje:</strong> ${pageContext.exception.message}</li>
		</ul>
		<p>Por favor, inténtalo nuevamente más tarde o ponte en contacto
			con el administrador del sistema si el problema persiste.</p>
		<p>
			<c:url value="../ListarProductos.jsp" var="paginaPrincipalURL" />
			<a href="${paginaPrincipalURL}">Volver a la página principal</a>
		</p>
	</div>
</body>
</html>
