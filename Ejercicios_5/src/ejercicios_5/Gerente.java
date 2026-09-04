package ejercicios_5;

public class Gerente extends Empleado {
    private String area;

    public Gerente(String nombre, int edad, double sueldo, String area) {
        super(nombre, edad, sueldo);
        this.area = area;
    }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Area: " + area);
    }
}
