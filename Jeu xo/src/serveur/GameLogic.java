package serveur;

public class GameLogic {

    private String[][] board;  // Plateau de jeu 20x20
    private String currentPlayer;  // Joueur actuel

    // Constructeur pour initialiser le plateau de taille 20x20
    public GameLogic() {
        board = new String[20][20];  // Créer un plateau 20x20
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = " ";  // Initialiser chaque case à vide
            }
        }
        currentPlayer = "X";  // Le joueur "X" commence
    }

    // Méthode pour obtenir l'état actuel du plateau sous forme de chaîne
    public synchronized String getBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append("|");
            for (int j = 0; j < 20; j++) {
                sb.append(board[i][j]);
                sb.append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Méthode pour jouer un coup
    public synchronized boolean playMove(String player, String move) {
        try {
            // Diviser le mouvement en ligne et colonne (séparé par une virgule)
            String[] parts = move.split(",");

            if (parts.length != 2) {
                return false; // Si le format n'est pas correct, retourner false
            }

            // Extraire les indices ligne et colonne et les convertir en entiers
            int row = Integer.parseInt(parts[0].trim()) - 1; // La ligne est décrite par un nombre de 1 à 20
            int col = Integer.parseInt(parts[1].trim()) - 1; // La colonne est décrite par un nombre de 1 à 20

            // Vérifier si les indices sont valides (entre 0 et 19) et si la case est vide
            if (row < 0 || row >= 20 || col < 0 || col >= 20 || !board[row][col].equals(" ")) {
                return false; // Si la case est déjà occupée ou hors des limites, retourner false
            }

            // Placer le mouvement du joueur sur le plateau
            board[row][col] = player;

            // Passer au joueur suivant
            currentPlayer = player.equals("X") ? "O" : "X";
            return true;
        } catch (NumberFormatException e) {
            return false; // Si l'entrée ne peut pas être convertie en un nombre, retourner false
        }
    }

    // Méthode pour vérifier si un joueur a gagné
    public synchronized boolean checkWinner(String player) {
        // Vérifier les lignes (horizontalement)
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j <= 15; j++) {  // On vérifie jusqu'à la colonne 15 pour éviter de sortir du tableau
                if (board[i][j].equals(player) && board[i][j+1].equals(player) &&
                        board[i][j+2].equals(player) && board[i][j+3].equals(player) &&
                        board[i][j+4].equals(player)) {
                    return true;  // Si 5 symboles consécutifs sont trouvés horizontalement
                }
            }
        }

        // Vérifier les colonnes (verticalement)
        for (int i = 0; i <= 15; i++) {  // On vérifie jusqu'à la ligne 15 pour éviter de sortir du tableau
            for (int j = 0; j < 20; j++) {
                if (board[i][j].equals(player) && board[i+1][j].equals(player) &&
                        board[i+2][j].equals(player) && board[i+3][j].equals(player) &&
                        board[i+4][j].equals(player)) {
                    return true;  // Si 5 symboles consécutifs sont trouvés verticalement
                }
            }
        }

        // Vérifier la diagonale descendante (haut gauche à bas droite)
        for (int i = 0; i <= 15; i++) {
            for (int j = 0; j <= 15; j++) {  // On vérifie jusqu'à la ligne et la colonne 15
                if (board[i][j].equals(player) && board[i+1][j+1].equals(player) &&
                        board[i+2][j+2].equals(player) && board[i+3][j+3].equals(player) &&
                        board[i+4][j+4].equals(player)) {
                    return true;  // Si 5 symboles consécutifs sont trouvés sur la diagonale descendante
                }
            }
        }

        // Vérifier la diagonale montante (bas gauche à haut droite)
        for (int i = 4; i < 20; i++) {  // On commence à la ligne 4 pour éviter de sortir du tableau
            for (int j = 0; j <= 15; j++) {
                if (board[i][j].equals(player) && board[i-1][j+1].equals(player) &&
                        board[i-2][j+2].equals(player) && board[i-3][j+3].equals(player) &&
                        board[i-4][j+4].equals(player)) {
                    return true;  // Si 5 symboles consécutifs sont trouvés sur la diagonale montante
                }
            }
        }

        return false;  // Si aucune condition de victoire n'est remplie
    }

    // Méthode pour vérifier si le plateau est plein
    public synchronized boolean isBoardFull() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (board[i][j].equals(" ")) {
                    return false;  // Si une case est vide, retourner false
                }
            }
        }
        return true;  // Si aucune case n'est vide, retourner true
    }

    // Méthode pour obtenir le joueur actuel
    public String getCurrentPlayer() {
        return currentPlayer;
    }
}
