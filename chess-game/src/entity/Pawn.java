package entity;

class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Board board, Position to) {
        int direction = (color == Color.WHITE) ? -1 : 1;
        int startRow = (color == Color.WHITE) ? 6 : 1;

        int dx = to.row - position.row;
        int dy = Math.abs(to.col - position.col);

        Piece dest = board.getPiece(to);

        // Move forward by 1
        if (dy == 0 && dx == direction && dest == null) {
            return true;
        }

        // First move: forward by 2
        if (dy == 0 && dx == 2 * direction &&
                position.row == startRow && dest == null) {
            return true;
        }

        // Diagonal capture
        if (dy == 1 && dx == direction && dest != null &&
                dest.color != this.color) {
            return true;
        }

        return false;
    }
}


/*
* 💬 Say in interview

“Pawn is directional, so movement depends on color.
I handled normal move, first double move, and diagonal capture.
En passant can be added later.”
*
*
* */