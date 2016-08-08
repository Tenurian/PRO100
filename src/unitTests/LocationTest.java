package unitTests;

//import static org.junit.Assert.*;

import org.junit.Test;

import pro100.Location;

public class LocationTest {
	@Test
	public void test() {
		System.out.println(new pro100.Board().toString());
		Location[] tests = {
				new Location("a8"),
				new Location("d8"),
				new Location("h8"),
				new Location("a4"),
				new Location("h4"),
				new Location("a1"),
				new Location("d1"),
				new Location("h1"),
				new Location("d4")
		};

		for(Location s : tests){
			System.out.println();
			System.out.println(s);
			System.out.println("========================");
			for(Location l : s.getSurroundingLocations()){
				System.out.println(l);
			}
		}
	}

}
