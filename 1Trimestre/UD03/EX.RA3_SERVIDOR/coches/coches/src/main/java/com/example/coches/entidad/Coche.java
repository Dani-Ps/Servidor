package com.example.coches.entidad;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.example.coches.entidad.FuenteEnergia;

public class Coche {

    private Long id;

    @NotBlank(message = "{error.marca}") // Validación: el campo no puede estar vacío o solo contener espacios en blanco.
    private String marca;

    @NotBlank(message = "{error.modelo}")
    private String modelo;

    @NotNull(message = "{error.anyo}") // Validación: el campo no puede ser nulo.
    private Integer ano;

    @NotNull(message = "{error.precio}")
    private Double precio;
    
    @NotNull(message = "{error.fuenteEnergia}")
    private FuenteEnergia fuenteEnergia;

	public Coche(@NotBlank(message = "{error.marca}") String marca,
			@NotBlank(message = "{error.modelo}") String modelo,
			@NotNull(message = "{error.anyo}") Integer ano,
			@NotNull(message = "{error.precio}") Double precio,
			@NotNull(message = "{error.fuenteEnergia}") FuenteEnergia fuenteEnergia) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.precio = precio;
		this.fuenteEnergia = fuenteEnergia;
	}
	public Coche() {}
	// Getters y setters para cada campo
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    
    public FuenteEnergia getFuenteEnergia() {
		return fuenteEnergia;
	}

	public void setFuenteEnergia(FuenteEnergia fuenteEnergia) {
		this.fuenteEnergia = fuenteEnergia;
	}

	// Puede que quieras sobrescribir el método toString() para una representación en cadena del objeto Coche.
    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", precio=" + precio +
                '}';
    }

	

}