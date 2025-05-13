package chess.game;

import chess.pieces.Piece;

import java.util.*;


/**
 * Cria o tabuleiro e adiciona as peças dentro da lista
 */
public class Board implements ChessBoard, Iterable<Piece> {

    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
    }
    public List<Piece> pieces = new LinkedList<>();

    public String printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = board[row][col];
                sb.append(p == null
                        ? '.'
                        : p.getRepresentation());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    int countPieces(Piece.Color color, Class<?> expectedClass) {
        int  count = 0;
        for(Piece[] row : board){
            for(Piece p : row) {
                if (p != null && p.getColor() == color && p.getClass().equals(expectedClass)) {
                    count++;
                }
            }
        }
        return count;
    }
    //Obtém peças por posição
    public Piece getPieceAt(String position) {
        int file = position.charAt(0) - 'a';        // 'a'→0, 'b'→1, … 'h'→7
        int rank =     '8'  - position.charAt(1);  // '8'→0, '7'→1, … '1'→7
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
        return getAllPieces().size();
    }

    public void addPawn(Piece pawn) {
        put(pawn,pawn.getPosition());
    }

    public List<Piece> getAllPieces() {
        List<Piece> allPieces = new ArrayList<>();
        for(Piece[] row : board) {
            for (Piece p : row) {
                if(p != null) {
                    allPieces.add(p);
                }
            }
        }
        return allPieces;
    }
    @Override
    public Iterator<Piece> iterator() {
        return getAllPieces().iterator();
    }



}
