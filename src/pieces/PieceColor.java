package pieces;

public enum PieceColor {
	WHITE ("White"),
	BLACK ("Black");
	private final String v;
	private PieceColor(String value){
		v = value;
	}

	@Override
	public String toString(){
		return this.v;
	}
}
