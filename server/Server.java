package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3333);
            System.out.println("Aguardando conexão dos players.");
            Socket p1 = serverSocket.accept();

            BufferedReader in1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            PrintStream out1 = new PrintStream(p1.getOutputStream(), true);

            System.out.println("Jogador 1 entrou. Aguardando novo player.");
            out1.println("Bem-vindo, jogador 1. Digite uma opção e aguarde a jogada do outro jogador.[1] pedra. [2] papel. [3] tesoura");

            Socket p2 = serverSocket.accept();

            BufferedReader in2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            PrintStream out2 = new PrintStream(p2.getOutputStream(), true);

            System.out.println("Jogador 2 entrou.");
            out2.println("Bem-vindo, jogador 2. Digite uma opção e aguarde a jogada do outro jogador. [1] pedra. [2] papel. [3] tesoura");
            while (true) {
                String escp1 = in1.readLine();
                String escp2 = in2.readLine();

                System.out.println("Jogador 1: " + escp1);
                System.out.println("Jogador 2: " + escp2);

                String calc = calc(escp1, escp2);

                out1.println(calc);
                out2.println(calc);
                System.out.println(calc);


                p1.close();
                p2.close();
                serverSocket.close();
                break;
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String calc(String escp1, String escp2) {
        // Pedra[1], Papel[2], Tesoura[3]
        if((escp1.equals("1") || escp1.equals("2") || escp1.equals("3")) && (escp2.equals("1") || escp2.equals("2") || escp2.equals("3"))){
            if (escp1.equals("1") && escp2.equals("3") || escp1.equals("2") && escp2.equals("1") || escp1.equals("3") && escp2.equals("2")) {
                return "Vitória do Jogador 1";
            } else if (escp2.equals("1") && escp1.equals("3") || escp2.equals("2") && escp1.equals("1") || escp2.equals("3") && escp1.equals("2")) {
                return "Vitória do Jogador 2";
            } else if (escp1.equals(escp2)) {
                return "Empate";
            }
        }
        else{
        } if (!escp1.equals("1") && !escp1.equals("2") && !escp1.equals("3")) {
            if (!escp2.equals("1") && !escp2.equals("2") && !escp2.equals("3")) {
                return "Valor Digitado pelo player 1 e 2 inválidos";
            } else {
                return "Valor Digitado pelo player 1 inválido";
            }
        } else {
            return "Valor digitado pelo player 2 inválido";
        }
    }
    }
