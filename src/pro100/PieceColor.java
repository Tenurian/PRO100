package pro100;

public enum PieceColor {
	White ("white"),
	Black ("black");
	private final String v;
	private PieceColor(String value){
		v = value;
	}

	@Override
	public String toString(){
		return this.v;
	}
}
