public class Board {
    private final int size;
    private final Cell[][] board;
    public Board(int size){
        this.size = size;
        this.board = new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                board[i][j] = Cell.EMPTY;
            }
        }
    }

    public boolean makeMove(int row, int col, Cell player){
        if(row<0 || row>=size || col<0 || col>=size || this.board[row][col]!=Cell.EMPTY){
            return false;
        }
        this.board[row][col] = player;
        return true;
    }

    public boolean isFull(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==Cell.EMPTY)
                    return false;
            }
        }
        return true;
    }

    public boolean checkWin(int row, int col, Cell player){
        boolean isWin = true;

        // check rows in given column for same value
        for(int r=0;r<this.size;r++){
            if(this.board[r][col]!=player){
                isWin = false;
                break;
            }
        }
        if(isWin) return true;


        // check cols for given row for same value
        isWin = true;
        for(int c=0;c<this.size;c++){
            if(this.board[row][c]!=player){
                isWin = false;
                break;
            }
        }
        if(isWin) return true;


        // check diagonals
        if(row==col) {
            isWin = true;
            for(int i=0;i<size;i++){
                if(this.board[i][i]!=player){
                    isWin = false;
                    break;
                }
            }
            if (isWin) return true;
        }

        // check anti-diagonals
        if(row+col == (size-1)) {
            isWin = true;
            for(int i=0;i<size;i++){
                if(this.board[i][size-1-i]!=player){
                    isWin = false;
                    break;
                }
            }
            if (isWin) return true;
        }
        return false;
    }

    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                switch(board[i][j]){
                    case X -> System.out.printf("X ");
                    case O -> System.out.printf("O ");
                    case EMPTY -> System.out.printf("_ ");
                }
            }
            System.out.println();
        }
    }

}
