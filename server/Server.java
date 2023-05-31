package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // start do servidor
            ServerSocket serverSocket = new ServerSocket(3333);
            System.out.println("Aguardando conexão dos players.");

            //declaracao da conexao do player
            Socket player = serverSocket.accept();


            //declaracao do in e out do servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(player.getInputStream()));
            PrintStream out = new PrintStream(player.getOutputStream(), true);


            //Conexão estabelecida com jogador
            System.out.println("Jogador 1 entrou. Aguardando novo player.");
            out.println("Bem-vindo, jogador 1. Aguarde a conexão de um novo player.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
