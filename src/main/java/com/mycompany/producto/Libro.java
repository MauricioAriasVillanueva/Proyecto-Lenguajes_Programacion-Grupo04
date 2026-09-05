
package com.mycompany.producto;

public class Libro extends Producto {
    private String autor;

    public Libro(String nombre, double precio, String autor) {
        super(nombre, precio);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("----- LIBRO -----");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: S/ " + getPrecio());
        System.out.println("Autor: " + autor);
    } 
}
