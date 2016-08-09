package pieces;

import pro100.Board;
import pro100.Location;

public class Peon extends ChessPiece {
	public Peon(PieceColor c) {
		super(PieceType.PEON, c);
	}

	@Override
	public boolean isValidMove(Location destination, Board board) {
		if(destination == this.getLocation()){
			return false;
		}
		if(destination.getColumn() == this.getLocation().getColumn()){
			//going straight
			if(this.getPieceColor() == PieceColor.WHITE){
				//going up; destination is less than or equal to location -1 or -2
				if(destination.getRow() >= this.getLocation().getRow() - ((isFirstMove())?2:1) && destination.getRow() < this.getLocation().getRow()){
					return board.getPiece(destination) == null;
				}
			} else {
				//going down;
				if(destination.getRow() <= this.getLocation().getRow() + ((isFirstMove())?2:1) && destination.getRow() > this.getLocation().getRow()){
					return board.getPiece(destination) == null;
				}
			}
		} else {
			//attacking
			if(this.getPieceColor() == PieceColor.WHITE){
				//going up
				if( destination.getRow() == this.getLocation().getRow() -1 && ( 
						destination.getColumn() == this.getLocation().getColumn() - 1 || 
						destination.getColumn() == this.getLocation().getColumn() + 1)){
					if(board.getPiece(destination) == null){
						return false;
					} else {
						return(board.getPiece(destination).getPieceColor() != this.getPieceColor());
					}
				} else {
					return false;
				}
			} else {
				//going down
				if( destination.getRow() == this.getLocation().getRow() +1 && ( 
						destination.getColumn() == this.getLocation().getColumn() - 1 || 
						destination.getColumn() == this.getLocation().getColumn() + 1)){
					if(board.getPiece(destination) == null){
						return false;
					} else {
						return(board.getPiece(destination).getPieceColor() != this.getPieceColor());
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	private boolean isFirstMove() {
		//GOTTA LOVE THEM TERNARY EXPRESSIONS, AMIRITE?
		return (this.getPieceColor()==PieceColor.WHITE)?this.getLocation().getRow() == 6:this.getLocation().getRow() == 1;
	}

}
