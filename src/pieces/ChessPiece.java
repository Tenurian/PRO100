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
	public ChessPiece(PieceType p, PieceColor c) {
		this.p = p;
		this.c = c;
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
	public abstract boolean isValidMove(Location destination, Board board);
	
	public ArrayList<Location> getValidLocations(Board board){
		ArrayList<Location> validLocations = new ArrayList<Location>();
		String cols = "abcdefgh";
		String rows = "87654321";
		for(int i = 0; i < board.getBoard().length; i++){
			for(int j = 0; j < board.getBoard()[i].length; j++){
				if(isValidMove(new Location(cols.charAt(j)+""+rows.charAt(i)), board)){
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
