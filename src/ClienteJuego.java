import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteJuego {

    public static void main(String[] args) {

        String host = "localhost";
        int puerto = 9876;

        //Variable de control para el bucle While
        boolean conectado = true;

        System.out.println("-----INICIANDO CLIENTE-----");

        try (Socket socket = new Socket(host, puerto);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner teclado = new Scanner(System.in)) {

             String mensajeBienvenida = in.readLine();
             System.out.println("SERVIDOR DICE: " + mensajeBienvenida);
             System.out.println(" ");

            // PASO 2: Bucle de comunicación controlada
            while (conectado) {
                System.out.print("> ");

                // a. Leer comando del usuario
                String comando = teclado.nextLine();

                // b. Enviar al servidor
                out.println(comando);

                // c. Recibir respuesta del servidor (Bloqueante)
                // Esperamos aquí hasta que el servidor calcule y responda
                String respuesta = in.readLine();


                // Validación de seguridad: si el servidor se cae, la respuesta será null
                if (respuesta == null) {
                    System.out.println("El servidor cerró la conexión.");
                    conectado = false;
                } else {
                    System.out.println("SERVIDOR: " + respuesta);
                }

                // d. Comprobar si debemos salir
                // Si enviamos "SALIR", el servidor nos contestó "Adios!" y ahora cerramos nosotros.
                if (comando.equalsIgnoreCase("SALIR")) {
                    conectado = false;
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
