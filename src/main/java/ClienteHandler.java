import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Random;

/**
 * Manejador de clientes para el juego "Memoriza la Secuencia".
 * <p>
 * Implementa {@link Runnable} para ejecutarse en un hilo separado.
 * Maneja toda la lógica del juego para un cliente específico:
 * </p>
 * <ul>
 * <li>Muestra la secuencia al jugador</li>
 * <li>Espera y valida la respuesta</li>
 * <li>Añade números nuevos al pasar de nivel</li>
 * <li>Permite reiniciar el juego tras perder</li>
 * </ul>
 * 
 * @author Estudiante
 * @version 1.0
 * @see ServidorJuego
 */
public class ClienteHandler implements Runnable {

    /** Socket de conexión con el cliente */
    private final Socket socket;

    /** Generador de números aleatorios para añadir a la secuencia */
    private Random generador;

    /** Instancia del juego para este cliente */
    Juego juego;

    /** Referencia a la secuencia actual del juego */
    List<Integer> secuencia;

    /**
     * Crea un nuevo manejador para un cliente.
     * 
     * @param socket socket de conexión con el cliente
     */
    public ClienteHandler(Socket socket) {
        this.socket = socket;
        this.generador = new Random();
        this.juego = new Juego();
        this.secuencia = juego.getSecuencia();
    }

    /**
     * Añade un nuevo número aleatorio (1-100) a la secuencia.
     * Este método se llama cada vez que el jugador pasa un nivel.
     */
    private void agregarNuevoNumero() {
        // Genera un número del 1 al 100
        int nuevoNumero = generador.nextInt(100) + 1;
        // Lo guarda en la lista
        secuencia.add(nuevoNumero);
    }

    /**
     * Ejecuta el bucle principal del juego para este cliente.
     * <p>
     * El flujo del juego es:
     * </p>
     * <ol>
     * <li>Enviar mensaje de bienvenida</li>
     * <li>Mostrar la secuencia durante 5 segundos</li>
     * <li>Limpiar pantalla y pedir la secuencia</li>
     * <li>Validar respuesta</li>
     * <li>Si acierta: añadir número y repetir</li>
     * <li>Si falla: preguntar si quiere reiniciar</li>
     * </ol>
     */
    @Override
    public void run() {
        // Usamos Try-with-resources para asegurar el cierre del socket
        try (socket; // Java 9+ permite referenciar la variable final aquí
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("*****BIENVENID@ A MEMORIZA LA SECUENCIA*****");

            boolean jugando = true;

            // Bucle principal del juego
            while (jugando) {
                // Enviar la secuencia actual al cliente
                out.println("SECUENCIA: " + secuencia.toString());
                out.println("Memoriza la secuencia, tienes 5 segundos TikTak TikTak");

                // Esperar 5 segundos para que memorice
                Thread.sleep(5000);

                // Limpiar pantalla (50 saltos de línea)
                for (int i = 0; i < 50; i++) {
                    out.println("");
                }

                // Pedir al cliente que ingrese la secuencia
                out.println("Escribe la secuencia separada por comas (ej: 2,1,9):");
                out.println("INGRESA_SECUENCIA"); // Señal para que el cliente pida input

                // Esperar la respuesta del cliente (viene por el socket, no del teclado)
                String respuesta = in.readLine();

                if (respuesta == null) {
                    // Cliente desconectado
                    break;
                }

                // Validar la respuesta
                if (validarRespuesta(respuesta)) {
                    out.println("Pasas al siguiente nivel.");
                    out.println("Se añade un nuevo número a la secuencia:");
                    agregarNuevoNumero();
                    Thread.sleep(2000); // Pausa para que lea el mensaje
                } else {
                    // El jugador perdió
                    out.println("Has perdido...Tu Madre está muy decepcionada contigo");
                    out.println("La secuencia correcta era: " + secuencia.toString());
                    out.println("¿Quieres volver a jugar? (SI/NO):");
                    out.println("PREGUNTA_REINICIO"); // Señal para que el cliente pida input

                    // Esperar respuesta de reinicio
                    String respuestaReinicio = in.readLine();

                    if (respuestaReinicio == null) {
                        break;
                    }

                    if (respuestaReinicio.trim().equalsIgnoreCase("SI")) {
                        // Reiniciar el juego
                        reiniciarJuego();
                        out.println("Juego reiniciado, comenzamos de nuevo, a ver si mejoras un poco...");
                        Thread.sleep(2000);
                    } else {
                        // Terminar el juego
                        out.println("Gracias por jugar, no vuelvas");
                        out.println("FIN_JUEGO");
                        jugando = false;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado tras error.");
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido.");
        } finally {
            System.out.println("Cliente finalizado: " + socket.getInetAddress());
        }
    }

    /**
     * Valida la respuesta del cliente comparándola con la secuencia actual.
     * <p>
     * La respuesta debe ser una lista de números separados por comas
     * que coincida exactamente con la secuencia en orden y valores.
     * </p>
     * 
     * @param respuesta cadena con los números separados por comas
     * @return true si la respuesta es correcta, false en caso contrario
     */
    private boolean validarRespuesta(String respuesta) {
        try {
            // Limpiar la respuesta y separar por comas
            String[] partes = respuesta.trim().split(",");

            // Verificar que tenga la misma cantidad de números
            if (partes.length != secuencia.size()) {
                return false;
            }

            // Comparar cada número
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
     * Reinicia el juego a su estado inicial.
     * Limpia la secuencia y la restablece a [8, 3, 5].
     */
    private void reiniciarJuego() {
        secuencia.clear();
        secuencia.add(8);
        secuencia.add(3);
        secuencia.add(5);
    }
}
