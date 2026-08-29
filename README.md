import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingresa los metros: ");
        double metros = teclado.nextDouble();

        double centimetros = metros * 100;
        double pulgadas = centimetros / 2.54;
        double pies = pulgadas / 12;
        double yardas = pies / 3;

        System.out.println("Metros: " + metros);
        System.out.println("Centimetros: " + centimetros);
        System.out.println("Pulgadas: " + pulgadas);
        System.out.println("Pies: " + pies);
        System.out.println("Yardas: " + yardas);
    }
}
