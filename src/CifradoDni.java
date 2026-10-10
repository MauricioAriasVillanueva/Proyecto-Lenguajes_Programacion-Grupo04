package util;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CifradoDni {

    private static final Path ARCHIVO_CLAVE = Paths.get(
        System.getProperty("user.home"), ".campoy", "dni.key"
    );

    private static byte[] obtenerClave() throws Exception {
        Files.createDirectories(ARCHIVO_CLAVE.getParent());

        if (!Files.exists(ARCHIVO_CLAVE)) {
            byte[] clave = new byte[32];
            new SecureRandom().nextBytes(clave);

            Files.write(ARCHIVO_CLAVE, clave,
                StandardOpenOption.CREATE_NEW);
        }

        return Files.readAllBytes(ARCHIVO_CLAVE);
    }

    public static String cifrar(String dni) {
        try {
            byte[] iv = new byte[12];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            cipher.init(
                Cipher.ENCRYPT_MODE,
                new SecretKeySpec(obtenerClave(), "AES"),
                new GCMParameterSpec(128, iv)
            );

            byte[] cifrado = cipher.doFinal(
                dni.getBytes(StandardCharsets.UTF_8)
            );

            byte[] resultado = new byte[iv.length + cifrado.length];

            System.arraycopy(iv, 0, resultado, 0, iv.length);
            System.arraycopy(cifrado, 0, resultado, iv.length,
                cifrado.length);

            return Base64.getEncoder().encodeToString(resultado);

        } catch (Exception e) {
            throw new IllegalStateException(
                "No se pudo cifrar el DNI", e);
        }
    }

    public static String descifrar(String texto) {
        try {
            byte[] datos = Base64.getDecoder().decode(texto);

            byte[] iv = Arrays.copyOfRange(datos, 0, 12);
            byte[] cifrado = Arrays.copyOfRange(
                datos, 12, datos.length);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            cipher.init(
                Cipher.DECRYPT_MODE,
                new SecretKeySpec(obtenerClave(), "AES"),
                new GCMParameterSpec(128, iv)
            );

            return new String(
                cipher.doFinal(cifrado),
                StandardCharsets.UTF_8
            );

        } catch (Exception e) {
            throw new IllegalStateException(
                "No se pudo recuperar el DNI", e);
        }
    }
}
