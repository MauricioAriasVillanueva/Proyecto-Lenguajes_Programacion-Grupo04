/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio;

/**
 *
 * @author W10
 */
import java.util.Scanner;

public class Ejercicio07 {

    public static void main(String[] args) { 
        
      Scanner entrada = new Scanner(System.in);
      
     
      System.out.print("Ingrese la medida del lado: ");
      double L = entrada.nextDouble(); 
      
      double area = ( L * L * Math.sqrt(3))/4;
      
      System.out.println("El área del triángulo equilátero es: " + area);
    }
}

