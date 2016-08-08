package pieces;

import java.util.ArrayList;

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
	
	public ArrayList<Location> getValidLocations(){
		ArrayList<Location> validLocations = new ArrayList<Location>();
		String cols = "abcdefgh";
		String rows = "87654321";
		for(int i = 0; i < board.getBoard().length; i++){
			for(int j = 0; j < board.getBoard()[i].length; j++){
				if(isValidMove(new Location(cols.charAt(j)+""+rows.charAt(i)))){
					validLocations.add(new Location(cols.charAt(j)+""+rows.charAt(i)));
				}
			}
		}
		return validLocations;
	}
	
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
