package serveur;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private String symbol; // "X" ou "O"
    private PrintWriter out;
    private BufferedReader in;
    private GameLogic gameLogic;
    private ClientHandler opponent;

    public ClientHandler(Socket socket, String symbol, PrintWriter out, BufferedReader in, GameLogic gameLogic) {
        this.socket = socket;
        this.symbol = symbol;
        this.out = out;
        this.in = in;
        this.gameLogic = gameLogic;
    }

    public void setOpponent(ClientHandler opponent) {
        this.opponent = opponent;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (gameLogic) {
                    if (!gameLogic.getCurrentPlayer().equals(symbol)) {
                        out.println("Ce n'est pas votre tour !");
                        gameLogic.wait();
                    }

                    out.println("Plateau actuel :\n" + gameLogic.getBoard());
                    out.println("Votre tour ! Entrez votre mouvement (ligne,colonne) :");
                    String move = in.readLine();
                    if (gameLogic.playMove(symbol, move)) {
                        if (gameLogic.checkWinner(symbol)) {
                            out.println("Vous avez gagné !");
                            opponent.out.println("Vous avez perdu !");
                            break;
                        } else if (gameLogic.isBoardFull()) {
                            out.println("Match nul !");
                            opponent.out.println("Match nul !");
                            break;
                        }
                        opponent.out.println("Plateau actuel :\n" + gameLogic.getBoard());
                        opponent.out.println("C'est votre tour !");
                        gameLogic.notifyAll();
                    } else {
                        out.println("Mouvement invalide, essayez à nouveau.");
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
