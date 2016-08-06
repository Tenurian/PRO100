package pieces;

import pro100.Board;
import pro100.Location;

//make this abstract later
public abstract class ChessPiece {
	@Override
	public String toString() {
		return c.toString() + " " + p.getName() + " @ " + location.toString();
	}
	private final PieceType p;
	private final PieceColor c;
	private Location location;
	protected Board board;
	public ChessPiece(PieceType p, PieceColor c, Board board) {
		this.p = p;
		this.c = c;
		this.board = board;
	}
	public String getToken(){
		String token = this.getPieceType().getToken();
		token = (this.getPieceColor() == PieceColor.WHITE)?token.toUpperCase():token;
		return token;
	}
	public PieceType getPieceType() {
		return p;
	}
	public PieceColor getPieceColor() {
		return c;
	}
	public abstract boolean isValidMove(Location destination);
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	enum Direction {
		NORTH,
		EAST,
		SOUTH,
		WEST,
		NORTHEAST,
		SOUTHEAST,
		SOUTHWEST,
		NORTHWEST
	}
}
