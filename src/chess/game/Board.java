package chess.game;

import chess.pieces.*;
import java.util.*;

/**
 * Cria o tabuleiro e adiciona as peças dentro da lista
 */
public class Board implements ChessBoard{


    public List<Piece> pieces = new LinkedList<>();

    public String printBoard() {
        StringBuilder board = new StringBuilder();

        board.append("RNBQKBNR\n");
        board.append("PPPPPPPP\n");
        board.append("........\n");
        board.append("........\n");
        board.append("........\n");
        board.append("........\n");
        board.append("pppppppp\n");
        board.append("rnbqkbnr\n");

        return board.toString();
    }

    int countPieces(Piece.Color color, Class<?> expectedClass) {
        int  count = 0;
        for(Piece piece: pieces){
            if(piece.getColor() == color && piece.getClass() == expectedClass){
                count++;
            }
        }
        return count;
    }
    //Obtém peças por posição
    public Piece getPieceAt(String position) {
        int file = position.charAt(0) - 'a'; // Converte 'a'-'h' para 0-7
        int rank = 8 - Character.getNumericValue(position.charAt(1)); // Converte '1'-'8' para 0-7
        for (Piece piece: pieces) {
            if(piece.getFile() == file && piece.getRank() == rank){
                return piece;
            }
        }
        return null;
    }
    //Move peças
    public void put(Piece piece, String position) {
        pieces.removeIf(p -> p.getPosition().equals(position));
        piece.setPosition(position);
        pieces.add(piece);
    }

    public int getPieceCount() {
        return pieces.size();
    }

    public void addPawn(Piece pawn) {
        pieces.add(pawn);
    }

}
