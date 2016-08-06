package pieces;

public enum PieceType {
	KING ("k", "King"),
	QUEEN ("q", "Queen"),
	BISHOP ("b", "Bishop"),
	KNIGHT ("n", "Knight"),
	ROOK ("r", "Rook"),
	PEON ("p", "Peon");
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
