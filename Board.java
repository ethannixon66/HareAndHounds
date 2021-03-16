import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Board {
	private ArrayList<Piece> piecesOnBoard = new ArrayList<Piece>();
	private ArrayList<Integer> occupiedSquares = new ArrayList<Integer>();
	private String[] squares =

		   { "0", "1", "2",
		"3", "4", "5", "6", "7",
			 "8", "9", "10"	};
	
	public static int validateInput(List<Integer> validInput, String prompt, String invalidPrompt) {
		Scanner input = new Scanner(System.in);
		int userInput;
		boolean isValid = true;
		do {
			if (!isValid) {
				System.out.print(invalidPrompt);
			}
			while (!input.hasNextInt()) {
				System.out.print(prompt);
				input.next();
			}
			userInput = input.nextInt();
			isValid = false;
		} while (!validInput.contains(userInput));
		return userInput;
	}
	
	public String toString() {
		return "\n    " + squares[0] + "───" + squares[1] + "───" + squares[2]
				+ "\n  / | \\ | / | \\   \n "
				+ squares[3] + "──" + squares[4] + "───" + squares[5] + "───" + squares[6] + "──" + squares[7] + "\n"
				+ "  \\ | / | \\ | / \r\n"
				+ "    " + squares[8] + "───" + squares[9] + "───" + squares[10] + "\n";
	}
	public void addPiece(Piece piece) {
		piecesOnBoard.add(piece);
	}
	
	public void setSquareValue(int squareToChange, String valueOfChange) {
		squares[squareToChange] = valueOfChange;
	}
	public String getSquareValue(int squareNumber) {
		return squares[squareNumber];
	}
	public ArrayList<Integer> getOccupiedSquares() {
		// If this isn't cleared at the beginning the occupiedSquares list will contain all squares that have ever been occupied
		occupiedSquares.clear(); 
		for (int i=0; i < 11; i++) {
			
			if (squares[i] == "H" || squares[i] == "h") {
				occupiedSquares.add(i);
			}
		}
		return occupiedSquares;
	}
	
	public void hareTurn(Piece hare) {
		System.out.print("What square will you move the hare to?\n> " );
		int hareMove = validateInput(hare.getLegalMoves(),"Please enter a number 0-10\n> ","Please enter a legal move\n> ");
		
		hare.movePiece(hareMove);
		System.out.println(this);
	}
	public void houndTurn(Piece hound1, Piece hound2, Piece hound3) {
		System.out.print("Which hound will you move? (1-3)\n1. Hound on square " + hound1.getLocation()
							+ "\n2. Hound on square " + hound2.getLocation() + "\n3. Hound on square " 
							+ hound3.getLocation() + "\n> ");
		
		int houndNumber = validateInput(Arrays.asList(1,2,3),"Please enter a number 1-3\n> ","Please enter a number 1-3\n> ");
		
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
				houndMove = validateInput(hound1.getLegalMoves(),"Please enter a number 0-10\n> ","Please enter a legal move\n> ");
				
				hound1.movePiece(houndMove);
				System.out.println(this);
				
				return;
			case 2:
				if (hound2.getLegalMoves().isEmpty()) {
					System.out.println("That hound cannot move\n");
					break;
				}
				System.out.print("What square will you move the hound to?\n> " );
				houndMove = validateInput(hound2.getLegalMoves(),"Please enter a number 0-10\n> ","Please enter a legal move\n> ");
				
				hound2.movePiece(houndMove);
				System.out.println(this);

				return;
			case 3:
				if (hound3.getLegalMoves().isEmpty()) {
					System.out.println("That hound cannot move\n");
					break;
				}
				System.out.print("What square will you move the hound to?\n> " );
				houndMove = validateInput(hound3.getLegalMoves(),"Please enter a number 0-10\n> ","Please enter a legal move\n> ");
			
				hound3.movePiece(houndMove);
				System.out.println(this);

				return;
		}
		houndTurn(hound1, hound2, hound3);
	}
	
	
	public boolean didHareWin() {
		if (squares[3] == "h") {
			return true;
		} 
		return false;
		
	}
	public boolean didHoundsWin() {
		for (Piece piece : piecesOnBoard) {
			if (piece.getType() == "h" && piece.getLegalMoves().isEmpty()) {
				return true;
			}
		}
		return false;
	}
}