package areatriangulo;

import java.util.Scanner;

public class AreaTriangulo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada de datos
        System.out.print("Ingrese el lado A: ");
        double a = scanner.nextDouble();

        System.out.print("Ingrese el lado B: ");
        double b = scanner.nextDouble();

        System.out.print("Ingrese el lado C: ");
        double c = scanner.nextDouble();

        // Validación de la desigualdad triangular
        if ((a + b > c) && (a + c > b) && (b + c > a)) {
            // Semiperímetro
            double p = (a + b + c) / 2.0;

            // Fórmula de Herón
            double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));

            System.out.printf("\nEl semiperímetro (P) es: %.2f\n", p);
            System.out.printf("El área del triángulo es: %.2f\n", area);
        } else {
            System.out.println("\nError: Los lados ingresados no forman un triángulo válido.");
        }

        scanner.close();
    }
}

    

