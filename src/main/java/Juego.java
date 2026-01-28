import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase modelo que representa el estado de un juego de "Memoriza la Secuencia".
 * <p>
 * Esta clase almacena la secuencia de números que el jugador debe memorizar,
 * así como información sobre el estado del juego (respuesta del usuario,
 * si pasó el nivel, y mensajes).
 * </p>
 * 
 * @author Estudiante
 * @version 1.0
 */
public class Juego {

    /** Lista de números que conforman la secuencia a memorizar */
    List<Integer> secuencia = new ArrayList<Integer>(Arrays.asList(8, 3, 5));

    /** Respuesta proporcionada por el jugador */
    String respuesta;

    /** Indica si el jugador pasó el nivel actual */
    Boolean pasaNivel;

    /** Mensaje informativo para el jugador */
    String mensaje;

    /**
     * Constructor por defecto.
     * Inicializa la secuencia con los valores [8, 3, 5].
     */
    public Juego() {
    }

    /**
     * Constructor con todos los parámetros.
     * 
     * @param secuencia lista de números que conforman la secuencia
     * @param mensaje   mensaje informativo para el jugador
     * @param respuesta respuesta proporcionada por el jugador
     * @param pasaNivel indica si el jugador pasó el nivel
     */
    public Juego(List<Integer> secuencia, String mensaje, String respuesta, Boolean pasaNivel) {
        this.secuencia = secuencia;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.pasaNivel = pasaNivel;
    }

    /**
     * Obtiene la secuencia actual del juego.
     * 
     * @return lista de números de la secuencia
     */
    public List<Integer> getSecuencia() {
        return secuencia;
    }

    /**
     * Establece una nueva secuencia para el juego.
     * 
     * @param secuencia nueva lista de números
     */
    public void setSecuencia(List<Integer> secuencia) {
        this.secuencia = secuencia;
    }

    /**
     * Obtiene la respuesta del jugador.
     * 
     * @return cadena con la respuesta del jugador
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la respuesta del jugador.
     * 
     * @param respuesta respuesta proporcionada por el jugador
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Indica si el jugador pasó el nivel.
     * 
     * @return true si pasó el nivel, false en caso contrario
     */
    public Boolean getPasaNivel() {
        return pasaNivel;
    }

    /**
     * Establece si el jugador pasó el nivel.
     * 
     * @param pasaNivel true si pasó, false si no
     */
    public void setPasaNivel(Boolean pasaNivel) {
        this.pasaNivel = pasaNivel;
    }

    /**
     * Obtiene el mensaje actual del juego.
     * 
     * @return mensaje informativo
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece un mensaje para el juego.
     * 
     * @param mensaje nuevo mensaje informativo
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
