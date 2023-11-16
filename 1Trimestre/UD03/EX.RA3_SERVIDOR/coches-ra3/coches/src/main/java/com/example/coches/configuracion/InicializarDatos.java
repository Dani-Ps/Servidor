package com.example.coches.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.coches.entidad.Coche;
import com.example.coches.entidad.FuenteEnergia;
import com.example.coches.servicios.ServicioCoches;

import jakarta.annotation.PostConstruct;

@Component
public class InicializarDatos {
	
	@Autowired
	private ServicioCoches servicioCoches;
	
	
	@PostConstruct
	public void init() {
		 servicioCoches.guardar(new Coche("Tesla", "Model S", 2022, 80000.00, FuenteEnergia.ELECTRICO));
	        servicioCoches.guardar(new Coche("Chevrolet", "Volt", 2019, 25000.00, FuenteEnergia.HIBRIDO));
		 servicioCoches.guardar(new Coche("Toyota", "Corolla", 2020, 20000.00, FuenteEnergia.GASOLINA));
	        servicioCoches.guardar(new Coche("Ford", "Mustang", 2021, 35000.00, FuenteEnergia.GASOLINA));
		 servicioCoches.guardar(new Coche("Toyota", "Camry", 2019, 39037.28, FuenteEnergia.HIBRIDO));
		    servicioCoches.guardar(new Coche("Honda", "Civic", 2021, 42801.95, FuenteEnergia.GASOLINA));
		    servicioCoches.guardar(new Coche("Nissan", "Sentra", 2021, 51432.00, FuenteEnergia.DIESEL));
		    servicioCoches.guardar(new Coche("Ford", "Explorer", 2023, 22798.16, FuenteEnergia.GASOLINA));
		    servicioCoches.guardar(new Coche("Chevrolet", "Impala", 2018, 62604.71, FuenteEnergia.DIESEL));
		    servicioCoches.guardar(new Coche("Hyundai", "Elantra", 2018, 36403.20, FuenteEnergia.GASOLINA));
		    servicioCoches.guardar(new Coche("Kia", "Optima", 2020, 66365.72, FuenteEnergia.ELECTRICO));
		    servicioCoches.guardar(new Coche("Volkswagen", "Jetta", 2016, 95515.48, FuenteEnergia.DIESEL));
		    servicioCoches.guardar(new Coche("Subaru", "Outback", 2020, 44631.43, FuenteEnergia.GASOLINA));
		    servicioCoches.guardar(new Coche("Mazda", "3", 2017, 76088.56, FuenteEnergia.DIESEL));
		    servicioCoches.guardar(new Coche("BMW", "3 Series", 2020, 91351.61, FuenteEnergia.GASOLINA));
		    servicioCoches.guardar(new Coche("Mercedes-Benz", "C-Class", 2021, 59266.54, FuenteEnergia.HIBRIDO));
		    servicioCoches.guardar(new Coche("Audi", "A4", 2023, 68277.74, FuenteEnergia.ELECTRICO));
		    servicioCoches.guardar(new Coche("Lexus", "IS", 2015, 71446.88, FuenteEnergia.DIESEL));
		    servicioCoches.guardar(new Coche("Acura", "TLX", 2019, 35394.89, FuenteEnergia.HIBRIDO));
		    servicioCoches.guardar(new Coche("Infiniti", "Q50", 2019, 41952.80, FuenteEnergia.HIBRIDO));
		    servicioCoches.guardar(new Coche("Jaguar", "XE", 2016, 99358.61, FuenteEnergia.HIBRIDO));
		    servicioCoches.guardar(new Coche("Tesla", "Model 3", 2018, 35810.84, FuenteEnergia.ELECTRICO));
		    servicioCoches.guardar(new Coche("Volvo", "S60", 2022, 49621.91, FuenteEnergia.ELECTRICO));
		    servicioCoches.guardar(new Coche("Porsche", "Cayman", 2023, 37621.63, FuenteEnergia.DIESEL));
	}
}
