
package AnimalPerro;
public class Perro extends Animal {
    
    private String raza;
    
    public Perro(String raza, String nombre, int edad) {
        super(nombre, edad);
        this.raza = raza;
    }
 
    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
    
    @Override
    public void hacerSonido(){
        System.out.println("El perro hace:Guau guau");
    }
}
