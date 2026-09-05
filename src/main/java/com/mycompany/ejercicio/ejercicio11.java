/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio;

/**
 *
 * @author W10
 */
import java.util.Scanner; 

public class ejercicio11 {
    
    public static void main(String[]args){
   
      Scanner entrada = new Scanner(System.in);
      System.out.print("Ingrese la cantidad de horas: ");
      
      double horas = entrada.nextDouble();
      
      double minutos = horas * 60;
      double segundos = horas * 3600;
      
      System.out.println("Minutos: " + minutos);
      System.out.println("Segundos: " + segundos);
  }         
}
