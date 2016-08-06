package pieces;

import pro100.Board;
import pro100.Location;

public class King extends ChessPiece {

	public King(PieceColor c, Board b){
		super(PieceType.KING, c, b);
	}


	@Override
	public boolean isValidMove(Location destination) {
		if(destination == this.getLocation()){
			return false;
		}
		if(Math.abs(destination.getColumn() - this.getLocation().getColumn()) > 1 ||
				Math.abs(destination.getRow() - this.getLocation().getRow()) > 1){
			return false;
		} else {
			if(board.getPiece(destination) == null){
				//check if it's "checked"
				for(ChessPiece p : board.getPieces((this.getPieceColor() == PieceColor.WHITE)?PieceColor.BLACK:PieceColor.WHITE)){
					if(p.isValidMove(destination)){
						return false;
					}
				}
			} else {
				//something exists at target destination
				if(board.getPiece(destination).getPieceColor() == this.getPieceColor()){
					//same color, auto-fail
					return false;
				} else {
					//different color, check if location is "checked"
					for(ChessPiece p : board.getPieces((this.getPieceColor() == PieceColor.WHITE)?PieceColor.BLACK:PieceColor.WHITE)){
						/* had to create temporary board with just the King and enemy piece, 
						 * so that it didn't automatically fail due to there being a piece at 
						 * the desired location already. 
						 * Also added a check so that we don't look at the piece in the desired
						 * location, as that will cause issues with the piece logic in certain 
						 * cases.
						 */
						if(p.getLocation() != destination){
							Board temp = new Board();
//							temp.placePiece(this, this.getLocation());
							ChessPiece tempPiece = null;
							switch(p.getPieceType()){
							case BISHOP:
								tempPiece = new Bishop(p.getPieceColor(), temp);
								break;
							case KING:
								tempPiece = new King(p.getPieceColor(), temp);
								break;
							case KNIGHT:
								tempPiece = new Knight(p.getPieceColor(), temp);
								break;
							case PEON:
								tempPiece = new Peon(p.getPieceColor(), temp);
								break;
							case QUEEN:
								tempPiece = new Queen(p.getPieceColor(), temp);
								break;
							case ROOK:
								tempPiece = new Rook(p.getPieceColor(), temp);
								break;
							}
							temp.placePiece(tempPiece, p.getLocation());
							if(temp.getPiece(p.getLocation()).isValidMove(destination)){
								return false;
							}
						}
					}
				}
			}
		}

		return true;
	}

}
