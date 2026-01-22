import java.util.List;

public class Juego {
    List<Integer> secuencia;
    List<Integer> respuesta;
    Boolean pasaNivel;
    String mensaje;


    public Juego() {
    }

    public Juego(List<Integer> secuencia, String mensaje) {
        this.secuencia = secuencia;
        this.mensaje = mensaje;
    }

    public List<Integer> getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(List<Integer> secuencia) {
        this.secuencia = secuencia;
    }

    public List<Integer> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<Integer> respuesta) {
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
