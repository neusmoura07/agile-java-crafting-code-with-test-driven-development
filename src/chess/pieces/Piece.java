package chess.pieces;

/**
 * Cria uma peça de de xadrez onde tem o branco como padrão
 */
public class Piece {

    public enum Color {white, black}

    /*enum Representation {P, p}*/

    public enum Type {PAWN, KNIGHT, ROOK, BISHOP, QUEEN, KING}

    public static final char PAWN_REPRESENTATION = 'p';
    public static final char KNIGHT_REPRESENTATION = 'n';
    public static final char ROOK_REPRESENTATION = 'r';
    public static final char BISHOP_REPRESENTATION = 'b';
    public static final char QUEEN_REPRESENTATION = 'q';
    public static final char KING_REPRESENTATION = 'k';

    private char representation;
    private  Color color;
    private Type type;
    private String position;

    private static int count;

    private Piece(char representation, Color color, Type type) {
        this.representation = representation;
        this.color = color;
        this.type = type;
    }

    public static Piece createPiece(Color color, Type type, char representation) {
        incrementCount();
        if (color == Color.black) {
            return new Piece(Character.toUpperCase(representation), Color.black, type);
        }
        else {

            return new Piece(representation, Color.white, type);
        }

    }

    private static void incrementCount() {
        count++;
    }

    public boolean isWhite() {
        return color.equals(Color.white);
    }

    public boolean isBlack() {
        return color.equals(Color.black);
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

    public Type getType() {
        return type;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getFile() {
        // Converte a letra da posição ('a' em "a8") em um índice (0 para 'a', 1 para 'b', etc.)
        return position.charAt(0) - 'a';
    }

    public int getRank() {
        // converte o dígito da posição ('8' em "a8") em um índice de linha.
        return 8 - (position.charAt(1) - '0');
    }

    public char getRepresentation() {
        return representation;
    }

}
