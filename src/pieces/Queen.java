package pieces;

import pro100.Board;
import pro100.Location;

public class Queen extends ChessPiece {

	public Queen(PieceColor c) {
		super(PieceType.QUEEN, c);
	}

	@Override
	public boolean isValidMove(Location destination, Board board) {
		if(destination == getLocation()){
			return false;
		}
		return rookValidMove(destination, board) || bishopValidMove(destination, board);
	}
	
	@SuppressWarnings("incomplete-switch")
	private boolean rookValidMove(Location destination, Board board){
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
	
	private boolean bishopValidMove(Location destination, Board board){
		if(Math.abs(destination.getColumn() - getLocation().getColumn()) != Math.abs(destination.getRow() - getLocation().getRow())){
			return false;
		} else {
			Direction d = null;
			if(board.getPiece(destination) != null){
				if(board.getPiece(destination).getPieceColor() == getPieceColor()){
					return false;
				} else {
					//loop up to destination
					if(destination.getRow() < getLocation().getRow()){
						//going north
						if(destination.getColumn() < getLocation().getColumn()){
							//going west
							d = Direction.NORTHWEST;
						} else {
							//going east
							d = Direction.NORTHEAST;
						}
					} else {
						//going south
						if(destination.getColumn() < getLocation().getColumn()){
							//going west
							d = Direction.SOUTHWEST;
						} else {
							//going east
							d = Direction.SOUTHEAST;
						}
					}
					
					
					for(int i = 1; i < Math.abs(destination.getColumn() - getLocation().getColumn()); i++){
						switch(d){
						case NORTHEAST:
							//-, +
							if(board.getBoard()[getLocation().getRow() - i][getLocation().getColumn() + i] != null){
								return false;
							}
							break;
						case NORTHWEST:
							//-, -
							if(board.getBoard()[getLocation().getRow() - i][getLocation().getColumn() - i] != null){
								return false;
							}
							break;
						case SOUTHEAST:
							//+, +
							if(board.getBoard()[getLocation().getRow() + i][getLocation().getColumn() + i] != null){
								return false;
							}
							break;
						case SOUTHWEST:
							//+, -
							if(board.getBoard()[getLocation().getRow() + i][getLocation().getColumn() - i] != null){
								return false;
							}
							break;
						default:
							i = Integer.MAX_VALUE;
							return false;
						}
					}
				}
			} else {
				//loop to destination
				if(destination.getRow() < getLocation().getRow()){
					//going north
					if(destination.getColumn() < getLocation().getColumn()){
						//going west
						d = Direction.NORTHWEST;
					} else {
						//going east
						d = Direction.NORTHEAST;
					}
				} else {
					//going south
					if(destination.getColumn() < getLocation().getColumn()){
						//going west
						d = Direction.SOUTHWEST;
					} else {
						//going east
						d = Direction.SOUTHEAST;
					}
				}
				
				
				for(int i = 1; i <= Math.abs(destination.getColumn() - getLocation().getColumn()); i++){
					switch(d){
					case NORTHEAST:
						//-, +
						if(board.getBoard()[getLocation().getRow() - i][getLocation().getColumn() + i] != null){
							return false;
						}
						break;
					case NORTHWEST:
						//-, -
						if(board.getBoard()[getLocation().getRow() - i][getLocation().getColumn() - i] != null){
							return false;
						}
						break;
					case SOUTHEAST:
						//+, +
						if(board.getBoard()[getLocation().getRow() + i][getLocation().getColumn() + i] != null){
							return false;
						}
						break;
					case SOUTHWEST:
						//+, -
						if(board.getBoard()[getLocation().getRow() + i][getLocation().getColumn() - i] != null){
							return false;
						}
						break;
					default:
						i = Integer.MAX_VALUE;
						return false;
					}
				}
			}
		}
		return true;
	}

}
