//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import snakeLadder.Board;
import snakeLadder.SnakeLadder;
import snakeLadder.User;

import java.util.ArrayList;
import java.util.List;
/*
Snake Ladder
*/

public class Main {
    public static void main(String []args) {
        Board board = new Board();
        List<User> users = new ArrayList<>();
        User u1 = new User("User1", 0);
        User u2 = new User("User2", 0);
        users.add(u1);
        users.add(u2);
        SnakeLadder snakeL = new SnakeLadder(board, users);
        snakeL.addUser(new User("User3", 0));
        snakeL.board.addLadder(51,87);
        snakeL.board.addLadder(11,47);
        snakeL.board.addSnake(10,4);
        snakeL.board.addSnake(32,13);
        snakeL.board.addSnake(67,9);
        snakeL.board.addSnake(68,8);
        snakeL.board.addSnake(97,3);
        snakeL.simulate();
    }
}