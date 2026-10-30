package com.nexodev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase responsable de crear la conexión con PostgreSQL mediante JDBC.
 */
public class Conexion {

    // Servidor de la BD: "localhost" al correr en la PC, o el nombre del
    // servicio "postgres" cuando la app corre dentro de Docker (variable DB_HOST)
    private static final String HOST = System.getenv().getOrDefault("DB_HOST", "localhost");

    // URL JDBC: protocolo:subprotocolo://servidor:puerto/base_de_datos
    private static final String URL = "jdbc:postgresql://" + HOST + ":5432/smartfactory";
    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "admin123";

    /**
     * Abre y devuelve una conexión nueva.
     * El driver se registra solo (JDBC 4.0+) por estar en el classpath vía Maven.
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
