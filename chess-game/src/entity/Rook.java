package entity;

class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position to) {
        if (position.row != to.row && position.col != to.col)
            return false;
        return board.isPathClear(position, to);
    }
}
