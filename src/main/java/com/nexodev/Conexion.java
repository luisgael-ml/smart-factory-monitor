package com.nexodev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase responsable de crear la conexión segura con PostgreSQL mediante JDBC.
 * - Las credenciales se leen de variables de entorno (no están escritas en el código).
 * - La conexión viaja cifrada con SSL/TLS.
 */
public class Conexion {

    // Servidor de la BD: "localhost" al correr en la PC, o el nombre del
    // servicio "postgres" cuando la app corre dentro de Docker (variable DB_HOST)
    private static final String HOST = System.getenv().getOrDefault("DB_HOST", "localhost");

    // URL JDBC: protocolo:subprotocolo://servidor:puerto/base_de_datos
    private static final String URL = "jdbc:postgresql://" + HOST + ":5432/smartfactory";

    /**
     * Abre y devuelve una conexión nueva cifrada con SSL.
     * El driver se registra solo (JDBC 4.0+) por estar en el classpath vía Maven.
     */
    public static Connection getConexion() throws SQLException {
        String password = System.getenv("DB_PASSWORD");
        if (password == null || password.isBlank()) {
            throw new SQLException("Falta la variable de entorno DB_PASSWORD");
        }

        Properties props = new Properties();
        props.setProperty("user", System.getenv().getOrDefault("DB_USER", "postgres"));
        props.setProperty("password", password);
        // require: la conexión se rechaza si el servidor no acepta SSL
        props.setProperty("sslmode", System.getenv().getOrDefault("DB_SSLMODE", "require"));

        return DriverManager.getConnection(URL, props);
    }

    /**
     * Consulta al servidor si la conexión actual va cifrada y con qué versión de TLS.
     */
    public static String estadoSSL() throws SQLException {
        String sql = "SELECT ssl, version FROM pg_stat_ssl WHERE pid = pg_backend_pid()";
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getBoolean("ssl")) {
                return "sí (" + rs.getString("version") + ")";
            }
            return "no";
        }
    }
}
