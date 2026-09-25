package model;

import util.SeguridadUtil;

public abstract class Persona {
    protected String dni;
    protected String dniHash;
    protected String nombre;
    protected int edad;

    public Persona(String dni, String nombre, int edad) {
        this.dni = dni;
        this.dniHash = SeguridadUtil.encriptarSHA256(dni);
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getDni() { return dni; }
    public String getDniHash() { return dniHash; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    public abstract String getRol();
    public abstract String toTxt();
}