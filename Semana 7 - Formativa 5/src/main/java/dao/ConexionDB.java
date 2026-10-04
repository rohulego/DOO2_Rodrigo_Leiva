package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/speedfast_db?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "my-secret-pw";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void inicializarBaseDeDatos() {
        String sqlRepartidor = "CREATE TABLE IF NOT EXISTS repartidor ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL"
                + ");";

        String sqlPedido = "CREATE TABLE IF NOT EXISTS pedido ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "direccion VARCHAR(150) NOT NULL, "
                + "tipo VARCHAR(30) NOT NULL, "
                + "estado VARCHAR(20) NOT NULL"
                + ");";

        String sqlEntrega = "CREATE TABLE IF NOT EXISTS entrega ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "id_pedido INT NOT NULL, "
                + "id_repartidor INT NOT NULL, "
                + "fecha_entrega TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + "FOREIGN KEY (id_pedido) REFERENCES pedido(id), "
                + "FOREIGN KEY (id_repartidor) REFERENCES repartidor(id)"
                + ");";

        try (Connection con = conectar();
             Statement stmt = con.createStatement()) {

            stmt.execute(sqlRepartidor);
            stmt.execute(sqlPedido);
            stmt.execute(sqlEntrega);
            System.out.println("¡Base de datos y tablas creadas/verificadas con éxito!");

        } catch (SQLException e) {
            System.err.println("Error al inicializar las tablas: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        inicializarBaseDeDatos();
    }
}