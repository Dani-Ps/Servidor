package com.model;

import java.io.Serializable;

/**
 * Clase que representa un producto.
 */
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String descripcion;
    private Double peso;
    private int stock;

    /**
     * Constructor por defecto de la clase Producto.
     */
    public Producto() {
        super();
    }

    /**
     * Constructor de la clase Producto que inicializa sus atributos.
     *
     * @param nombre      El nombre del producto.
     * @param descripcion La descripción del producto.
     * @param peso        El peso del producto.
     * @param stock       El stock del producto.
     */
    public Producto(String nombre, String descripcion, Double peso, int stock) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setPeso(peso);
        setStock(stock);
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre El nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return La descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param descripcion La descripción del producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el peso del producto.
     *
     * @return El peso del producto.
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * Establece el peso del producto.
     *
     * @param peso El peso del producto.
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * Obtiene el stock del producto.
     *
     * @return El stock del producto.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece el stock del producto.
     *
     * @param stock El stock del producto.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Producto [nombre=" + nombre + ", descripcion=" + descripcion + ", peso=" + peso + ", Stock=" + stock
                + "]";
    }
}
