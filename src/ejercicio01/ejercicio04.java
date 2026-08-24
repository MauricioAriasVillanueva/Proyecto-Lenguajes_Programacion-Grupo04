/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio01;
import java.util.Scanner;
/**
 *
 * @author mauvi
 */
public class ejercicio04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese un numero: ");
        double numero = entrada.nextDouble();   
        double suma;
        suma = (numero * (numero + 1))/2;
        System.out.print("la suma es: "+ suma);
               
    }
}