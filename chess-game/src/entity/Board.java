package entity;

public class Board {
    private Piece[][] board = new Piece[8][8];

    public Board() {
        initialize();
    }

    private void initialize() {
        board[0][4] = new King(Color.BLACK, new Position(0, 4));
        board[7][4] = new King(Color.WHITE, new Position(7, 4));

        board[0][0] = new Rook(Color.BLACK, new Position(0, 0));
        board[7][0] = new Rook(Color.WHITE, new Position(7, 0));
    }

    public Piece getPiece(Position p) {
        return board[p.row][p.col];
    }

    public void setPiece(Position p, Piece piece) {
        board[p.row][p.col] = piece;
        if (piece != null) piece.setPosition(p);
    }

    public void movePiece(Position from, Position to) {
        Piece p = getPiece(from);
        setPiece(to, p);
        setPiece(from, null);
    }

    public boolean isPathClear(Position from, Position to) {
        // simplified for interview
        return true;
    }

    public Position findKing(Color color) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (board[i][j] instanceof King &&
                        board[i][j].color == color)
                    return new Position(i, j);
        return null;
    }

    public boolean isKingInCheck(Color kingColor) {
        Position kingPos = findKing(kingColor);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[i][j];
                if (p != null && p.color != kingColor) {
                    if (p.canMove(this, kingPos))
                        return true;
                }
            }
        }
        return false;
    }
}
