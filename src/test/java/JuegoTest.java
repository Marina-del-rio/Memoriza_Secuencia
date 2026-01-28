import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Tests unitarios para la clase Juego.
 */
public class JuegoTest {

    private Juego juego;

    @BeforeEach
    void setUp() {
        juego = new Juego();
    }

    /**
     * Verifica que el constructor por defecto inicializa la secuencia con [8, 3,
     * 5].
     */
    @Test
    void testConstructorPorDefecto_SecuenciaInicial() {
        List<Integer> secuenciaEsperada = Arrays.asList(8, 3, 5);
        assertEquals(secuenciaEsperada, juego.getSecuencia(),
                "La secuencia inicial debe ser [8, 3, 5]");
    }

    /**
     * Verifica que el constructor con parámetros asigna correctamente los valores.
     */
    @Test
    void testConstructorConParametros() {
        List<Integer> secuencia = Arrays.asList(1, 2, 3);
        Juego juegoPersonalizado = new Juego(secuencia, "mensaje", "respuesta", true);

        assertEquals(secuencia, juegoPersonalizado.getSecuencia());
        assertEquals("mensaje", juegoPersonalizado.getMensaje());
        assertEquals("respuesta", juegoPersonalizado.getRespuesta());
        assertTrue(juegoPersonalizado.getPasaNivel());
    }

    /**
     * Verifica que setSecuencia actualiza correctamente la secuencia.
     */
    @Test
    void testSetSecuencia() {
        List<Integer> nuevaSecuencia = Arrays.asList(10, 20, 30);
        juego.setSecuencia(nuevaSecuencia);
        assertEquals(nuevaSecuencia, juego.getSecuencia());
    }

    /**
     * Verifica que setRespuesta y getRespuesta funcionan correctamente.
     */
    @Test
    void testSetGetRespuesta() {
        juego.setRespuesta("8,3,5");
        assertEquals("8,3,5", juego.getRespuesta());
    }

    /**
     * Verifica que setPasaNivel y getPasaNivel funcionan correctamente.
     */
    @Test
    void testSetGetPasaNivel() {
        juego.setPasaNivel(true);
        assertTrue(juego.getPasaNivel());

        juego.setPasaNivel(false);
        assertFalse(juego.getPasaNivel());
    }

    /**
     * Verifica que setMensaje y getMensaje funcionan correctamente.
     */
    @Test
    void testSetGetMensaje() {
        juego.setMensaje("Bienvenido al juego");
        assertEquals("Bienvenido al juego", juego.getMensaje());
    }

    /**
     * Verifica que la secuencia puede ser modificada (es mutable).
     */
    @Test
    void testSecuenciaMutable() {
        juego.getSecuencia().add(99);
        assertTrue(juego.getSecuencia().contains(99),
                "La secuencia debe permitir añadir elementos");
    }
}
