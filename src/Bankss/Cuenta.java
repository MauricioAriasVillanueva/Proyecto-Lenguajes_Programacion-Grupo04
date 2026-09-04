
package Bankss;
public class Cuenta {
    
    private String titular;
    private double saldo;

    public Cuenta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }
    
    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public void mostrarInformacion(){
        System.out.println("El titular es " + titular + " y cuenta con un saldo de " + saldo + " soles");
    }
    
    public void calcularBeneficio(){
        System.out.println("Cálculo de beneficio general para la cuenta");        
    }
}
