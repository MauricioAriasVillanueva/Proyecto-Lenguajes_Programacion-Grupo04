/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author glori
 */
package javaapplication3;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Ejercicio6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir la temperatura en grados Celsius
        System.out.print("Ingrese la temperatura en grados Celsius (°C): ");
        double celsius = scanner.nextDouble();

        // Aplicar la fórmula: F = 1.8 * C + 32
        double fahrenheit = (1.8 * celsius) + 32;

        // Formato para mostrar dos decimales
        DecimalFormat df = new DecimalFormat("0.00");

        // Mostrar el resultado
        System.out.println("Temperatura en Fahrenheit: " + df.format(fahrenheit) + " °F");

        scanner.close();
    }
}
            

