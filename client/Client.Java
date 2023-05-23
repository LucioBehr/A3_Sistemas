import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Client{
    public static void main(String[] args) {
        final String host = "localhost";
        final int port = 1234;

        try {
            Socket socket = new Socket(host, port);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Conectado ao servidor. Aguardando outro jogador...");

            String serverMessage = reader.readLine();
            System.out.println(serverMessage);

            System.out.print("Escolha uma opção (1-Papel, 2-Pedra, 3-Tesoura): ");
            String choice = consoleReader.readLine();
            writer.println(choice);

            serverMessage = reader.readLine();
            System.out.println(serverMessage);

            // Encerra a conexão
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
