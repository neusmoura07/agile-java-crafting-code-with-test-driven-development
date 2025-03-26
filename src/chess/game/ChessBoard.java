package chess.game;

import chess.pieces.Piece;

public interface ChessBoard {
    // Retorna uma representação em string do tabuleiro
    String printBoard();

    // Posiciona uma peça em uma posição específica (ex.: "a8")
    void put(Piece piece, String position);

    // Retorna a peça na posição especificada ou null se não houver peça
    Piece getPieceAt(String position);

}
