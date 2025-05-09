package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getMove() {
        String move = null;
        try {
            move = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return move;
    }
}
