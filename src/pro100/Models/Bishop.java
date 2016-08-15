package pro100.Models;

public class Bishop extends ChessPiece {

	public Bishop(PieceColor c) {
		super(PieceType.BISHOP, c);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isValidMove(Location destination, Board board) {
		if(destination == this.getLocation()){
			return false;
		}
		if(Math.abs(destination.getColumn() - this.getLocation().getColumn()) != Math.abs(destination.getRow() - this.getLocation().getRow())){
			return false;
		} else {
			Direction d = null;
			if(board.getPiece(destination) != null){
				if(board.getPiece(destination).getPieceColor() == this.getPieceColor()){
					return false;
				} else {
					//loop up to destination
					if(destination.getRow() < this.getLocation().getRow()){
						//going north
						if(destination.getColumn() < this.getLocation().getColumn()){
							//going west
							d = Direction.NORTHWEST;
						} else {
							//going east
							d = Direction.NORTHEAST;
						}
					} else {
						//going south
						if(destination.getColumn() < this.getLocation().getColumn()){
							//going west
							d = Direction.SOUTHWEST;
						} else {
							//going east
							d = Direction.SOUTHEAST;
						}
					}
					
					
					for(int i = 1; i < Math.abs(destination.getColumn() - this.getLocation().getColumn()); i++){
						switch(d){
						case NORTHEAST:
							//-, +
							if(board.toArray()[this.getLocation().getRow() - i][this.getLocation().getColumn() + i] != null){
								return false;
							}
							break;
						case NORTHWEST:
							//-, -
							if(board.toArray()[this.getLocation().getRow() - i][this.getLocation().getColumn() - i] != null){
								return false;
							}
							break;
						case SOUTHEAST:
							//+, +
							if(board.toArray()[this.getLocation().getRow() + i][this.getLocation().getColumn() + i] != null){
								return false;
							}
							break;
						case SOUTHWEST:
							//+, -
							if(board.toArray()[this.getLocation().getRow() + i][this.getLocation().getColumn() - i] != null){
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
				if(destination.getRow() < this.getLocation().getRow()){
					//going north
					if(destination.getColumn() < this.getLocation().getColumn()){
						//going west
						d = Direction.NORTHWEST;
					} else {
						//going east
						d = Direction.NORTHEAST;
					}
				} else {
					//going south
					if(destination.getColumn() < this.getLocation().getColumn()){
						//going west
						d = Direction.SOUTHWEST;
					} else {
						//going east
						d = Direction.SOUTHEAST;
					}
				}
				
				
				for(int i = 1; i <= Math.abs(destination.getColumn() - this.getLocation().getColumn()); i++){
					switch(d){
					case NORTHEAST:
						//-, +
						if(board.toArray()[this.getLocation().getRow() - i][this.getLocation().getColumn() + i] != null){
							return false;
						}
						break;
					case NORTHWEST:
						//-, -
						if(board.toArray()[this.getLocation().getRow() - i][this.getLocation().getColumn() - i] != null){
							return false;
						}
						break;
					case SOUTHEAST:
						//+, +
						if(board.toArray()[this.getLocation().getRow() + i][this.getLocation().getColumn() + i] != null){
							return false;
						}
						break;
					case SOUTHWEST:
						//+, -
						if(board.toArray()[this.getLocation().getRow() + i][this.getLocation().getColumn() - i] != null){
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
