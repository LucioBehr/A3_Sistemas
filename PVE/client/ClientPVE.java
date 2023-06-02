package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientPVE {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3333);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            while (true) {

                System.out.println("Digite [1] para pedra, [2] para papel, e [3] para tesoura.");
                String pc = sc.nextLine();

                out.println(pc);
                String serverResp = in.readLine();
                System.out.print(serverResp);
            if(serverResp.equals("Saindo do jogo. Obrigado por jogar")){
            break;
            }
            }
            System.exit(0);
            in.close();
            out.close();
            socket.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}