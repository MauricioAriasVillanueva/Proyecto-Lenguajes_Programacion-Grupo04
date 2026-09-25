package model;

public class Medico extends Persona {
    private String rolCargo;
    private String especialidad;
    private String turno;

    public Medico(String id, String nombre, String rolCargo, String especialidad, String turno) {
        super(id, nombre, 0);
        this.rolCargo = rolCargo;
        this.especialidad = especialidad;
        this.turno = turno;
    }

    public String getRolCargo() { return rolCargo; }
    public String getEspecialidad() { return especialidad; }
    public String getTurno() { return turno; }

    @Override
    public String getRol() {
        return "Personal Médico - " + especialidad;
    }

    @Override
    public String toTxt() {
        return dni + " | " + nombre + " | " + rolCargo + " | " + especialidad + " | " + turno;
    }
}