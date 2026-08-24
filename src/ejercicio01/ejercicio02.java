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
public class ejercicio02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese la base: ");
        double base = entrada.nextDouble();
        System.out.print("Ingrese la altura: ");
        double altura = entrada.nextDouble();
        double area;
        area = base*altura;
        System.out.print("El area del rectangulo es: ");
        System.out.println(area);
               
    }
}