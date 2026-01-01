package entity;

class King extends Piece {

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position to) {
        int dx = Math.abs(position.row - to.row);
        int dy = Math.abs(position.col - to.col);
        return dx <= 1 && dy <= 1;
    }
}
