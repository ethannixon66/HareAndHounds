import java.util.ArrayList;
import java.util.Arrays;

public class Piece {
	
	private int location;
	private String type;
	private Board board;
	private ArrayList<Integer> legalMoves = new ArrayList<Integer>();

	public Piece(int location, Board board, String type) {
		
		this.location = location;
		this.type = type;
		this.board = board;
		
		board.addPiece(this);
		movePiece(location);
	}

	public int getLocation() {
		return location;
	}
	public String getType() {
		return type;
	}
	public void movePiece(int destination) {
		
		// Changes starting square to be empty
		board.setSquareValue(location, String.valueOf(location));
		
		// Changes destination square to display piece 
		board.setSquareValue(destination, type); 
		
		// Updates the location
		location = destination; 
		
		if (board.didHareWin()) {
			System.out.println(board);
			System.out.println("The hare has won!");
			System.exit(0);
		}
		if (board.didHoundsWin()) {
			System.out.println(board);
			System.out.println("The hounds won!");
			System.exit(0);
		}
	}
	public ArrayList<Integer> getLegalMoves() { 
		legalMoves.clear();
		if (type.equals("h")) {	
			switch (location) {
				case 0:
					legalMoves.addAll(Arrays.asList(1,3,4,5));
					break;
				case 1: 
					legalMoves.addAll(Arrays.asList(0,2,5));
					break;
				case 2:
					legalMoves.addAll(Arrays.asList(1,5,6,7));
					break;
				case 3:
					legalMoves.addAll(Arrays.asList(0,4,8));
					break;
				case 4:
					legalMoves.addAll(Arrays.asList(0,3,5,8));
					break;
				case 5:
					legalMoves.addAll(Arrays.asList(0,1,2,4,6,8,9,10));
					break;
				case 6:
					legalMoves.addAll(Arrays.asList(2,5,7,10));
					break;
				case 7: 
					legalMoves.addAll(Arrays.asList(2,6,10));
					break;
				case 8:
					legalMoves.addAll(Arrays.asList(3,4,5,9));
					break;
				case 9:
					legalMoves.addAll(Arrays.asList(5,8,10));
					break;
				case 10:
					legalMoves.addAll(Arrays.asList(5,6,7,9));
					break;
			} 
		} else {
			switch (location) {
			case 0:
				legalMoves.addAll(Arrays.asList(1,4,5));
				break;
			case 1: 
				legalMoves.addAll(Arrays.asList(2,5));
				break;
			case 2:
				legalMoves.addAll(Arrays.asList(6,7));
				break;
			case 3:
				legalMoves.addAll(Arrays.asList(0,4,8));
				break;
			case 4:
				legalMoves.addAll(Arrays.asList(0,5,8));
				break;
			case 5:
				legalMoves.addAll(Arrays.asList(1,2,6,9,10));
				break;
			case 6:
				legalMoves.addAll(Arrays.asList(2,7,10));
				break;
			case 7: 
				legalMoves.addAll(Arrays.asList());
				break;
			case 8:
				legalMoves.addAll(Arrays.asList(4,5,9));
				break;
			case 9:
				legalMoves.addAll(Arrays.asList(5,10));
				break;
			case 10:
				legalMoves.addAll(Arrays.asList(6,7));
				break;
			} 
		}
		legalMoves.removeAll(board.getOccupiedSquares());
		return legalMoves;
	}
}