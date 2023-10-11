<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossorigin="anonymous">
    <title>Editar Producto</title>
</head>
<body>
    <!-- Contenedor principal con margen -->
    <div class="container mt-4">
        <!-- Formulario para editar un producto existente -->
        <form action="${pageContext.request.contextPath}/EditarProducto" method="post">
            <!-- Campo oculto para el nombre del producto -->
            <input type="hidden" name="nombre" value="${producto.nombre}" />
            
            <!-- Campo para la descripción del producto -->
            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <input type="text" class="form-control" name="descripcion" value="${producto.descripcion}" />
            </div>
            
            <!-- Campo para el peso del producto -->
            <div class="form-group">
                <label for="peso">Peso:</label>
                <input type="number" class="form-control" name="peso" value="${producto.peso}" />
            </div>
            
            <!-- Campo para el stock del producto -->
            <div class="form-group">
                <label for="stock">Stock:</label>
                <input type="number" class="form-control" name="stock" value="${producto.stock}" required />
            </div>
            
            <!-- Botón para actualizar el producto -->
            <button type="submit" class="btn btn-primary">Actualizar</button>
        </form>
    </div>
</body>
</html>
