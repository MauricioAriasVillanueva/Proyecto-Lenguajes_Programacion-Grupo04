
package ejerciciovehiculo;

public class Main {
   public static void main(String[] args) {
        Vehiculo miVehiculo = new Vehiculo("Toyota", "Corolla");
        Auto miAuto = new Auto("Honda", "Civic", 4);

        System.out.println("---Datos del Vehiculo---");
        miVehiculo.mostrarInformacion();

        System.out.println("\n---Datos del auto---");
        miAuto.mostrarInformacion();

        miAuto.setNumeroPuertas(2);

        System.out.println("\n---Datos del auto modificado---");
        miAuto.mostrarInformacion();
    }
}
