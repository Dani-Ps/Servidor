package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Producto;

/**
 * Implementación de la interfaz ProductoServicio que proporciona servicios
 * relacionados con la gestión de productos mediante una base de datos MySQL.
 */
public class ProductoServicioImp implements ProductoServicio {

	private static ProductoServicioImp instancia;
	private final String url = "jdbc:mysql://localhost:3306/Tarea2";
	private final String user = "root";
	private final String password = "root";

	/**
	 * Constructor privado de ProductoServicioImp. Inicializa la conexión a la base
	 * de datos MySQL.
	 */
	public ProductoServicioImp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene una instancia única de ProductoServicioImp.
	 *
	 * @return La instancia de ProductoServicioImp.
	 */
	public static synchronized ProductoServicioImp obtenerInstancia() {
		if (instancia == null) {
			instancia = new ProductoServicioImp();
		}
		return instancia;

	}

	@Override
	public Boolean createProducto(Producto producto) {
		// Implementación del método para crear un producto en la base de datos

		try (Connection connection = DriverManager.getConnection(url, user, password)) {

			String sql = "INSERT INTO Productos (nombre, descripcion, peso, stock) VALUES (?, ?, ?, ?)";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, producto.getNombre());
				statement.setString(2, producto.getDescripcion());
				statement.setDouble(3, producto.getPeso());
				statement.setInt(4, producto.getStock());

				int rowsInserted = statement.executeUpdate();
				return rowsInserted > 0;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Producto readProducto(String nombre) {
		// Implementación del método para leer un producto de la base de datos por
		// nombre

		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT * FROM Productos WHERE nombre = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, nombre);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {

						String descripcion = resultSet.getString("descripcion");
						Double peso = resultSet.getDouble("peso");
						int stock = resultSet.getInt("stock");
						return new Producto(nombre, descripcion, peso, stock);
					}
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean updateProducto(Producto producto) {
		// Implementación del método para actualizar un producto en la base de datos

		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			System.out.println("update producto conexion establecida");

			String sql = "UPDATE Productos SET nombre = ?, descripcion = ?, peso = ?, stock = ? WHERE nombre = ?";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, producto.getNombre());
				statement.setString(2, producto.getDescripcion());
				statement.setDouble(3, producto.getPeso());
				statement.setInt(4, producto.getStock());
				int rowsUpdated = statement.executeUpdate();
				return rowsUpdated > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deleteProducto(String nombre) {
		// Implementación del método para eliminar un producto de la base de datos por
		// nombre

		try (Connection connection = DriverManager.getConnection(url, user, password)) {

			String sql = "DELETE FROM Productos WHERE nombre = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, nombre);
				int rowsDeleted = statement.executeUpdate();
				return rowsDeleted > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Producto> getAllProductos() {
		// Implementación del método para obtener una lista de todos los productos en la
		// base de datos

		List<Producto> productos = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(url, user, password)) {

			String sql = "SELECT * FROM Productos";

			try (Statement statement = connection.createStatement()) {

				ResultSet rs = statement.executeQuery(sql);

				while (rs.next()) {

					String nombre = rs.getString("nombre");
					String descripcion = rs.getString("descripcion");
					double peso = rs.getDouble("peso");
					int stock = rs.getInt("stock");

					Producto p = new Producto(nombre, descripcion, peso, stock);
					productos.add(p);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}

}
