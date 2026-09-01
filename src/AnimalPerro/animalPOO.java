
package AnimalPerro;
public class animalPOO {
    public static void main(String[] args){
            Animal mascota = new Animal("Scott", 12);
            
            Perro wanda = new Perro("labrador", "Wanda", 5);            

            

            wanda.setRaza("boxer");            
            System.out.println("El nombre es: " + mascota.getNombre());
            System.out.println("La edad es: " + mascota.getEdad());
            mascota.hacerSonido();
            System.out.println();

            System.out.println("El nombre es: " + wanda.getNombre());
            System.out.println("La edad es: " + wanda.getEdad());
            System.out.println("La raza es: " + wanda.getRaza());
            wanda.hacerSonido();       
    }
}
