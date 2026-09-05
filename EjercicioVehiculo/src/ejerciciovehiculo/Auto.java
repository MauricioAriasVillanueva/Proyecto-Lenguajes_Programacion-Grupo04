
package ejerciciovehiculo;

public class Auto extends Vehiculo {
    private int numeroPuertas;

    // El constructor DEBE ser public
    public Auto(String marca, String modelo, int numeroPuertas) {
        super(marca, modelo);
        this.numeroPuertas = numeroPuertas;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Número de puertas: " + numeroPuertas);
        
   }
    
}
