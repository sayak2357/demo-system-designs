package entity;

class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position to) {
        int dx = Math.abs(position.row - to.row);
        int dy = Math.abs(position.col - to.col);

        if (dx != dy) return false;

        return board.isPathClear(position, to);
    }
}
/**
 * Say in interview
 *
 * “Bishop moves diagonally,
 */
?
