package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        final int port = 1234;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor aguardando conexões...");

            int playerCount = 0;

            while (true) {
                Socket clientSocket = serverSocket.accept();
                playerCount++;

                System.out.println("Jogador " + playerCount + " conectado.");

                GameThread gameThread = new GameThread(clientSocket, playerCount);
                gameThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class GameThread extends Thread {
    private Socket clientSocket;
    private int playerNumber;
    private BufferedReader reader;
    private PrintWriter writer;

    public GameThread(Socket clientSocket, int playerNumber) {
        this.clientSocket = clientSocket;
        this.playerNumber = playerNumber;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writer.println("Bem-vindo ao jogo Pedra, Papel e Tesoura!");

            // Aguarda a escolha do jogador
            while (true) {
                String choice = reader.readLine();

                if (choice != null) {
                    int playerSelection = Integer.parseInt(choice);

                    int result = calculateResult(playerSelection);

                    writer.println("Resultado: " + getResultMessage(result));

                    break;
                }
            }

            // Encerra a conexão
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calculateResult(int playerSelection) {
        // Implemente a lógica para calcular o resultado do jogo
        // Você pode seguir as regras mencionadas anteriormente

        // Exemplo: sempre retorna 1 (vence) para o jogador 1 e 2 (perde) para o jogador 2
        if (playerNumber == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    private String getResultMessage(int result) {
        // Implemente a lógica para gerar a mensagem de resultado do jogo

        // Exemplo: mensagem fixa
        if (result == 1) {
            return "Você venceu!";
        } else {
            return "Você perdeu!";
        }
    }
}