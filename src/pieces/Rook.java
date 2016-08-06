package pieces;

import pro100.Board;
import pro100.Location;

public class Rook extends ChessPiece {

	public Rook(PieceColor c, Board b) {
		super(PieceType.ROOK, c, b);
	}

	@Override
	public boolean isValidMove(Location destination) {
		if(destination == this.getLocation()){
			return false;
		}
		if(destination.getColumn() != this.getLocation().getColumn() && destination.getRow() != this.getLocation().getRow()){
			//both are different
			return false;
		} else {
			Direction d = null;
			if(board.getPiece(destination) != null){
				//something at target destination
				if(board.getPiece(destination).getPieceColor() == this.getPieceColor()){
					//same color; auto-fail
					return false;
				} else {
					//search between current and target location
					if(destination.getColumn() == this.getLocation().getColumn()){
						if(destination.getRow() < this.getLocation().getRow()){
							d = Direction.NORTH;
						} else {
							d = Direction.SOUTH;
						}
					} else {
						if(destination.getColumn() < this.getLocation().getColumn()){
							d = Direction.WEST;
						} else {
							d = Direction.EAST;
						}
					}
					int i;
					switch(d){
					case NORTH:
						for(i = 1; i < Math.abs(destination.getColumn() - this.getLocation().getColumn()); i++){
							if(board.getBoard()[this.getLocation().getColumn() - i][this.getLocation().getRow()] != null){
								return false;
							}
						}
						break;
					case SOUTH:
						for(i = 1; i < Math.abs(destination.getColumn() - this.getLocation().getColumn()); i++){
							if(board.getBoard()[this.getLocation().getColumn() + i][this.getLocation().getRow()] != null){
								return false;
							}
						}
						break;
					case EAST:
						for(i = 1; i < Math.abs(destination.getRow() - this.getLocation().getRow()); i++){
							if(board.getBoard()[this.getLocation().getColumn()][this.getLocation().getRow() + i] != null){
								return false;
							}
						}
						break;
					case WEST:
						for(i = 1; i < Math.abs(destination.getRow() - this.getLocation().getRow()); i++){
							if(board.getBoard()[this.getLocation().getColumn()][this.getLocation().getRow() - i] != null){
								return false;
							}
						}
						break;
					default:
						return false;

					}
				}
			} else {
				//nothing at target location; search between current and target location
				if(destination.getColumn() == this.getLocation().getColumn()){
					if(destination.getRow() < this.getLocation().getRow()){
						d = Direction.NORTH;
					} else {
						d = Direction.SOUTH;
					}
				} else {
					if(destination.getColumn() < this.getLocation().getColumn()){
						d = Direction.WEST;
					} else {
						d = Direction.EAST;
					}
				}
				int i;
				switch(d){
				case WEST:
					for(i = 1; i <= Math.abs(destination.getColumn() - this.getLocation().getColumn()); i++){
						if(board.getBoard()[this.getLocation().getRow()][this.getLocation().getColumn() - i] != null){
							return false;
						}
					}
					break;
				case EAST:
					for(i = 1; i <= Math.abs(destination.getColumn() - this.getLocation().getColumn()); i++){
						if(board.getBoard()[this.getLocation().getRow()][this.getLocation().getColumn() + i] != null){
							return false;
						}
					}
					break;
				case SOUTH:
					for(i = 1; i <= Math.abs(destination.getRow() - this.getLocation().getRow()); i++){
						if(board.getBoard()[this.getLocation().getRow() + i][this.getLocation().getColumn()] != null){
							return false;
						}
					}
					break;
				case NORTH:
					for(i = 1; i <= Math.abs(destination.getRow() - this.getLocation().getRow()); i++){
						if(board.getBoard()[this.getLocation().getRow() - i][this.getLocation().getColumn()] != null){
							return false;
						}
					}
					break;
				default:
					return false;

				}
			}
		}
		return true;
	}

}
