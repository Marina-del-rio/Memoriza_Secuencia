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

            System.out.println("INSTRUCCIONES DEL JUEGO: memoriza la secuencia de números, en cada ronda" +
                    "se sumará un número a la cifra anterior, además de limpiar la terminal" +
                    "¡¡SUERTE!!");

            // PASO 2: Bucle de comunicación controlada
            while (conectado) {
                System.out.print("> ");

            }

        } catch (ConnectException e) {
            System.err.println("Error: No se pudo conectar al servidor. ¿Está encendido?");
        } catch (IOException e) {
            System.err.println("Error de comunicación: " + e.getMessage());
        }
        System.out.println("--- Cliente desconectado ---");
    }
}
