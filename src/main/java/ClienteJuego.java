import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cliente TCP para el juego "Memoriza la Secuencia".
 * <p>
 * Este cliente se conecta a un servidor de juego y maneja la comunicación
 * bidireccional, mostrando los mensajes del servidor y enviando las
 * respuestas del usuario.
 * </p>
 * 
 * @author Estudiante
 * @version 1.0
 * @see ServidorJuego
 */
public class ClienteJuego {

    /**
     * Punto de entrada principal del cliente.
     * <p>
     * Conecta al servidor en localhost:9876 y maneja el protocolo
     * de comunicación del juego.
     * </p>
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        String host = "localhost";
        int puerto = 9876;

        // Variable de control para el bucle While
        boolean conectado = true;

        System.out.println("-----INICIANDO CLIENTE-----");

        try (Socket socket = new Socket(host, puerto);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner teclado = new Scanner(System.in)) {

            String mensajeBienvenida = in.readLine();
            System.out.println("SERVIDOR DICE: " + mensajeBienvenida);

            System.out.println("INSTRUCCIONES DEL JUEGO: memoriza la secuencia de números, en cada ronda" +
                    "se sumará un número a la cifra anterior, además de limpiar la terminal");

            // Bucle de comunicación controlada
            while (conectado) {
                String mensaje = in.readLine();
                if (mensaje == null) {
                    conectado = false;
                } else if (mensaje.equals("INGRESA_SECUENCIA")) {
                    // El servidor pide la secuencia, esperar input del usuario
                    System.out.print("> ");
                    String respuesta = teclado.nextLine();
                    out.println(respuesta);
                } else if (mensaje.equals("PREGUNTA_REINICIO")) {
                    // El servidor pregunta si quiere volver a jugar
                    System.out.print("> ");
                    String respuesta = teclado.nextLine();
                    out.println(respuesta);
                } else if (mensaje.equals("FIN_JUEGO")) {
                    // El servidor indica que el juego terminó
                    conectado = false;
                } else {
                    System.out.println(mensaje);
                }
            }

        } catch (ConnectException e) {
            System.err.println("Error: No se pudo conectar al servidor. ¿Está encendido?");
        } catch (IOException e) {
            System.err.println("Error de comunicación: " + e.getMessage());
        }
        System.out.println("--- Cliente desconectado ---");
    }
}
