package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerPVE {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3333);
            System.out.println("Servidor iniciado. Aguardando jogador.");

            Socket p = server.accept();
            System.out.println("Player conectado!");

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            PrintWriter out = new PrintWriter(p.getOutputStream(), true);

            int pw = 0;
            int ew = 0;

            while (true) {
                Random r = new Random(3);
                int EnvCh = r.nextInt(3);
                String ec = "";
            
            switch (EnvCh){
                case 0:
            ec = "1";
            break;
            case 1:
            ec = "2";
            break;
            case 2:
            ec = "3";
            }
                String pc = in.readLine();
                System.out.println("jogador" + pc);


                String res = calc(pc, ec);
                if (res.equals("Voce venceu esta rodada!")) {
                    out.print(res);
                    pw++;
                } else if (res.equals("A Maquina venceu esta rodada!")) {
                    out.print(res);
                    ew++;
                } else if (res.equals("Empate")){
                    out.print("Empate! Tente novamente.");
                }
                else if (res.equals("caractere invalido")){
                    out.print("Digite um caractere válido");
                }
                if (res.equals("Saindo. Obrigado por jogar!!")){
                    out.print("Saindo do jogo. Obrigado por jogar");
                    break;

                }
                out.println(". Placar: Jogador: [" + pw + "] X Máquina: [" + ew + "]. ");

            }

            // Fechando as conexões
            out.close();
            in.close();
            p.close();
            server.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String calc(String pc, String ec) {
        // Lógica para determinar o vencedor
        if(pc.equals("1") || pc.equals("2") || pc.equals("3") || pc.equals("4")){
            if (pc.equals("4")) {
                return "Saindo. Obrigado por jogar!!";
            }
            else if(pc.equals(ec)){
                return "Empate";
            }
            else if ((pc.equals("1") && ec.equals("3")) || (pc.equals("2") && ec.equals("1")) || (pc.equals("3") && ec.equals("2")) ) {
                return "Voce venceu esta rodada!";
            }
            else{
                return "A Maquina venceu esta rodada!";
            }
        }
        else{
            return "caractere invalido";
        }
    }
}