/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio01;

import java.util.Scanner;
/**
 *
 * @author mauvi
 */

public class Ejercicio01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese un numero: ");
        double numero = entrada.nextDouble();
        System.out.println("El numero que coloco es: " + numero);
        double cubo;
        cubo = numero*numero*numero;
        System.out.print("El cubo del numero es: ");
        System.out.println(cubo);
               
    }
    
}
