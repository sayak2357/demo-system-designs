import controller.ChessGame;
import entity.Position;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome to Chess Game!");
        ChessGame game = new ChessGame();

        // Sample moves (just for demonstration)
        // White moves rook
        game.makeMove(
                new Position(7, 0),
                new Position(5, 0)
        );

        // Black moves rook
        game.makeMove(
                new Position(0, 0),
                new Position(2, 0)
        );

        // White moves queen (assume queen placed at 7,3)
        game.makeMove(
                new Position(7, 3),
                new Position(3, 7)
        );

        // Check game status
        System.out.println("Game Status: " + game.getStatus());
        System.out.println("Winner: " + game.getWinner());
    }
}