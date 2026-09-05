
package com.mycompany.producto;

public class Ejercicio04 {
    
       public static void main(String[] args) {
      
        Libro libro = new Libro(
                "El Principito",
                45.50,
                "Antoine de Saint-Exupéry"
        );

        Computadora computadora = new Computadora(
                "Laptop",
                2500.00,
                "Lenovo"
        );

        libro.mostrarInformacion();

        System.out.println();

        computadora.mostrarInformacion();

        libro.setPrecio(50.00);

        System.out.println();
        System.out.println("Precio modificado:");

        libro.mostrarInformacion();

        Producto producto1 = libro;
        Producto producto2 = computadora;

        System.out.println();
        System.out.println("===== POLIMORFISMO =====");

        producto1.mostrarInformacion();

        System.out.println();

        producto2.mostrarInformacion();
    }
}
