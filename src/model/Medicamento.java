package  model;

public class Medicamento {
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Medicamento(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public String toTxt() {
        return codigo + " | " + nombre + " | S/ " + precio + " | Stock: " + stock;
    }
}
