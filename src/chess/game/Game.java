package chess.game;

import chess.pieces.*;


import java.util.*;

public class Game {
    private Board board;

    public Game() {
        board = new Board();
    }

    // Calcula a força total das peças de uma determinada cor no tabuleiro
    public double getStrength(Piece.Color color) {
        double strength = 0;
        for(Piece piece : board.pieces) {
            if(piece.getColor() == color) {
                switch (piece.getClass().getSimpleName()) {
                    case "Pawn":
                        int pawnCount = countPawnOnFile(piece.getFile(), color);
                        if (pawnCount > 1) {
                            strength += 0.5;
                        } else {
                            strength += 1.0;
                        }
                        break;
                    default:
                        strength += piece.getStrength();
                        break;
                }
            }
        }
        return strength;
    }

    // Atribui a força (valor ajustado) a cada peça, levando em consideração a regra dos peões
    public void assignPieceStrength() {
        for (Piece piece : board.pieces) {
            double strength = 0;

            switch (piece.getClass().getSimpleName()) {
                case "Pawn":
                    // Se houver mais de um peão na mesma coluna, cada peão vale 0.5; caso contrário, vale 1.0
                    int pawnCount = countPawnOnFile(piece.getFile(), piece.getColor());
                    if (pawnCount > 1) {
                        strength = 0.5;
                    } else {
                        strength = piece.getStrength();
                    }
                    break;
                default:
                    strength = piece.getStrength();
                    break;
            }
            piece.setStrength(strength);
        }
    }

    // Retorna uma lista de peças de uma cor específica, ordenadas da de maior força para a de menor
    public List<Piece> getPiecesSortedByStrength(Piece.Color color) {
        List<Piece> list = new ArrayList<>();
        for (Piece piece : board.pieces) {
            if (piece.getColor() == color) {
                list.add(piece);
            }
        }
        // Ordena a lista em ordem decrescente de força
        Collections.sort(list, new Comparator<Piece>() {
            public int compare(Piece p1, Piece p2) {
                return Double.compare(p2.getStrength(), p1.getStrength());
            }
        });
        return list;
    }

    // Metodo auxiliar para contar o número de peões de uma cor específica em uma determinada coluna (file)
    private int countPawnOnFile(int file, Piece.Color color) {
        int count = 0;
        for(Piece piece: board.pieces) {
            // Verifica se a peça é um peão da cor especificada e está na coluna desejada
            if(piece.getColor() == color && piece.getClass() == Pawn.class & piece.getFile() == file) {
                count++;
            }
        }
        return count;
    }

    public Board getBoard() {
        return board;
    }


    public void initializeGame() {
        board.put(Rook.createRook(Piece.Color.BLACK,"a8"),"a8");
        board.put(Knight.createKnight(Piece.Color.BLACK,"b8"),"b8");
        board.put(Bishop.createBishop(Piece.Color.BLACK,"c8"),"c8");
        board.put(Queen.createQueen(Piece.Color.BLACK,"d8"),"d8");
        board.put(King.createKing(Piece.Color.BLACK,"e8"),"e8");
        board.put(Bishop.createBishop(Piece.Color.BLACK,"f8"),"f8");
        board.put(Knight.createKnight(Piece.Color.BLACK,"g8"),"g8");
        board.put(Rook.createRook(Piece.Color.BLACK,"h8"),"h8");

        board.put(Pawn.createPawn(Piece.Color.BLACK,"a7"),"a7");
        board.put(Pawn.createPawn(Piece.Color.BLACK,"b7"),"b7");
        board.put(Pawn.createPawn(Piece.Color.BLACK,"c7"),"c7");
        board.put(Pawn.createPawn(Piece.Color.BLACK,"d7"),"d7");
        board.put(Pawn.createPawn(Piece.Color.BLACK,"e7"),"e7");
        board.put(Pawn.createPawn(Piece.Color.BLACK,"f7"),"f7");
        board.put(Pawn.createPawn(Piece.Color.BLACK,"g7"),"g7");
        board.put(Pawn.createPawn(Piece.Color.BLACK,"h7"),"h7");

        board.put(Pawn.createPawn(Piece.Color.WHITE,"a2"), "a2");
        board.put(Pawn.createPawn(Piece.Color.WHITE,"b2"), "b2");
        board.put(Pawn.createPawn(Piece.Color.WHITE,"c2"), "c2");
        board.put(Pawn.createPawn(Piece.Color.WHITE,"d2"), "d2");
        board.put(Pawn.createPawn(Piece.Color.WHITE, "e2"), "e2");
        board.put(Pawn.createPawn(Piece.Color.WHITE,"f2"), "f2");
        board.put(Pawn.createPawn(Piece.Color.WHITE,"g2"), "g2");
        board.put(Pawn.createPawn(Piece.Color.WHITE,"h2"), "h2");

        board.put(Rook.createRook(Piece.Color.WHITE,"a1"), "a1");
        board.put(Knight.createKnight(Piece.Color.WHITE,"b1"), "b1");
        board.put(Bishop.createBishop(Piece.Color.WHITE,"c1"), "c1");
        board.put(Queen.createQueen(Piece.Color.WHITE,"d1"), "d1");
        board.put(King.createKing(Piece.Color.WHITE,"e1"), "e1");
        board.put(Bishop.createBishop(Piece.Color.WHITE,"f1"), "f1");
        board.put(Knight.createKnight(Piece.Color.WHITE,"g1"), "g1");
        board.put(Rook.createRook(Piece.Color.WHITE,"h1"), "h1");
    }


}
