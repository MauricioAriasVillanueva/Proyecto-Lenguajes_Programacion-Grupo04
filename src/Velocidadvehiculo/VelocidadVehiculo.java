/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Velocidadvehiculo;

/**
 *
 * @author Estudiante
 */

    import java.util.Scanner;

public class VelocidadVehiculo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== CÁLCULO DE VELOCIDAD PROMEDIO ===");
        
        // 1. Solicitar la distancia
        System.out.print("Ingrese la distancia recorrida (en km): ");
        double distancia = scanner.nextDouble();
        
        // 2. Solicitar el tiempo
        System.out.print("Ingrese el tiempo empleado (en horas): ");
        double tiempo = scanner.nextDouble();
        
        // 3. Validar que el tiempo sea mayor a cero para evitar errores matemáticos
        if (tiempo > 0) {
            // 4. Calcular la velocidad
            double velocidad = distancia / tiempo;
            
            // 5. Mostrar el resultado formateado como xxxxxxx.xx km / hr
            // Usamos %.2f para asegurar dos decimales
            System.out.printf("Velocidad promedio: %11.2f km / hr\n", velocidad);
        } else {
            System.out.println("Error: El tiempo debe ser mayor que cero.");
        }
        
        scanner.close();
    }
}

