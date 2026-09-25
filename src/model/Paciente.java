package model;

public class Paciente extends Persona {
    private String apellidos;
    private String telefono;
    private String seguro;
    private String presionArterial;
    private double temperatura;
    private double peso;
    private String estadoTriaje;

    public Paciente(String dni, String apellidos, String nombre, int edad, String telefono, String seguro) {
        super(dni, nombre, edad);
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.seguro = seguro;
        this.presionArterial = "Sin registrar";
        this.temperatura = 0.0;
        this.peso = 0.0;
        this.estadoTriaje = "Pendiente";
    }

    public String getApellidos() { return apellidos; }
    public String getTelefono() { return telefono; }
    public String getSeguro() { return seguro; }
    public String getPresionArterial() { return presionArterial; }
    public void setPresionArterial(String presionArterial) { this.presionArterial = presionArterial; }
    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public String getEstadoTriaje() { return estadoTriaje; }
    public void setEstadoTriaje(String estadoTriaje) { this.estadoTriaje = estadoTriaje; }

    @Override
    public String getRol() {
        return "Paciente";
    }

    @Override
    public String toTxt() {
        return dni + " | " + apellidos + " | " + nombre + " | Edad: " + edad + " | Tel: " + telefono + " | " + seguro + " | Triaje: " + estadoTriaje;
    }
}