package com.nexodev;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object: concentra todas las operaciones SQL sobre la tabla sensores.
 */
public class SensorDAO {

    /** CREATE: inserta un sensor nuevo. Devuelve true si se insertó. */
    public boolean insertar(Sensor s) {
        String sql = "INSERT INTO sensores (nombre, tipo, ubicacion, valor) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getNombre());     // reemplaza el primer ?
            ps.setString(2, s.getTipo());
            ps.setString(3, s.getUbicacion());
            ps.setDouble(4, s.getValor());
            return ps.executeUpdate() > 0;      // filas afectadas
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    /** READ: devuelve todos los sensores ordenados por id. */
    public List<Sensor> listar() {
        List<Sensor> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, tipo, ubicacion, valor, fecha FROM sensores ORDER BY id";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {                 // recorre fila por fila
                Sensor s = new Sensor();
                s.setId(rs.getInt("id"));
                s.setNombre(rs.getString("nombre"));
                s.setTipo(rs.getString("tipo"));
                s.setUbicacion(rs.getString("ubicacion"));
                s.setValor(rs.getDouble("valor"));
                s.setFecha(rs.getTimestamp("fecha"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    /** UPDATE: actualiza el valor de lectura de un sensor por id. */
    public boolean actualizarValor(int id, double nuevoValor) {
        String sql = "UPDATE sensores SET valor = ?, fecha = NOW() WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, nuevoValor);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    /** DELETE: elimina un sensor por id. */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM sensores WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}
