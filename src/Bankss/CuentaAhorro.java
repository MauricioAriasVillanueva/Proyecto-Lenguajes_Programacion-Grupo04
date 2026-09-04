
package Bankss;
public class CuentaAhorro extends Cuenta {
    
    private double tasaInteres;
 
    public CuentaAhorro(double tasaInteres, String titular, double saldo) {
        super(titular, saldo);
        this.tasaInteres = tasaInteres;
    }
    
    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
    
    @Override
    public void calcularBeneficio(){
                System.out.println(getTitular() + " genera rendimientos con una tasa de interés del " + tasaInteres + "%.");
    }
}
