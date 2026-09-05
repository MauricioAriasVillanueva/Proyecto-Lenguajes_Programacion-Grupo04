
package com.mycompany.producto;

public class Computadora extends Producto {
    private String marca;

    public Computadora(String nombre, double precio, String marca) {
        super(nombre, precio);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("----- COMPUTADORA -----");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: S/ " + getPrecio());
        System.out.println("Marca: " + marca);
    }
}
