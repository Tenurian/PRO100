package pro100.Models;

public class Knight extends ChessPiece {

	public Knight(PieceColor c) {
		super(PieceType.KNIGHT, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(Location destination, Board board) {
		if(destination == this.getLocation()){
			return false;
		}
		/*   -2   0  +2
		 *    . x . x . -2
		 *    x . . . x -1
		 *    . . N . .  0
		 *    x . . . x +1
		 *    . x . x . +2
		 *     -1  +1 
		 * */
		int offsetY = destination.getColumn() - this.getLocation().getColumn();
		int offsetX = destination.getRow() - this.getLocation().getRow();
		if( (offsetY == -2 && (offsetX == -1 || offsetX == 1))
			|| (offsetY == -1 && (offsetX == -2 || offsetX == 2))
			|| (offsetY ==  1 && (offsetX == -2 || offsetX == 2))
			|| (offsetY ==  2 && (offsetX == -1 || offsetX == 1))){
			//correct locations... check if it's empty or has enemy piece
			return (board.getPiece(destination) == null) || (board.getPiece(destination).getPieceColor() != this.getPieceColor());
		}
		return false;
	}

}
