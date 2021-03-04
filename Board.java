import java.util.ArrayList;

public class Board {
	private ArrayList<Piece> piecesOnBoard = new ArrayList<Piece>();
	private ArrayList<Integer> occupiedSquares = new ArrayList<Integer>();
	private String[] squares =

		   { "0", "1", "2",
		"3", "4", "5", "6", "7",
			 "8", "9", "10"	};
	
	public String toString() {
		return "    " + squares[0] + "───" + squares[1] + "───" + squares[2]
				+ "\n  / | \\ | / | \\   \n"
				+ squares[3] + "───" + squares[4] + "───" + squares[5] + "───" + squares[6] + "───" + squares[7] + "\n"
				+ "  \\ | / | \\ | / \r\n"
				+ "    " + squares[8] + "───" + squares[9] + "───" + squares[10];

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