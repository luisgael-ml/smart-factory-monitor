package com.nexodev;

import java.util.Scanner;

/**
 * Punto de entrada: menú para probar el CRUD de sensores.
 */
public class Main {
    public static void main(String[] args) {
        SensorDAO dao = new SensorDAO();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== SMART FACTORY MONITOR ===");
            System.out.println("1. Insertar sensor");
            System.out.println("2. Listar sensores");
            System.out.println("3. Actualizar lectura");
            System.out.println("4. Eliminar sensor");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");    String nombre = sc.nextLine();
                    System.out.print("Tipo: ");      String tipo = sc.nextLine();
                    System.out.print("Ubicación: "); String ubicacion = sc.nextLine();
                    System.out.print("Valor: ");     double valor = Double.parseDouble(sc.nextLine());
                    boolean ok = dao.insertar(new Sensor(nombre, tipo, ubicacion, valor));
                    System.out.println(ok ? "Sensor insertado." : "No se pudo insertar.");
                }
                case 2 -> {
                    System.out.printf("%-4s %-10s %-12s %-12s %8s  %s%n",
                            "ID", "NOMBRE", "TIPO", "UBICACIÓN", "VALOR", "FECHA");
                    dao.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID del sensor: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Nuevo valor: ");   double v = Double.parseDouble(sc.nextLine());
                    System.out.println(dao.actualizarValor(id, v) ? "Lectura actualizada." : "ID no encontrado.");
                }
                case 4 -> {
                    System.out.print("ID del sensor: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.println(dao.eliminar(id) ? "Sensor eliminado." : "ID no encontrado.");
                }
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        sc.close();
    }
}
