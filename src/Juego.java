import java.util.ArrayList;
import java.util.List;

public class Juego {
    List<Integer> secuencia = new ArrayList<Integer>(8, 3, 5);
    String respuesta;
    Boolean pasaNivel;
    String mensaje;


    public Juego() {
    }

    public Juego(List<Integer> secuencia, String mensaje, String respuesta, Boolean pasaNivel) {
        this.secuencia = secuencia;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.pasaNivel = pasaNivel;
    }

    public List<Integer> getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(List<Integer> secuencia) {
        this.secuencia = secuencia;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Boolean getPasaNivel() {
        return pasaNivel;
    }

    public void setPasaNivel(Boolean pasaNivel) {
        this.pasaNivel = pasaNivel;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
