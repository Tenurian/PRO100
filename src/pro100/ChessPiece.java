package pro100;

//make this abstract later
public class ChessPiece {
	private final PieceType p;
	private final PieceColor c;
	public ChessPiece(PieceType p, PieceColor c) {
		this.p = p;
		this.c = c;
	}
	public String getToken(){
		String token = this.getPieceType().getToken();
		token = (this.getPieceColor() == PieceColor.White)?token.toUpperCase():token;
		return token;
	}
	public PieceType getPieceType() {
		return p;
	}
	public PieceColor getPieceColor() {
		return c;
	}
	public String isValidMove(String loc1, String loc2){
		switch(this.p){
		case King:
			break;
		case Queen:
			break;
		case Bishop:
			break;
		case Knight:
			break;
		case Rook:
			break;
		case Pawn:
			break;
		default:
			break;
		
		}
		return null;
	}
}
