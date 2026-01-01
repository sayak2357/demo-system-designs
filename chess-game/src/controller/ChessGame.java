package controller;

import entity.*;

public class ChessGame {
    private Board board = new Board();
    private Color currentTurn = Color.WHITE;
    private GameStatus status = GameStatus.ACTIVE;
    private Color winner = null;
    public GameStatus getStatus() {
        return status;
    }

    public Color getWinner() {
        return winner;
    }

    public boolean makeMove(Position from, Position to) {
        Piece piece = board.getPiece(from);

        if (piece == null || piece.getColor() != currentTurn)
            return false;

        if (!piece.canMove(board, to))
            return false;

        Piece captured = board.getPiece(to);
        board.movePiece(from, to);

        // illegal move if own king still in check
        if (board.isKingInCheck(currentTurn)) {
            board.movePiece(to, from);
            board.setPiece(to, captured);
            return false;
        }

        Color opponent = (currentTurn == Color.WHITE)
                ? Color.BLACK : Color.WHITE;

        if (board.isKingInCheck(opponent)) {
            if (isCheckmate(opponent)) {
                status = GameStatus.CHECKMATE;
                winner = currentTurn;
            } else {
                status = GameStatus.CHECK;
            }
        } else if (noLegalMoves(opponent)) {
            status = GameStatus.STALEMATE;
        } else {
            status = GameStatus.ACTIVE;
        }

        currentTurn = opponent;
        return true;
    }

    private boolean isCheckmate(Color color) {
        return board.isKingInCheck(color) && noLegalMoves(color);
    }

    private boolean noLegalMoves(Color color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board.getPiece(new Position(i, j));
                if (p != null && p.getColor() == color) {
                    for (int r = 0; r < 8; r++) {
                        for (int c = 0; c < 8; c++) {
                            Position to = new Position(r, c);
                            if (p.canMove(board, to)) {
                                Piece captured = board.getPiece(to);
                                board.movePiece(p.getPosition(), to);
                                boolean stillCheck =
                                        board.isKingInCheck(color);
                                board.movePiece(to, p.getPosition());
                                board.setPiece(to, captured);
                                if (!stillCheck) return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
