-- Crear una nueva base de datos llamada "Tarea2"
CREATE DATABASE Tarea2;

-- Usar la base de datos recién creada
USE Tarea2;

-- Crear la tabla "productos" con las columnas necesarias
CREATE TABLE productos (
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    peso DOUBLE NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (nombre)
);



-- Insertar algunas marcas de ordenadores en la tabla
INSERT INTO productos (nombre, descripcion, peso, stock)
VALUES
    ('Producto 1', 'Descripción 1', 2.5, 10),
    ('Producto 2', 'Descripción 2', 1.8, 20),
    ('Producto 3', 'Descripción 3', 3.0, 15),
    ('Producto 4', 'Descripción 4', 2.2, 30),
    ('Producto 5', 'Descripción 5', 2.7, 25),
    ('Producto 6', 'Descripción 6', 1.5, 5),
    ('Producto 7', 'Descripción 7', 2.0, 8),
    ('Producto 8', 'Descripción 8', 2.9, 12),
    ('Producto 9', 'Descripción 9', 2.3, 18),
    ('Producto 10', 'Descripción 10', 1.7, 22);


