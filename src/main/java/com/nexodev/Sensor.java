package com.nexodev;

import java.sql.Timestamp;

/**
 * Modelo de datos: cada objeto equivale a una fila de la tabla sensores.
 */
public class Sensor {
    private int id;
    private String nombre;
    private String tipo;
    private String ubicacion;
    private double valor;
    private Timestamp fecha;

    public Sensor() { }

    public Sensor(String nombre, String tipo, String ubicacion, double valor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.valor = valor;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return String.format("%-4d %-10s %-12s %-12s %8.2f  %s",
                id, nombre, tipo, ubicacion, valor, fecha);
    }
}
