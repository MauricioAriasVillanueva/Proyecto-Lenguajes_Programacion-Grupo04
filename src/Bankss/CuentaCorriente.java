package Bankss;

public class CuentaCorriente extends Cuenta {
    private double comision;

    public CuentaCorriente(double comision, String titular, double saldo) {
        super(titular, saldo);
        this.comision = comision;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @Override
    public void calcularBeneficio() {
        System.out.println(getTitular() + " mantiene una comisión mensual fijada de " + comision + " soles");
    }
}