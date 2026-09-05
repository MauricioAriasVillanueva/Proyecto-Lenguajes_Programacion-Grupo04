
package ejerciciovehiculo;

public class Vehiculo {

    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
  public void mostrarInformacion(){
      System.out.println("Marca:" + marca);
      System.out.println("Modelo:" + modelo);
      
  }

    private String marca;
    private String modelo;
    
    
}
