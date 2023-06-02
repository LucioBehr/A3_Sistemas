package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int cli1P = 0;
        int cli2P = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(3333);
            System.out.println("Aguardando conexão dos players.");
            Socket p1 = serverSocket.accept();

            BufferedReader in1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            PrintStream out1 = new PrintStream(p1.getOutputStream(), true);
            out1.println("Bem vindo, jogador 1. Digite uma opção e aguarde a jogada do outro jogador.[1] pedra. [2] papel. [3] tesoura [4] Sair");
            System.out.println("Jogador 1 entrou. Aguardando novo player.");
            Socket p2 = serverSocket.accept();
        
            BufferedReader in2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            PrintStream out2 = new PrintStream(p2.getOutputStream(), true);
            out2.println("Bem vindo, jogador 2. Digite uma opção e aguarde a jogada do outro jogador. [1] pedra. [2] papel. [3] tesoura [4] sair");
            System.out.println("Jogador 2 entrou.");
            
            while (true) {


                String escp1 = in1.readLine();
                String escp2 = in2.readLine();

                System.out.println("Jogador 1: " + escp1);
                System.out.println("Jogador 2: " + escp2);

                String calc = calc(escp1, escp2);
                if(calc.equals("Vitória do Jogador 1")){
                    cli1P++;
                    }
                    else if (calc.equals("Vitória do Jogador 2")){
                        cli2P++;
                    }
                    if (calc.equals("Saindo do jogo.")) {

                        //out1.print("Placar: Jogador 1: ["+ cli1P+ "] X Jogador 2: ["+ cli2P+ "]. ");
                        //out2.print("Placar: Jogador 1: ["+ cli1P+ "] X Jogador 2: ["+ cli2P+ "]. ");
                        out1.println(calc);
                        out2.println(calc);
                        break;
                    }

                out1.print(calc);
                out2.print(calc);
                out1.println(". Placar Atual: Jogador 1: ["+ cli1P+ "] X Jogador 2: ["+ cli2P+ "]. Novamente digite [1] para pedra, [2] para papel, [3] para tesoura e [4] para sair");
                out2.println(". Placar Atual: Jogador 1: ["+ cli1P+ "] X Jogador 2: ["+ cli2P+ "]. Novamente digite [1] para pedra, [2] para papel, [3] para tesoura e [4] para sair");
                


            
            }
            p1.close();
            p2.close();
            serverSocket.close();
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String calc(String escp1, String escp2) {
        // Pedra[1], Papel[2], Tesoura[3]
        if ((escp1.equals("1") || escp1.equals("2") || escp1.equals("3") || escp1.equals("4")) && (escp2.equals("1") || escp2.equals("2") || escp2.equals("3") || escp2.equals("4"))) {
            if (escp1.equals("1") && escp2.equals("3") || escp1.equals("2") && escp2.equals("1") || escp1.equals("3") && escp2.equals("2")) {
                return "Vitória do Jogador 1";
            } else if (escp2.equals("1") && escp1.equals("3") || escp2.equals("2") && escp1.equals("1") || escp2.equals("3") && escp1.equals("2")) {
                return "Vitória do Jogador 2";
            } else if (escp1.equals(escp2)) {
                return "Empate";
            } if (escp1.equals("4") || escp2.equals("4")) {
                return "Saindo do jogo.";
            }
            return "erro";
        } else {
            if (!escp1.equals("1") && !escp1.equals("2") && !escp1.equals("3")) {
                if (!escp2.equals("1") && !escp2.equals("2") && !escp2.equals("3")) {
                    return "Valores digitados pelo player 1 e 2 inválidos";
                } else {
                    return "Valor digitado pelo player 1 inválido";
                }
            } else {
                return "Valor digitado pelo player 2 inválido";
            }
        }
    }
}