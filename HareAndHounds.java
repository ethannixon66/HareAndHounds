import java.util.Arrays;
import java.util.Scanner;

public class HareAndHounds {
	public static void main(String[] args) {
		
		Board board = new Board();
		Piece hare = new Piece(7, board, "h");
		Piece hound1 = new Piece(0, board, "H");
		Piece hound2 = new Piece(3, board, "H");
		Piece hound3 = new Piece(8, board, "H");

		greeting();
		System.out.println(board);
		
		while (true) {
			hareTurn(hare, board);
			houndTurn(hound1, hound2, hound3, board);
		}
	}
	
	public static void greeting() {
		System.out.println(" __  __   ______   ______   ______       ______   __   __   _____      \r\n"
				+ "/\\ \\_\\ \\ /\\  __ \\ /\\  == \\ /\\  ___\\     /\\  __ \\ /\\ \"-.\\ \\ /\\  __-.    \r\n"
				+ "\\ \\  __ \\\\ \\  __ \\\\ \\  __< \\ \\  __\\     \\ \\  __ \\\\ \\ \\-.  \\\\ \\ \\/\\ \\   \r\n"
				+ " \\ \\_\\ \\_\\\\ \\_\\ \\_\\\\ \\_\\ \\_\\\\ \\_____\\    \\ \\_\\ \\_\\\\ \\_\\\\\"\\_\\\\ \\____-   \r\n"
				+ "  \\/_/\\/_/ \\/_/\\/_/ \\/_/ /_/ \\/_____/     \\/_/\\/_/ \\/_/ \\/_/ \\/____/   \r\n"
				+ "                                                                       \r\n"
				+ "          __  __   ______   __  __   __   __   _____    ______         \r\n"
				+ "         /\\ \\_\\ \\ /\\  __ \\ /\\ \\/\\ \\ /\\ \"-.\\ \\ /\\  __-. /\\  ___\\        \r\n"
				+ "         \\ \\  __ \\\\ \\ \\/\\ \\\\ \\ \\_\\ \\\\ \\ \\-.  \\\\ \\ \\/\\ \\\\ \\___  \\       \r\n"
				+ "          \\ \\_\\ \\_\\\\ \\_____\\\\ \\_____\\\\ \\_\\\\\"\\_\\\\ \\____- \\/\\_____\\      \r\n"
				+ "           \\/_/\\/_/ \\/_____/ \\/_____/ \\/_/ \\/_/ \\/____/  \\/_____/      \r\n"
				+ "                                                                       ");
		System.out.println(" Rules: \n  1. The hare (h) can move in any direction\n  2. The hounds (H) can move in any non-backwards direction\n  "
				+ "3. The hare wins when it reaches the left-most square\n  4. The hounds win if the hare has no legal moves left\n");
		System.out.println(" How To Play: \n  1. The hare moves first. Input the number of the square\n     you wish to move to (square numbers shown below)\n"
				+ "\t\t    0───1───2\r\n"
				+ "\t\t  / | \\ | / | \\   \r\n"
				+ "\t\t 3──4───5───6──7\r\n"
				+ "\t\t  \\ | / | \\ | / \r\n"
				+ "\t\t    8———9───10\r\n"
				+ "  2. The hounds move next. Select which hound you want to\n     move and then input the square you wish to move it to\n  "
				+ "3. Players take turn making moves until either the hare or hounds win\n");
		System.out.println("(Press enter to continue...)");
		Scanner pressEnter = new Scanner(System.in);
		pressEnter.nextLine();
	}
 
	public static int validateMoveAttempt(Piece piece) {
		Scanner input = new Scanner(System.in);
		int moveAttempt;
		boolean isLegalmove = true;
		do {

			if (!isLegalmove) {										// This block is skipped on the first pass and is only run
				System.out.print("Please enter a valid move\n> ");	// if the user gave an integer AND the integer was not a valid move
			}															
			while (!input.hasNextInt()) {
				System.out.print("Please enter a number 0-10\n> ");
				input.next();
			}
			moveAttempt = input.nextInt();
			isLegalmove = false;
		} while (!piece.getLegalMoves().contains(moveAttempt));
		return moveAttempt;
	}
	public static int validateHoundSelect() {
		// Works identically to validateMoveAttempt
		Scanner input = new Scanner(System.in);
		int houndNumber;
		boolean isValidHound = true;
		do {
			if (!isValidHound) {
				System.out.print("Please enter a number 1-3\n> ");
			}
			while (!input.hasNextInt()) {
				System.out.print("Please enter a number 1-3\n> ");
				input.next();
			}
			houndNumber = input.nextInt();
			isValidHound = false;
		} while (!Arrays.asList(1,2,3).contains(houndNumber));
		return houndNumber;
	}
	
	public static void hareTurn(Piece hare, Board board) {
		System.out.print("What square will you move the hare to?\n> " );
		int hareMove = validateMoveAttempt(hare);
		
		hare.movePiece(hareMove);
		System.out.println(board);
	}
	public static void houndTurn(Piece hound1, Piece hound2, Piece hound3, Board board) {
		System.out.print("Which hound will you move? (1-3)\n1. Hound on square " + hound1.getLocation()
							+ "\n2. Hound on square " + hound2.getLocation() + "\n3. Hound on square " 
							+ hound3.getLocation() + "\n> ");
		
		int houndNumber = validateHoundSelect();
		
		// Switch statement checks if the hound chosen has legal moves. If it does the hound is moved and the function returns
		// If the hound has no legal moves the switch statement is exited and the houndMove function is called recursively
		int houndMove;
		switch (houndNumber) {
			case 1:
				if (hound1.getLegalMoves().isEmpty()) {
					System.out.println("That hound cannot move\n");
					break;
				}
				System.out.print("What square will you move the hound to?\n> " );
				houndMove = validateMoveAttempt(hound1);
				
				hound1.movePiece(houndMove);
				System.out.println(board);
				
				return;
			case 2:
				if (hound2.getLegalMoves().isEmpty()) {
					System.out.println("That hound cannot move\n");
					break;
				}
				System.out.print("What square will you move the hound to?\n> " );
				houndMove = validateMoveAttempt(hound2);
				
				hound2.movePiece(houndMove);
				System.out.println(board);

				return;
			case 3:
				if (hound3.getLegalMoves().isEmpty()) {
					System.out.println("That hound cannot move\n");
					break;
				}
				System.out.print("What square will you move the hound to?\n> " );
				houndMove = validateMoveAttempt(hound3);
			
				hound3.movePiece(houndMove);
				System.out.println(board);

				return;
		}
		houndTurn(hound1, hound2, hound3, board);
	}
}