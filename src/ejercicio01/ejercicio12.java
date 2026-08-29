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
public class ejercicio12 {
    public static void main(String[] args) {
        Scanner dato = new Scanner(System.in);
        dato.useLocale(Locale.US);
        System.out.print("Indique una medida en (m): ");
        double M = dato.nextDouble();
        
        double C = 100 * M;
        double Pul = C / 2.54;
        double Pie = Pul / 12;
        double Y = Pie / 3;
        
        System.out.printf("Su equivalente en (cm): %.2f\n", C);
        System.out.printf("Su equivalente en (pulgadas): %.2f\n", Pul);
        System.out.printf("Su equivalente en (Pies): %.2f\n", Pie);
        System.out.printf("Su equivalente en (Yardas): %.2f\n", Y);
        // %.2f -> redondea a solo 2 digitos
        // \n -> fuerza el salto de linea
        // printf -> expresar texto con fromato especial de numeros, ademas no da saltos automaticos como println
        dato.close();
    }    
}
