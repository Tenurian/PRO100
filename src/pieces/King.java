package pieces;

import pro100.Board;
import pro100.Location;

public class King extends ChessPiece {

	public King(PieceColor c){
		super(PieceType.KING, c);
	}
	
	public boolean isInCheck(Board board){
		for(ChessPiece p : board.getPieces(this.getPieceColor() == PieceColor.WHITE?PieceColor.BLACK:PieceColor.BLACK)){
			if(p.isValidMove(this.getLocation(), board)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isCheckMate(Board board){
		//is the king in check
		boolean inCheck = this.isInCheck(board), isSurrounded = true;
		
		
		if(inCheck){
			for(Location l : this.getLocation().getSurroundingLocations() ){
				if(this.isValidMove(l, board)){
					isSurrounded = false;
					return false;
				}
			}
			
			if(isSurrounded){
				for(ChessPiece p : board.getPieces(this.getPieceColor())){
					Location origin = p.getLocation();
					for(Location l : p.getValidLocations(board)){
						board.getBoard()[origin.getRow()][origin.getColumn()] = null;
						ChessPiece possibleEnemy = board.getBoard()[l.getRow()][l.getColumn()];
						board.getBoard()[l.getRow()][l.getColumn()] = p;
						if(this.isInCheck(board)){
							board.getBoard()[l.getRow()][l.getColumn()] = possibleEnemy;
							board.getBoard()[origin.getRow()][origin.getColumn()] = p;
						} else {
							board.getBoard()[l.getRow()][l.getColumn()] = possibleEnemy;
							board.getBoard()[origin.getRow()][origin.getColumn()] = p;
							return false;
						}
						board.getBoard()[l.getRow()][l.getColumn()] = possibleEnemy;
						board.getBoard()[origin.getRow()][origin.getColumn()] = p;
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidMove(Location destination, Board board) {
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
					if(p.isValidMove(destination, board)){
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
						if(p.getLocation() != destination){
							Board temp = new Board();
//							temp.placePiece(this, this.getLocation());
							ChessPiece tempPiece = null;
							switch(p.getPieceType()){
							case BISHOP:
								tempPiece = new Bishop(p.getPieceColor());
								break;
							case KING:
								tempPiece = new King(p.getPieceColor());
								break;
							case KNIGHT:
								tempPiece = new Knight(p.getPieceColor());
								break;
							case PEON:
								tempPiece = new Peon(p.getPieceColor());
								break;
							case QUEEN:
								tempPiece = new Queen(p.getPieceColor());
								break;
							case ROOK:
								tempPiece = new Rook(p.getPieceColor());
								break;
							}
							temp.placePiece(tempPiece, p.getLocation());
							if(temp.getPiece(p.getLocation()).isValidMove(destination, temp)){
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
