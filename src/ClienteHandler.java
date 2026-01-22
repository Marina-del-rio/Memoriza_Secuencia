import java.io.*;
import java.net.*;


public class ClienteHandler implements Runnable {
    private final Socket socket;
    public ClienteHandler(Socket socket) {this.socket = socket;}

    @Override
    public void run() {
        // Usamos Try-with-resources para asegurar el cierre del socket de
        try (socket; // Java 9+ permite referenciar la variable final aquí
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("*****BIENVENID@ A MEMORIZA LA SECUENCIA*****)");


            // Leemos líneas completas (más fácil que leer bytes fijos)
            String linea;
            while ((linea = in.readLine()) != null) {
                if (linea.equalsIgnoreCase("SALIR")) break;

                try {



                } catch (NumberFormatException e) {
                    out.println("ERROR: Los argumentos deben ser números enteros.");
                }
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado abruptamente.");
        } finally {
            System.out.println("Cliente finalizado: " + socket.getInetAddress());
        }
    }
}
