package pro100;

public enum PieceType {
	King ("k", "King"),
	Queen ("q", "Queen"),
	Bishop ("b", "Bishop"),
	Knight ("n", "Knight"),
	Rook ("r", "Rook"),
	Pawn ("p", "Pawn");
	private final String token;
	private final String name;
	private PieceType(String value, String name){
		this.token = value;
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public String getToken(){
		return this.token;
	}
}
