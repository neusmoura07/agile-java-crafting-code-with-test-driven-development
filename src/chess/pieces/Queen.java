package chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private Queen(Color color, String position) {
        super('q', color, position, 9.0);
    }

    public static Queen createQueen(Color color, String position) {
        return new Queen(color, position);
    }

    public List<String> getPossibleMoves() {
        List<String> moves = new ArrayList<>();
        int file = getFile();  // índice da coluna (0 a 7)
        int rank = getRank();  // índice da linha (0 a 7)

        // Os deslocamentos para as 8 direções:
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int d = 0; d < dx.length; d++) {
            int x = file;
            int y = rank;
            // Avança na direção enquanto a posição for válida
            while (true) {
                x += dx[d];
                y += dy[d];
                if (!isValidPosition(x, y)) {
                    break;  // Fora do tabuleiro
                }
                moves.add(convertToPosition(x, y));
            }
        }
        return moves;
    }

    private boolean isValidPosition(int file, int rank) {
        return file >= 0 && file < 8 & rank >= 0 && rank < 8;
    }

    private String convertToPosition(int file, int rank) {
        char fileChar = (char) ('a' + file);
        int rankNumber = 8 - rank;
        return "" + fileChar + rankNumber;
    }

    @Override
    public double getStrength() {
        return 9.0;
    }
}
