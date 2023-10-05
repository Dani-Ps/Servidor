package com.service;

import java.util.List;

import com.model.Producto;

/**
 * Interfaz que define los servicios relacionados con la gestión de productos.
 */
public interface ProductoServicio {

    /**
     * Crea un nuevo producto.
     *
     * @param producto El producto a crear.
     * @return `true` si el producto se creó correctamente, `false` en caso contrario.
     */
    Boolean createProducto(Producto producto);

    /**
     * Lee un producto por su nombre.
     *
     * @param nombre El nombre del producto a buscar.
     * @return El producto encontrado o `null` si no se encontró ningún producto con ese nombre.
     */
    Producto readProducto(String nombre);

    /**
     * Actualiza un producto existente.
     *
     * @param producto El producto actualizado.
     * @return `true` si la actualización fue exitosa, `false` en caso contrario.
     */
    Boolean updateProducto(Producto producto);

    /**
     * Elimina un producto por su nombre.
     *
     * @param nombre El nombre del producto a eliminar.
     * @return `true` si el producto se eliminó correctamente, `false` en caso contrario.
     */
    Boolean deleteProducto(String nombre);

    /**
     * Obtiene una lista de todos los productos disponibles.
     *
     * @return Una lista de productos.
     */
    List<Producto> getAllProductos();
}
