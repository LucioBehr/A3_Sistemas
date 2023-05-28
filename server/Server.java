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
            System.out.println("Server waiting for connections...");

            int playerCount = 0;

            while (true) {
                Socket clientSocket = serverSocket.accept();
                playerCount++;

                System.out.println("Player " + playerCount + " connected.");

                GameThread gameThread = new GameThread(clientSocket, playerCount);
                gameThread.start();

                if (playerCount == 2) {
                    playerCount = 0;
                }
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
    private static Object lock = new Object();
    private static boolean playersReady = false;
    private static int player1Choice;
    private static int player2Choice;

    public GameThread(Socket clientSocket, int playerNumber) {
        this.clientSocket = clientSocket;
        this.playerNumber = playerNumber;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            if (playerNumber == 1) {
                writer.println("Waiting for another player to connect...");
            }

            synchronized (lock) {
                if (playerNumber == 2) {
                    playersReady = true;
                    lock.notifyAll();
                } else {
                    while (!playersReady) {
                        lock.wait();
                    }
                }
            }

            writer.println("Choose an option: [1] Rock [2] Paper [3] Scissors");
            int choice = Integer.parseInt(reader.readLine());

            if (playerNumber == 1) {
                player1Choice = choice;
            } else {
                player2Choice = choice;
            }

            synchronized (lock) {
                if (playerNumber == 1) {
                    while (player2Choice == 0) {
                        lock.wait();
                    }
                } else {
                    while (player1Choice == 0) {
                        lock.wait();
                    }
                }

                calculateResult();

                player1Choice = 0;
                player2Choice = 0;
                playersReady = false;
                lock.notifyAll();
            }

            clientSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void calculateResult() {
        int result;

        if (player1Choice == player2Choice) {
            result = 0; // Draw
        } else if ((player1Choice == 1 && player2Choice == 3) ||
                (player1Choice == 2 && player2Choice == 1) ||
                (player1Choice == 3 && player2Choice == 2)) {
            result = 1; // Player 1 wins
        } else {
            result = 2; // Player 2 wins
        }

        if (playerNumber == 1) {
            writer.println(getResultMessage(result));
        }
    }

    private String getResultMessage(int result) {
        if (result == 0) {
            return "It's a draw!";
        } else if (result == 1) {
            return "You win!";
        } else {
            return "You lose!";
        }
    }
}