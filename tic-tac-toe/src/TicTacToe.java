import java.util.Scanner;

// Main game class
public class TicTacToe {
    private final Board board;
    private Cell currentPlayer;

    public TicTacToe(int size){
        this.board = new Board(size);
        this.currentPlayer = Cell.X;
    }

    void play(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            board.printBoard();
            System.out.println("current player: " + currentPlayer + "\nenter row and column:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if(!board.makeMove(row,col,currentPlayer)){
                System.out.println("Invalid move, try again!");
                continue;
            }

            if(board.checkWin(row,col,currentPlayer)){
                board.printBoard();
                System.out.println("Player "+currentPlayer+" has won!");
                break;
            }

            if(board.isFull()){
                board.printBoard();
                System.out.println("Mathc is a draw!");
                break;
            }

            currentPlayer = currentPlayer.equals(Cell.X)?Cell.O:Cell.X;
        }
        scanner.close();
    }
}
