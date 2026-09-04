
package Bankss;
public class bankPOO {
        public static void main(String[] args){
            CuentaAhorro ahorro = new CuentaAhorro(2.30, "Massiel", 350.40);
            CuentaCorriente corriente = new CuentaCorriente(1.30, "Thiago", 789.20);
            ahorro.mostrarInformacion();
            ahorro.calcularBeneficio();
            corriente.mostrarInformacion();
            corriente.calcularBeneficio();
            System.out.println();
            
            ahorro.setTitular("Alexis");
            ahorro.setSaldo(954.10);
            ahorro.setTasaInteres(2.10);
            corriente.setTitular("Nicole");
            corriente.setSaldo(2435.25);
            corriente.setComision(2.40);
            
            Cuenta c1 = ahorro;
            Cuenta c2 = corriente;
            
            c1.mostrarInformacion();
            c1.calcularBeneficio();
            c2.mostrarInformacion();
            c2.calcularBeneficio();            
        }
}
