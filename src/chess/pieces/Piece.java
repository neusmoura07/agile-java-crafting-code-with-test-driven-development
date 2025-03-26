package chess.pieces;

import java.util.ArrayList;
import java.util.List;

/**
 * Cria uma peça de de xadrez onde tem o branco como padrão
 */
public class Piece {

    public enum Color {WHITE, BLACK}

    public static final char PAWN_REPRESENTATION = 'p';
    public static final char KNIGHT_REPRESENTATION = 'n';
    public static final char ROOK_REPRESENTATION = 'r';
    public static final char BISHOP_REPRESENTATION = 'b';
    public static final char QUEEN_REPRESENTATION = 'q';
    public static final char KING_REPRESENTATION = 'k';


    private char representation;
    private  Color color;
    private String position;

    private double strength;

    private static int count;

    protected Piece(char representation, Color color, String position, double strength) {
        incrementCount();
        this.representation = (color == Color.BLACK) ? Character.toUpperCase(representation) : representation;
        this.color = color;
        this.position = position;
        this.strength = strength;
    }

    public static Piece createPiece(Color color, char representation, String position, double strength) {
        return new Piece(representation,color, position, strength);
    }

    private static void incrementCount() {
        count++;
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    static void resetCount() {
        count = 0;
    }

    static int getCount() {
        return count;
    }

    public Color getColor() {
        return color;
    }

    public String getPosition() {
        return position;
    }

    public int getFile() {
        // Converte a letra da posição ('a' em "a8") em um índice (0 para 'a', 1 para 'b', etc.)
        return position.charAt(0) - 'a';
    }

    public int getRank() {
        // converte o dígito da posição ('8' em "a8") em um índice de linha.
        return 8 - (position.charAt(1) - '0');
    }

    public double getValue() {
        return strength;
    }

    public char getRepresentation() {
        return representation;
    }


    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getStrength() {
        return strength;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
