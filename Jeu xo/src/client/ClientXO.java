package client;

import java.io.*;
import java.net.*;

public class ClientXO {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println(serverMessage);

                if (serverMessage.contains("Votre tour")) {
                    String move = consoleInput.readLine();
                    out.println(move);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
