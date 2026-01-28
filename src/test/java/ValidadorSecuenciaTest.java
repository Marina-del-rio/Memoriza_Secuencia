import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests unitarios para la lógica de validación de respuestas.
 * Dado que el método validarRespuesta en ClienteHandler es privado,
 * estos tests prueban la lógica de validación de forma aislada.
 */
public class ValidadorSecuenciaTest {

    private List<Integer> secuencia;

    @BeforeEach
    void setUp() {
        secuencia = new ArrayList<>(Arrays.asList(8, 3, 5));
    }

    /**
     * Método auxiliar que replica la lógica de validación de ClienteHandler.
     * Compara una respuesta del usuario con la secuencia esperada.
     * 
     * @param respuesta la respuesta del usuario separada por comas
     * @param secuencia la secuencia correcta
     * @return true si la respuesta coincide con la secuencia
     */
    private boolean validarRespuesta(String respuesta, List<Integer> secuencia) {
        try {
            String[] partes = respuesta.trim().split(",");

            if (partes.length != secuencia.size()) {
                return false;
            }

            for (int i = 0; i < partes.length; i++) {
                int numero = Integer.parseInt(partes[i].trim());
                if (numero != secuencia.get(i)) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica que una respuesta correcta es validada como correcta.
     */
    @Test
    void testRespuestaCorrecta() {
        assertTrue(validarRespuesta("8,3,5", secuencia),
                "La respuesta '8,3,5' debe ser correcta");
    }

    /**
     * Verifica que una respuesta correcta con espacios es validada.
     */
    @Test
    void testRespuestaCorrectaConEspacios() {
        assertTrue(validarRespuesta("8, 3, 5", secuencia),
                "La respuesta '8, 3, 5' debe ser correcta");
        assertTrue(validarRespuesta(" 8 , 3 , 5 ", secuencia),
                "La respuesta con espacios extras debe ser correcta");
    }

    /**
     * Verifica que una respuesta incorrecta es rechazada.
     */
    @Test
    void testRespuestaIncorrecta() {
        assertFalse(validarRespuesta("8,3,6", secuencia),
                "La respuesta '8,3,6' debe ser incorrecta");
        assertFalse(validarRespuesta("1,2,3", secuencia),
                "La respuesta '1,2,3' debe ser incorrecta");
    }

    /**
     * Verifica que una respuesta con números de menos es rechazada.
     */
    @Test
    void testRespuestaConMenosNumeros() {
        assertFalse(validarRespuesta("8,3", secuencia),
                "Una respuesta con menos números debe ser incorrecta");
    }

    /**
     * Verifica que una respuesta con números de más es rechazada.
     */
    @Test
    void testRespuestaConMasNumeros() {
        assertFalse(validarRespuesta("8,3,5,7", secuencia),
                "Una respuesta con más números debe ser incorrecta");
    }

    /**
     * Verifica que una respuesta vacía es rechazada.
     */
    @Test
    void testRespuestaVacia() {
        assertFalse(validarRespuesta("", secuencia),
                "Una respuesta vacía debe ser incorrecta");
    }

    /**
     * Verifica que una respuesta con formato inválido es rechazada.
     */
    @Test
    void testRespuestaFormatoInvalido() {
        assertFalse(validarRespuesta("ocho,tres,cinco", secuencia),
                "Una respuesta con texto debe ser incorrecta");
        assertFalse(validarRespuesta("abc", secuencia),
                "Una respuesta con caracteres debe ser incorrecta");
    }

    /**
     * Verifica que la validación funciona con una secuencia más larga.
     */
    @Test
    void testSecuenciaMasLarga() {
        List<Integer> secuenciaLarga = Arrays.asList(8, 3, 5, 42, 17, 99);
        assertTrue(validarRespuesta("8,3,5,42,17,99", secuenciaLarga),
                "La respuesta debe coincidir con la secuencia larga");
        assertFalse(validarRespuesta("8,3,5,42,17,100", secuenciaLarga),
                "Un número incorrecto debe fallar");
    }
}
