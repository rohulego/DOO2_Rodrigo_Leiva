package dao;

import models.Entrega;

import java.sql.*;

public class EntregaDAO {

    public boolean guardar(Entrega entrega) {
        String sql = "INSERT INTO entrega (id_pedido, id_repartidor) VALUES (?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        entrega.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar la entrega: " + e.getMessage());
        }
        return false;
    }
}