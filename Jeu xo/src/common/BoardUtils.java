package common;

public class BoardUtils {

    public static void printBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(String.join(" | ", board[i]));
            if (i < board.length - 1) {
                System.out.println("---------");
            }
        }
    }

    public static boolean isValidMove(int row, int col, String[][] board) {
        return (row >= 0 && row < 3) && (col >= 0 && col < 3) && board[row][col].equals(" ");
    }
}
