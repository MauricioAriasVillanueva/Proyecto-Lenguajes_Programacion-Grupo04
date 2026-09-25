package factory;

import model.Medico;
import model.Paciente;
import model.Persona;

public class UsuarioFactory {

    public static Persona crearPersona(String tipo, String[] datos) {
        switch (tipo.toLowerCase()) {
            case "paciente":
                return new Paciente(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]), datos[4], datos[5]);
            case "medico":
                return new Medico(datos[0], datos[1], datos[2], datos[3], datos[4]);
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " + tipo);
        }
    }
}