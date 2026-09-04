package ejercicios_5;

public class Empleado extends Persona {
    private double sueldo;

    public Empleado(String nombre, int edad, double sueldo) {
        super(nombre, edad);
        this.sueldo = sueldo;
    }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Sueldo: $" + sueldo);
    }
}
