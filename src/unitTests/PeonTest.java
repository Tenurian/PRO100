package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pro100.Models.Board;
import pro100.Models.Location;
import pro100.Models.Peon;
import pro100.Models.PieceColor;

public class PeonTest {

	@Test
	public void test() {
		Board board = new Board();
		Peon blackPeonOne = new Peon(PieceColor.BLACK);
		Peon blackPeonTwo = new Peon(PieceColor.BLACK);
		Peon blackPeonThree = new Peon(PieceColor.BLACK);
		Peon whitePeonOne = new Peon(PieceColor.WHITE);
		Peon whitePeonTwo = new Peon(PieceColor.WHITE);
		Peon blackPeonFour = new Peon(PieceColor.BLACK);
		board.placePiece(blackPeonOne, new Location("b7"));
		board.placePiece(blackPeonTwo, new Location("f6"));
		board.placePiece(blackPeonThree, new Location("d6"));
		board.placePiece(whitePeonOne, new Location("e5"));
		board.placePiece(whitePeonTwo, new Location("g5"));
		board.placePiece(blackPeonFour, new Location("d4"));
		System.out.println(board.toString());
		assertFalse(blackPeonOne.isValidMove(blackPeonOne.getLocation(), board));
		assertTrue(blackPeonOne.isValidMove(new Location("b6"), board));
		assertTrue(blackPeonOne.isValidMove(new Location("b5"), board));
		assertFalse(blackPeonOne.isValidMove(new Location("a6"), board));
		assertFalse(blackPeonOne.isValidMove(new Location("c6"), board));
		assertTrue(blackPeonTwo.isValidMove(new Location("f5"), board));
		assertFalse(blackPeonTwo.isValidMove(new Location("f4"), board));
		assertTrue(blackPeonTwo.isValidMove(whitePeonOne.getLocation(), board));
		assertTrue(blackPeonTwo.isValidMove(whitePeonTwo.getLocation(), board));
		assertTrue(whitePeonOne.isValidMove(blackPeonThree.getLocation(), board));
		assertTrue(whitePeonOne.isValidMove(blackPeonTwo.getLocation(), board));
		assertTrue(whitePeonTwo.isValidMove(blackPeonTwo.getLocation(), board));
		assertFalse(blackPeonFour.isValidMove(whitePeonOne.getLocation(), board));
		assertFalse(whitePeonOne.isValidMove(blackPeonFour.getLocation(), board));
	}

}
