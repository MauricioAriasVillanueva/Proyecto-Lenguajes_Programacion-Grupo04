package ejercicios_5;

public class Ejercicios_5 {
    public static void main(String[] args) {
        // 1. Creación de objetos de cada tipo
        Persona persona = new Persona("Carlos", 45);
        Empleado empleado = new Empleado("Maximo", 30, 2500.0);
        Gerente gerente = new Gerente("Luis", 40, 5000.0, "Tecnologia");

        System.out.println("=== MOSTRAR INFORMACION INICIAL ===");
        persona.mostrarInformacion();
        System.out.println();
        empleado.mostrarInformacion();
        System.out.println();
        gerente.mostrarInformacion();
        System.out.println();

        // 2. Modificación de datos mediante setters
        System.out.println("=== MODIFICANDO DATOS VIA SETTERS ===");
        persona.setEdad(46);
        empleado.setSueldo(2800.0);
        gerente.setArea("Innovacion y Desarrollo");

        persona.mostrarInformacion();
        System.out.println();
        empleado.mostrarInformacion();
        System.out.println();
        gerente.mostrarInformacion();
        System.out.println();

        // 3. Demostración de Polimorfismo
        System.out.println("=== DEMOSTRACION DE POLIMORFISMO ===");
        Persona p1 = new Empleado("Laura", 28, 2200.0);
        Persona p2 = new Gerente("Roberto", 50, 6000.0, "Operaciones");

        Persona[] personal = { persona, p1, p2 };

        for (Persona p : personal) {
            p.mostrarInformacion();
            System.out.println("--------------------------------");
        }
    }
}
