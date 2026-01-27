import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Random;

public class ClienteHandler implements Runnable {
    private final Socket socket;
    private Random generador;
    Juego juego;
    List<Integer> secuencia;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
        this.generador = new Random();
        this.juego = new Juego();
        this.secuencia = juego.getSecuencia();
    }

    // Método que reutilizarás en cada ronda para añadir dificultad
    private void agregarNuevoNumero() {
        // Genera un número del 1 al 100
        int nuevoNumero = generador.nextInt(100) + 1;
        // Lo guarda en la lista
        secuencia.add(nuevoNumero);
    }

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

    // Método para validar la respuesta del cliente
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

    // Método para reiniciar el juego
    private void reiniciarJuego() {
        secuencia.clear();
        secuencia.add(8);
        secuencia.add(3);
        secuencia.add(5);
    }

}
