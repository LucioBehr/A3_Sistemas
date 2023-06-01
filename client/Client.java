package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintStream out = null;
        BufferedReader consin = null;
        try {
            socket = new Socket("localhost", 3333);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
            String message = in.readLine();
            System.out.println(message);
            consin = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
        }
        //in.close();
        //out.close();
       // socket.close();
       // consin.close();
       // System.out.println("Obrigado por jogar!!");
       // System.exit(0);
    }catch (IOException e) {
            e.printStackTrace();
        }
    }
}