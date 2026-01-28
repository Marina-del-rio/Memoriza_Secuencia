import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Servidor TCP para el juego "Memoriza la Secuencia".
 * <p>
 * Este servidor escucha conexiones en un puerto específico (por defecto 9876)
 * y crea un hilo separado para manejar cada cliente conectado.
 * </p>
 * <p>
 * El servidor puede detenerse de forma segura usando Ctrl+C.
 * </p>
 * 
 * @author Estudiante
 * @version 1.0
 * @see ClienteHandler
 */
public class ServidorJuego {

    /** Flag volátil para controlar el ciclo de vida del servidor */
    private static volatile boolean servidorActivo = true;

    /**
     * Punto de entrada principal del servidor.
     * <p>
     * Acepta un argumento opcional para especificar el puerto:
     * {@code java ServidorJuego [puerto]}
     * </p>
     * 
     * @param args argumentos de línea de comandos (opcional: puerto)
     */
    public static void main(String[] args) {
        int puerto = 9876;

        if (args.length > 0) {
            try {
                puerto = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Puerto invalido");
            }
        }

        // Hook de apagado para terminar el servidor de forma segura
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n--- Apagando servidor... ---");
            servidorActivo = false;
        }));

        ServidorJuego servidor = new ServidorJuego();
        servidor.iniciar(puerto);
    }

    /**
     * Inicia el servidor y comienza a aceptar conexiones de clientes.
     * <p>
     * El servidor se ejecuta en un bucle infinito hasta que se recibe
     * una señal de terminación (Ctrl+C).
     * </p>
     * 
     * @param puerto número de puerto en el que escuchar conexiones
     */
    public void iniciar(int puerto) {
        try (ServerSocket socketEscucha = new ServerSocket(puerto)) {

            socketEscucha.setSoTimeout(1000);

            System.out.println("========================================");
            System.out.println("   SERVIDOR MEMORIZA LA SECUENCIA");
            System.out.println("========================================");
            System.out.println("Puerto: " + puerto);
            System.out.println("Pulsa Ctrl+C para detener");
            System.out.println("----------------------------------------");
            System.out.println("Esperando conexiones...\n");

            while (servidorActivo) {
                try {
                    Socket cliente = socketEscucha.accept();
                    System.out.println("NUEVO CLIENTE: " + cliente.getInetAddress());

                    new Thread(new ClienteHandler(cliente)).start();
                } catch (SocketTimeoutException e) {
                    System.out.print(".");
                }
            }

        } catch (BindException e) {
            System.err.println("ERROR: El puerto " + puerto + " ya está en uso.");
            System.err.println("Cierra el otro servidor o usa otro puerto.");
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }

        System.out.println("Servidor detenido.");
    }
}
