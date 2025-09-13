import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to Tic-tac-toe!");
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter board size: ");
        int n = sc.nextInt();
        TicTacToe ticTacToe = new TicTacToe(n);
        ticTacToe.play();

    }
}