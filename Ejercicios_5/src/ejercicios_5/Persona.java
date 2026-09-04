package ejercicios_5;

public class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public void mostrarInformacion() {
        System.out.println("--- Informacion de Persona ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
    }
}