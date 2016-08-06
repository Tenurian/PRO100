package pro100;

public class Location{
	private final int COL, ROW;
	public Location(String location){
		this.COL = "abcdefgh".indexOf(location.charAt(0));
		this.ROW = "87654321".indexOf(location.charAt(1));
	}
	public int getColumn(){
		return this.COL;
	}
	public int getRow(){
		return this.ROW;
	}
	@Override
	public String toString() {
		return "Location [ "+ "ABCDEFGH".charAt(COL) + "" + "87654321".charAt(ROW) +" (COL=" + COL + ", ROW=" + ROW + ")]";
	}	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + COL;
		result = prime * result + ROW;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (COL != other.COL)
			return false;
		if (ROW != other.ROW)
			return false;
		return true;
	}
}