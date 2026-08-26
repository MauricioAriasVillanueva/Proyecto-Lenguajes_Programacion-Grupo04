/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio01;

import java.util.Scanner;
import java.util.Locale;

/**
 *
 * @author mauvi
 */
public class ejercicio05 {
    public static void main(String[] args) {
      Scanner dato = new Scanner(System.in);  
      dato.useLocale(Locale.US);
      System.out.print("Indique la Distancia recorrida (Km): ");
      double D = dato.nextDouble();
      System.out.print("Indique el tiempo (Hr): ");
      double T = dato.nextDouble();
      
      double V = D / T;
      
      System.out.print("La velocidad promedio es de: " + V);
      dato.close();
    }
}
