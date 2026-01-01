package entity;

//
public abstract class Piece {
    protected Color color;
    protected Position position;
    protected boolean killed = false;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() { return color; }
    public Position getPosition() { return position; }
    public void setPosition(Position p) { this.position = p; }

    public abstract boolean canMove(Board board, Position to);
}
