package util;

import model.CitaMedica;
import model.Paciente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSingleton {

    private static DatabaseSingleton instancia;
    private final List<Paciente> pacientesMemoria;
    private final List<CitaMedica> citasMemoria;

    private DatabaseSingleton() {
        pacientesMemoria = new ArrayList<>();
        citasMemoria = new ArrayList<>();
    }

    public static synchronized DatabaseSingleton getInstancia() {
        if (instancia == null) {
            instancia = new DatabaseSingleton();
        }
        return instancia;
    }

    public synchronized boolean guardarArchivoTxt(String nombreArchivo, String linea) {
        try (FileWriter fw = new FileWriter(nombreArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error I/O en " + nombreArchivo + ": " + e.getMessage());
            return false;
        }
    }

    public synchronized String buscarClaveEnTxt(String nombreArchivo, String clave) {
        File file = new File(nombreArchivo);
        if (!file.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(clave)) {
                    return linea;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer " + nombreArchivo + ": " + e.getMessage());
        }
        return null;
    }

    public List<Paciente> getPacientesMemoria() { return pacientesMemoria; }
    public List<CitaMedica> getCitasMemoria() { return citasMemoria; }
}