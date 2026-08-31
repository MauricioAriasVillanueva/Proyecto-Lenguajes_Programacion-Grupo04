
package AnimalPerro;
public class animalPOO {
    public static void main(String[] args){
            Animal mascota = new Animal("Scott", 12);
            mascota.hacerSonido();
            System.out.println();
            
            Perro wanda = new Perro("Boxer", "Wanda", 5);
            wanda.setRaza("labrador");            
            System.out.println("El nombre de mi primera mascota es: " + mascota.getNombre());
            System.out.println("La edad de mi primera mascota es: " + mascota.getEdad());
            System.out.println("La raza de mi primera mascota es: " + wanda.getRaza());
            System.out.println("El nombre de mi ultima mascota es: " + wanda.getNombre());
            System.out.println("La edad de mi ultima mascota es: " + wanda.getEdad());
            wanda.hacerSonido();       
    }
}
