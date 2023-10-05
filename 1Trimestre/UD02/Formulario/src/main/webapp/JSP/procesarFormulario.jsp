<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Procesar Datos</title>
</head>
<body>
	<h1>Datos recibidos:</h1>
	<p>
		Nombre:
		<%=request.getParameter("nombre")%></p>
	<p>
		Apellidos:
		<%=request.getParameter("apellidos")%></p>
	<p>
		Correo:
		<%=request.getParameter("mail")%></p>
	<p>
		Genero:
		<%=request.getParameter("genero")%>
	</p>
	<p>
		Lenguajes que sabes:
		<%
	String javaChecked = request.getParameter("java");
	String cChecked = request.getParameter("c");
	String basicChecked = request.getParameter("basic");
	String htmlChecked = request.getParameter("html");
	%>

		<%
		if (javaChecked != null && javaChecked.equals("java")) {
		%>
		Java<br>
		<%
		}
		%>

		<%
		if (cChecked != null && cChecked.equals("c")) {
		%>
		C/C++<br>
		<%
		}
		%>

		<%
		if (basicChecked != null && basicChecked.equals("basic")) {
		%>
		Basic<br>
		<%
		}
		%>

		<%
		if (htmlChecked != null && htmlChecked.equals("html")) {
		%>
		HTML<br>
		<%
		}
		%>
	</p>

	<table>
		<thead>
			<th>Idioma</th>
		</thead>
		<tbody>
			<tr><%=request.getParameter("idiomas")%></tr>
		</tbody>
	</table>
	<p>
		Comentarios:
		<%
	String comentariosChecked = request.getParameter("comentario");
	%>
		<%
		if (comentariosChecked != null) {
			out.print(comentariosChecked);
		%>
		<%
		}
		%>
	</p>



</body>
</html>
