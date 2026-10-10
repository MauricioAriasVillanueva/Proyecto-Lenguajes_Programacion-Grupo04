
package Pruebas;

import java.util.List;
import model.CitaMedica;
import org.junit.Test;
import static org.junit.Assert.*;
import service.GestionSaludService;
import util.CifradoDni;
import util.DatabaseSingleton;
import util.SeguridadUtil;

public class SistemaSaludTest {

    // PRUEBA 1: Seguridad SHA-256
    @Test
    public void pruebaHashDNI() {
        String dni = "12345678";

        String hash = SeguridadUtil.encriptarSHA256(dni);

        assertEquals(64, hash.length());
        assertEquals(
            "ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f",
            hash
        );
        assertNotEquals(dni, hash);
    }

    // PRUEBA 2: Cifrado y descifrado AES-GCM
    @Test
    public void pruebaCifradoDNI() {
        String dni = "12345678";

        String cifrado = CifradoDni.cifrar(dni);
        String recuperado = CifradoDni.descifrar(cifrado);

        assertNotEquals(dni, cifrado);
        assertEquals(dni, recuperado);
    }

    // PRUEBA 3: Calcular recaudación de citas
    @Test
    public void pruebaRecaudacionCitas() {

        GestionSaludService servicio =
            new GestionSaludService();

        List<CitaMedica> citas =
            DatabaseSingleton.getInstancia().getCitasMemoria();

        double anterior =
            servicio.calcularRecaudacionCitasTotal();

        CitaMedica cita1 = new CitaMedica(
            "12345678", "DOC01", "MED01", 12.00
        );

        CitaMedica cita2 = new CitaMedica(
            "87654321", "DOC02", "MED02", 25.50
        );

        try {
            citas.add(cita1);
            citas.add(cita2);

            double esperado = anterior + 37.50;

            assertEquals(
                esperado,
                servicio.calcularRecaudacionCitasTotal(),
                0.001
            );

        } finally {
            citas.remove(cita1);
            citas.remove(cita2);
        }
    }
}
