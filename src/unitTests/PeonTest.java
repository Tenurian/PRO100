package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pieces.Peon;
import pieces.PieceColor;
import pro100.Board;
import pro100.Location;

public class PeonTest {

	@Test
	public void test() {
		Board board = new Board();
		Peon blackPeonOne = new Peon(PieceColor.BLACK, board);
		Peon blackPeonTwo = new Peon(PieceColor.BLACK, board);
		Peon blackPeonThree = new Peon(PieceColor.BLACK, board);
		Peon whitePeonOne = new Peon(PieceColor.WHITE, board);
		Peon whitePeonTwo = new Peon(PieceColor.WHITE, board);
		Peon blackPeonFour = new Peon(PieceColor.BLACK, board);
		board.placePiece(blackPeonOne, new Location("b7"));
		board.placePiece(blackPeonTwo, new Location("f6"));
		board.placePiece(blackPeonThree, new Location("d6"));
		board.placePiece(whitePeonOne, new Location("e5"));
		board.placePiece(whitePeonTwo, new Location("g5"));
		board.placePiece(blackPeonFour, new Location("d4"));
		System.out.println(board.toString());
		assertFalse(blackPeonOne.isValidMove(blackPeonOne.getLocation()));
		assertTrue(blackPeonOne.isValidMove(new Location("b6")));
		assertTrue(blackPeonOne.isValidMove(new Location("b5")));
		assertFalse(blackPeonOne.isValidMove(new Location("a6")));
		assertFalse(blackPeonOne.isValidMove(new Location("c6")));
		assertTrue(blackPeonTwo.isValidMove(new Location("f5")));
		assertFalse(blackPeonTwo.isValidMove(new Location("f4")));
		assertTrue(blackPeonTwo.isValidMove(whitePeonOne.getLocation()));
		assertTrue(blackPeonTwo.isValidMove(whitePeonTwo.getLocation()));
		assertTrue(whitePeonOne.isValidMove(blackPeonThree.getLocation()));
		assertTrue(whitePeonOne.isValidMove(blackPeonTwo.getLocation()));
		assertTrue(whitePeonTwo.isValidMove(blackPeonTwo.getLocation()));
		assertFalse(blackPeonFour.isValidMove(whitePeonOne.getLocation()));
		assertFalse(whitePeonOne.isValidMove(blackPeonFour.getLocation()));
	}

}
