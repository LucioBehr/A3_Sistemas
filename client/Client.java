package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            //start do client
            Socket socket = new Socket("localhost", 3333);

            //declaracao de in e out do client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream(), true);

            //declaracao do envio de mensagem pelo client
            String message = in.readLine();
            System.out.println(message);

            //leitura do que o usuario digitar.
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
            }
            //in.close();
            //out.close();
            //socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
