package service;

import model.CitaMedica;
import model.Medicamento;
import model.Medico;
import model.Paciente;
import util.DatabaseSingleton;
import java.util.List;

public class GestionSaludService {

    private final DatabaseSingleton db = DatabaseSingleton.getInstancia();

    public boolean registrarPaciente(Paciente p) {
        db.getPacientesMemoria().add(p);
        return db.guardarArchivoTxt("pacientes.txt", p.toTxt());
    }

    public boolean registrarMedico(Medico m) {
        return db.guardarArchivoTxt("medicos.txt", m.toTxt());
    }

    public boolean registrarMedicamento(Medicamento med) {
        return db.guardarArchivoTxt("medicamentos.txt", med.toTxt());
    }

    public boolean registrarCita(CitaMedica c) {
        db.getCitasMemoria().add(c);
        return db.guardarArchivoTxt("citas.txt", c.toTxt());
    }

    public boolean registrarUsuario(String id, String nombre, String correo) {
        return db.guardarArchivoTxt("usuarios.txt", id + " | " + nombre + " | " + correo);
    }

    public String buscarPaciente(String dni) {
        String dniHash = util.SeguridadUtil.encriptarSHA256(dni);
        return db.buscarClaveEnTxt("pacientes.txt", dniHash);
    }

    public String buscarMedico(String id) {
        return db.buscarClaveEnTxt("medicos.txt", id);
    }

    public String buscarMedicamento(String codigo) {
        return db.buscarClaveEnTxt("medicamentos.txt", codigo);
    }

    public double calcularRecaudacionCitasTotal() {
        return db.getCitasMemoria().stream()
                .mapToDouble(CitaMedica::getCostoAtencion)
                .reduce(0.0, Double::sum);
    }

    public List<Paciente> obtenerPacientesConTriajeCompletado() {
        return db.getPacientesMemoria().stream()
                .filter(p -> "Completado".equalsIgnoreCase(p.getEstadoTriaje()))
                .toList();
    }
}
