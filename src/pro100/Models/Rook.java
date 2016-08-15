package pro100.Models;

public class Rook extends ChessPiece {

	public Rook(PieceColor c) {
		super(PieceType.ROOK, c);
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public boolean isValidMove(Location destination, Board board) {
		if(destination == this.getLocation()){
			return false;
		}
		if(destination.getColumn() != this.getLocation().getColumn() && destination.getRow() != this.getLocation().getRow()){
			//both are different
			return false;
		} else {
			Direction d = null;
			if(destination.getColumn() == getLocation().getColumn()){
				
				if(destination.getRow() < getLocation().getRow()){
					d = Direction.NORTH;
				} else {
					d = Direction.SOUTH;
				}
			} else {
				if(destination.getColumn() < getLocation().getColumn()){
					d = Direction.WEST;
				} else {
					d = Direction.EAST;
				}
			}

			int diff = Math.abs((getLocation().getColumn() - destination.getColumn()) + (getLocation().getRow() - destination.getRow()));
			boolean clearPath = true;
			for(int i = 1; i < diff; i++){
				switch(d){
				case EAST:
					//<=
					if(board.getPiece(new Location(getLocation().getColumn() + i, getLocation().getRow())) != null){
						clearPath = false;
					}
					break;
				case NORTH:
					if(board.getPiece(new Location(getLocation().getColumn(), getLocation().getRow() - i)) != null){
						clearPath = false;
					}
					break;
				case SOUTH:
					if(board.getPiece(new Location(getLocation().getColumn(), getLocation().getRow() + i)) != null){
						clearPath = false;
					}
					break;
				case WEST:
					if(board.getPiece(new Location(getLocation().getColumn() - i, getLocation().getRow())) != null){
						clearPath = false;
					}
					break;
				
				}
			}
			if(clearPath){
				if(board.getPiece(destination) == null){
					return true;
				} else {
					return !board.getPiece(destination).getPieceColor().equals(getPieceColor());
				}
			} else {
				return false;
			}
		}
	}

}
