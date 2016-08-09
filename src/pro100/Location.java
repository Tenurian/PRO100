package pro100;

import java.util.ArrayList;

public class Location{
	private final int COL, ROW;
	public Location(String location){
		this.COL = "abcdefgh".indexOf(location.charAt(0));
		this.ROW = "87654321".indexOf(location.charAt(1));
	}
	
	public Location(int col, int row){
		this.COL = col;
		this.ROW = row;
	}
	
	public int getColumn(){
		return this.COL;
	}
	public int getRow(){
		return this.ROW;
	}
	
	public ArrayList<Location> getSurroundingLocations(){
		ArrayList<Location> surrounding = new ArrayList<Location>();
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				try{
					char col = "abcdefgh".charAt(COL + j);
					char row = "87654321".charAt(ROW + i);
					if(!new Location(col+""+row).equals(this)){
						surrounding.add(new Location(col+""+row));
					}
				}catch (Exception e) {
				}
			}
		}
		return surrounding;
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