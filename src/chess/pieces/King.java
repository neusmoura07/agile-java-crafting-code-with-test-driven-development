package chess.pieces;

import java.util.*;

public class King extends Piece {

    private King(Piece.Color color, String position) {
        super(KING_REPRESENTATION, color, position, 0.0);
    }

    public static King createKing(Piece.Color color, String position) {
        return new King(color, position);

    }

    public List<String> getPossibleMoves() {
        List<String> moves = new ArrayList<>();

        //Pega a posição atual do King
        int file = getFile();
        int rank = getRank();

        int[] fileOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] rankOffsets = {1, 1, 1, 0, 0, -1, -1, -1};

        //Percorre todas as direções
        for(int i = 0; i < fileOffsets.length; i++) {
            int newFile = file + fileOffsets[i];
            int newRank = rank + rankOffsets[i];

            //Verifica se é possível ir para aquele direção
            if (isValidPosition(newFile, newRank)) {
                moves.add(convertToPosition(newFile, newRank));
            }
        }
        return moves;
    }

    private boolean isValidPosition(int file, int rank) {
        return file >= 0 && file < 8 & rank >= 0 && rank < 8;
    }

    private String convertToPosition(int file, int rank) {
        char fileChar = (char) ('a' + file);
        int rankChar = 8 - rank;
        return "" + fileChar + rankChar;
    }

}
