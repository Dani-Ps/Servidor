<%@page import="java.util.Collection"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.lang.reflect.Array"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>

<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Procesar Datos</title>
</head>
<body>
	<h1>Datos recibidos:</h1>
	<p>
		Nombre:
		<%=request.getAttribute("nombre")%></p>
	<p>
		Apellidos:
		<%=request.getAttribute("apellidos")%></p>
	<p>
		Correo:
		<%=request.getAttribute("mail")%></p>
	<p>
	<p>
		Genero:
		<%=request.getAttribute("genero")%>
	</p>
	<p>
		Lenguajes que sabes:
		<%
	List<String> conocimientos = (List<String>) request.getAttribute("conocimientos");
	if (conocimientos != null && !conocimientos.isEmpty()) {
		for (String conocimiento : conocimientos) {
			if (conocimiento != null) { // Verifica si el conocimiento no es nulo
	%>
		<br>
		<%=conocimiento%>
		<%
		}
		}
		} else {
		%>
		<br> No has seleccionado ningún lenguaje.
		<%
		}
		%>
	</p>



	<table class="table">
		<thead>
			<th>Idioma</th>
		</thead>
		<tbody>
			<%
			String[] idiomas = (String[]) request.getAttribute("idiomas");
			if (idiomas != null && idiomas.length > 0) {
				for (String idioma : idiomas) {
			%>
			<tr>
				<td><%=idioma%></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td>No has seleccionado ningún idioma.</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<p>
		Comentario:
		<%
	String comentario = (String) request.getAttribute("comentario");
	if (comentario != null && !comentario.isEmpty()) {
	%>
		<br>
		<%=comentario%>
		<%
		} else {
		%>
		<br> No has ingresado ningún comentario.
		<%
		}
		%>
	</p>



</body>
</html>
