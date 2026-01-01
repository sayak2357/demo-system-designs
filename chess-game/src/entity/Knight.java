package entity;

class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position to) {
        int dx = Math.abs(position.row - to.row);
        int dy = Math.abs(position.col - to.col);

        // Knight moves in L shape
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
/*
* Say in interview

“Knight is special because it ignores path obstruction, so no path check is needed.”
*
* */