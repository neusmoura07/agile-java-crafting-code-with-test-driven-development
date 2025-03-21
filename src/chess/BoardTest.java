package chess;

import chess.pieces.Piece;
import junit.framework.TestCase;
import chess.util.*;


public class BoardTest extends TestCase {
    private Board board;

    protected void setUp() {
        board = new Board();
    }

    public void testCreate() {
        board.initialize();
        assertEquals(32, board.getPieceCount());
        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
                StringUtil.appendNewLine("RNBQKBNR") +
                        StringUtil.appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("pppppppp") +
                        StringUtil.appendNewLine("rnbqkbnr"),
                board.printBoard());
    }
    public void testCountPieces() {
        board.initialize();

        assertEquals(2, board.countPieces(Piece.Color.black, Piece.Type.KNIGHT));
        assertEquals(2, board.countPieces(Piece.Color.white, Piece.Type.BISHOP));
        assertEquals(1, board.countPieces(Piece.Color.black, Piece.Type.KING));

        board.addPawn(Piece.createPiece(Piece.Color.black,Piece.Type.KING, Piece.KING_REPRESENTATION));
        assertEquals(2, board.countPieces(Piece.Color.black, Piece.Type.KING));
    }

    public void testGetPieceAt() {
        Piece blackRook = Piece.createPiece(Piece.Color.black, Piece.Type.ROOK, Piece.ROOK_REPRESENTATION);
        blackRook.setPosition("a8");

        Piece whiteKing = Piece.createPiece(Piece.Color.white, Piece.Type.KING, Piece.KING_REPRESENTATION);
        whiteKing.setPosition("e1");

        board.addPawn(blackRook);
        board.addPawn(whiteKing);

        assertEquals(blackRook, board.getPieceAt("a8"));
        assertEquals(whiteKing, board.getPieceAt("e1"));

        assertNull(board.getPieceAt("d4"));
    }

}
