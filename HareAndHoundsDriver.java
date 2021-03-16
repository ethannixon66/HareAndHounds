import java.util.List;
import java.util.Scanner;

public class HareAndHoundsDriver {
	public static void main(String[] args) {
		
		Board board = new Board();
		Piece hare = new Piece(7, board, "h");
		Piece hound1 = new Piece(0, board, "H");
		Piece hound2 = new Piece(3, board, "H");
		Piece hound3 = new Piece(8, board, "H");

		Greeting greeting = new Greeting();
		greeting.displayGreeting();
		
		System.out.println(board);
		while (true) {
			board.hareTurn(hare);
			board.houndTurn(hound1, hound2, hound3);
		}
	}
}