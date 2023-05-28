package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String host = "localhost";
        final int port = 1234;

        try {
            Socket socket = new Socket(host, port);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String serverMessage = reader.readLine();
            System.out.println(serverMessage);

            serverMessage = reader.readLine();
            System.out.println(serverMessage);

            System.out.print("Enter your choice (1-Rock, 2-Paper, 3-Scissors): ");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String choice = consoleReader.readLine();
            writer.println(choice);

            serverMessage = reader.readLine();
            System.out.println(serverMessage);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}