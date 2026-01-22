import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServidorJuego {
     private static volatile boolean servidorActivo = true;

     public static void main(String[] args) {
         int puerto = 9876;

         if(args.length > 0 ){
             try{
                 puerto= Integer.parseInt(args[0]);
             } catch(NumberFormatException e){
                 System.out.println("Puerto invalido");
             }

         }

         Runtime.getRuntime().addShutdownHook(new Thread(() -> {
             System.out.println("\n--- Apagando servidor... ---");
             servidorActivo = false;
         }));

         ServidorJuego servidor = new ServidorJuego();
         servidor.iniciar(puerto);
     }

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
