package entity;

class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position to) {
        int dx = Math.abs(position.row - to.row);
        int dy = Math.abs(position.col - to.col);

        // Rook-like OR Bishop-like movement
        if (dx == dy || position.row == to.row || position.col == to.col) {
            return board.isPathClear(position, to);
        }
        return false;
    }
}
// Say in interview: “Queen combines rook and bishop movement, so I reuse the same path-clear logic.”
