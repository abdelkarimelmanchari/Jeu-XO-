package serveur;

import java.io.*;
import java.net.*;

public class ServeurXO {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur en attente de deux joueurs...");

            // Accepter deux connexions
            Socket joueur1 = serverSocket.accept();
            System.out.println("Joueur 1 connecté !");
            PrintWriter out1 = new PrintWriter(joueur1.getOutputStream(), true);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(joueur1.getInputStream()));
            out1.println("Vous êtes le joueur X.");

            Socket joueur2 = serverSocket.accept();
            System.out.println("Joueur 2 connecté !");
            PrintWriter out2 = new PrintWriter(joueur2.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(joueur2.getInputStream()));
            out2.println("Vous êtes le joueur O.");

            // Démarrer un thread pour gérer chaque joueur
            GameLogic gameLogic = new GameLogic();
            ClientHandler handler1 = new ClientHandler(joueur1, "X", out1, in1, gameLogic);
            ClientHandler handler2 = new ClientHandler(joueur2, "O", out2, in2, gameLogic);

            handler1.setOpponent(handler2);
            handler2.setOpponent(handler1);

            new Thread(handler1).start();
            new Thread(handler2).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

