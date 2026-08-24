/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio01;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Locale;

public class ejercicio03 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        DecimalFormat formato = new DecimalFormat("0.00");
        entrada.useLocale(Locale.US);
        System.out.println("Ingrese apellidos y nombres del empleado: ");
        String Apellidos_y_nombres = entrada.nextLine();
        System.out.println("Ingrese el Número de horas laboradas: ");
        double horas_laboradas = entrada.nextDouble();
        System.out.println("Ingrese el Precio por hora laborada: ");
        double Precio_por_hora = entrada.nextDouble();
        double sueldo;
        double sueldoBruto;
        double descuento;
        double descuentoLey;
        double sueldoNeto;
        sueldo = horas_laboradas * Precio_por_hora;
        sueldoBruto = sueldo;
        descuento = sueldo * 0.125;
        descuentoLey = descuento;
        sueldoNeto = sueldo - descuento;
        System.out.println("Los Nombres y Apellidos del empleado son: " + Apellidos_y_nombres);
        System.out.print("Las Horas laborales del empleado son: " + horas_laboradas);
        System.out.println("El Precio por hora laborada del empleado son: " + Precio_por_hora);
        System.out.println("El Sueldo bruto del empleado es: " + formato.format(sueldoBruto));
        System.out.println("El Descuento de ley del empleado es: " + formato.format(descuentoLey));
        System.out.println("El Sueldo neto a pagar del empleado es: " + formato.format(sueldoNeto));
               
    }
}