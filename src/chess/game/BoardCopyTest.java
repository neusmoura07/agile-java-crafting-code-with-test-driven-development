package chess.game;

import chess.pieces.*;
import junit.framework.TestCase;
import chess.util.*;

import java.util.List;


public class BoardCopyTest extends TestCase {
    private Game game;

    protected void setUp() {
        game = new Game();

    }

    public void testCreate() {
        game.initializeGame();
        Board board = game.getBoard();
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
        game.initializeGame();
        Board board = game.getBoard();
        assertEquals(2, board.countPieces(Piece.Color.BLACK, Knight.class));
        assertEquals(2, board.countPieces(Piece.Color.WHITE, Bishop.class));
        assertEquals(1, board.countPieces(Piece.Color.BLACK, King.class));

        board.addPawn(King.createKing(Piece.Color.BLACK, ""));
        assertEquals(2, board.countPieces(Piece.Color.BLACK, King.class));
    }

    public void testGetPieceAt() {
        Board board = game.getBoard();
        Piece blackRook = Rook.createRook(Piece.Color.BLACK, "");
        blackRook.setPosition("a8");

        Piece whiteKing = King.createKing(Piece.Color.WHITE, "");
        whiteKing.setPosition("e1");

        board.addPawn(blackRook);
        board.addPawn(whiteKing);

        assertEquals(blackRook, board.getPieceAt("a8"));
        assertEquals(whiteKing, board.getPieceAt("e1"));

        assertNull(board.getPieceAt("d4"));
    }

    public void testPlacePiece() {
        game.initializeGame();
        Board board = game.getBoard();
        Piece king = King.createKing(Piece.Color.WHITE, "b6");
        board.put(king,"b6");
        assertEquals(king, board.getPieceAt("b6"));

        Piece pawn = Pawn.createPawn(Piece.Color.BLACK, "b2");
        board.put(pawn, "b2");
        assertEquals(pawn, board.getPieceAt("b2"));

        Piece bishop = Bishop.createBishop(Piece.Color.BLACK, "a1");
        board.put(bishop, "a1");
        assertEquals(bishop, board.getPieceAt("a1"));

    }

    public void testCalculateStrength() {
        Board board = game.getBoard();

        Piece blackKing = King.createKing(Piece.Color.BLACK,"b8");
        Piece blackRook = Rook.createRook(Piece.Color.BLACK, "c8");
        Piece blackPawn1 = Pawn.createPawn(Piece.Color.BLACK, "a7");
        Piece blackPawn2 = Pawn.createPawn(Piece.Color.BLACK, "c7");
        Piece blackPawn3 = Pawn.createPawn(Piece.Color.BLACK, "b6");
        Piece blackBishop = Bishop.createBishop(Piece.Color.BLACK, "d7");
        Piece blackQueen = Queen.createQueen(Piece.Color.BLACK, "e6");

        Piece whiteKnight = Knight.createKnight(Piece.Color.WHITE,"f4");
        Piece whiteQueen = Queen.createQueen(Piece.Color.WHITE, "g4");
        Piece whitePawn1 = Pawn.createPawn(Piece.Color.WHITE,"f3");
        Piece whitePawn2 = Pawn.createPawn(Piece.Color.WHITE,"h3");
        Piece whitePawn3 = Pawn.createPawn(Piece.Color.WHITE,"f2");
        Piece whitePawn4 = Pawn.createPawn(Piece.Color.WHITE,"g3");
        Piece whiteRook = Rook.createRook(Piece.Color.WHITE,"e1");
        Piece whiteKing = King.createKing(Piece.Color.WHITE,"f1");


        board.addPawn(blackKing);
        board.addPawn(blackRook);
        board.addPawn(blackPawn1);
        board.addPawn(blackPawn2);
        board.addPawn(blackPawn3);
        board.addPawn(blackBishop);
        board.addPawn(blackQueen);

        board.addPawn(whiteKnight);
        board.addPawn(whiteQueen);
        board.addPawn(whitePawn1);
        board.addPawn(whitePawn2);
        board.addPawn(whitePawn3);
        board.addPawn(whitePawn4);
        board.addPawn(whiteRook);
        board.addPawn(whiteKing);

        double whiteStrength = game.getStrength(Piece.Color.WHITE);
        double blackStrength = game.getStrength(Piece.Color.BLACK);

        assertEquals(20.0, blackStrength, 0.01);
        assertEquals(19.5,whiteStrength,0.01);

    }

    public void testPieceStrengthAndSorting() {
        Board board = game.getBoard();

        // Criar peças de exemplo
        Piece whitePawn1 = Pawn.createPawn(Piece.Color.WHITE, "a2");
        Piece whitePawn2 = Pawn.createPawn(Piece.Color.WHITE, "a3");
        Piece whiteRook = Rook.createRook(Piece.Color.WHITE, "h1");
        Piece blackQueen = Queen.createQueen(Piece.Color.BLACK, "d8");
        Piece blackKnight = Knight.createKnight(Piece.Color.BLACK, "b8");

        // Adicionar as peças ao tabuleiro
        board.addPawn(whitePawn1);
        board.addPawn(whitePawn2);
        board.addPawn(whiteRook);
        board.addPawn(blackQueen);
        board.addPawn(blackKnight);

        // Atribuir força a cada peça
        game.assignPieceStrength();

        // Para as peças brancas:
        // whitePawn1 e whitePawn2 estão na mesma coluna ("a"), então cada peão deve valer 0.5
        // whiteRook tem valor 5.0
        // Total esperado para brancas = 0.5 + 0.5 + 5.0 = 6.0
        double whiteStrength = 0.0;
        List<Piece> whitePieces = game.getPiecesSortedByStrength(Piece.Color.WHITE);
        for (Piece p : whitePieces) {
            whiteStrength += p.getStrength();
        }
        assertEquals(6.0, whiteStrength, 0.001);

        // Para as peças pretas:
        // blackQueen tem valor 9.0
        // blackKnight tem valor 2.5
        // Total esperado para pretas = 9.0 + 2.5 = 11.5
        double blackStrength = 0.0;
        List<Piece> blackPieces = game.getPiecesSortedByStrength(Piece.Color.BLACK);
        for (Piece p : blackPieces) {
            blackStrength += p.getStrength();
        }
        assertEquals(11.5, blackStrength, 0.001);

        // Verifica se a lista de peças pretas está ordenada de forma decrescente pela força
        assertTrue(blackPieces.get(0).getStrength() >= blackPieces.get(1).getStrength());
    }

}
