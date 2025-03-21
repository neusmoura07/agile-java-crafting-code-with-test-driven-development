package chess;

import chess.pieces.*;
import java.util.*;

/**
 * Cria o tabuleiro e adiciona as peças dentro da lista
 */
public class Board {
    private List<Piece> pieces = new LinkedList<Piece>();

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

    int countPieces(Piece.Color color, Piece.Type type) {
        int  count = 0;
        for(Piece piece: pieces){
            if(piece.getColor() == color && piece.getType() == type){
                count++;
            }
        }
        return count;
    }

    public Piece getPieceAt(String position) {
        int file = position.charAt(0) - 'a'; // Converte 'a' - 'h- para 0-7
        int rank = 8 - Character.getNumericValue(position.charAt(1)); // Converte '1' - '8' para 0-7

        for (Piece piece: pieces) {
            if(piece.getFile() == file && piece.getRank() == rank){
                return piece;
            }
        }
        return null;
    }

    int getPieceCount() {
        return pieces.size();
    }

    public void addPawn(Piece pawn) {
        pieces.add(pawn);
    }

    Piece get(int index) {
        return pieces.get(index);
    }

    void initialize() {


        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.ROOK, Piece.ROOK_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.KNIGHT, Piece.KNIGHT_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.BISHOP,Piece.BISHOP_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.QUEEN, Piece.QUEEN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.KING, Piece.KING_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.BISHOP,Piece.BISHOP_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.KNIGHT, Piece.KNIGHT_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.ROOK, Piece.ROOK_REPRESENTATION));

        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));

        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION));

        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.ROOK, Piece.ROOK_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.KNIGHT, Piece.KNIGHT_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.BISHOP,Piece.BISHOP_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.QUEEN, Piece.QUEEN_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.KING, Piece.KING_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.BISHOP,Piece.BISHOP_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.KNIGHT, Piece.KNIGHT_REPRESENTATION));
        addPawn(Piece.createPiece(Piece.Color.white, Piece.Type.ROOK, Piece.ROOK_REPRESENTATION));


    }
}
