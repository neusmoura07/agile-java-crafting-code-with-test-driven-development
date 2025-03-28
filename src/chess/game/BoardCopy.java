package chess.game;

import chess.pieces.Piece;

import java.util.LinkedList;
import java.util.List;

/**
 * Cria o tabuleiro e adiciona as peças dentro da lista
 */
public class BoardCopy implements ChessBoard{

    private Piece[][] board;

    public BoardCopy() {
        board = new Piece[8][8];
    }
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
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++) {
                Piece p = board[row][col];
                if (p != null && p.getColor() == color && p.getClass().equals(expectedClass)) {
                    count++;
                }
            }
        }
        return count;
    }
    //Obtém peças por posição
    public Piece getPieceAt(String position) {
        int file = position.charAt(0) - 'a'; // Converte 'a'-'h' para 0-7
        int rank = 8 - Character.getNumericValue(position.charAt(1)); // Converte '1'-'8' para 0-7
        return board[rank][file];
    }
    // Coloca uma peça em uma posição específica e atualiza a posição da peça
    public void put(Piece piece, String position) {
        int file = position.charAt(0) - 'a';
        int rank = 8 - Character.getNumericValue(position.charAt(1));
        board[rank][file] = piece;
        piece.setPosition(position);
    }

    public int getPieceCount() {
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public void addPawn(Piece pawn) {
        put(pawn,pawn.getPosition());
    }

}
