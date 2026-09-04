package ejercicio10;

import java.util.Scanner;

public class Ejercicio10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada de datos
        System.out.print("Ingrese el capital inicial (C): ");
        double capital = scanner.nextDouble();

        System.out.print("Ingrese la tasa de interés por período en % (T): ");
        double tasaPorcentaje = scanner.nextDouble();

        System.out.print("Ingrese el número de períodos (N): ");
        int periodos = scanner.nextInt();

        // Conversión de tasa porcentual a decimal
        double tasaDecimal = tasaPorcentaje / 100.0;

        // Cálculo del monto acumulado: M = C * (1 + T)^N
        double montoAcumulado = capital * Math.pow(1 + tasaDecimal, periodos);

        // Cálculo del interés generado: I = M - C
        double interesGenerado = montoAcumulado - capital;

        // Salida de resultados
        System.out.println("\n================ RESUMEN ================");
        System.out.printf("Capital inicial:        S/ %.2f\n", capital);
        System.out.printf("Tasa de interés:        %.2f%%\n", tasaPorcentaje);
        System.out.printf("Número de períodos:     %d\n", periodos);
        System.out.printf("Monto acumulado:        S/ %.2f\n", montoAcumulado);
        System.out.printf("Interés generado:       S/ %.2f\n", interesGenerado);
        System.out.println("=========================================");

        scanner.close();
    }
}