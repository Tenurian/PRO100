package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pieces.*;
import pro100.Board;
import pro100.Location;

public class KingTest {

	@Test
	public void test() {
		Board board = new Board();
		ChessPiece robert = new King(PieceColor.WHITE);
		ChessPiece verys = new Queen(PieceColor.BLACK);
		board.placePiece(robert, new Location("f3"));
		board.placePiece(verys, new Location("d6"));
		board.placePiece(new Peon(PieceColor.BLACK), new Location("g3"));
		board.placePiece(new Peon(PieceColor.WHITE), new Location("e3"));
		board.placePiece(new Peon(PieceColor.BLACK), new Location("g2"));
		System.out.println(board.toString());
		assertFalse(robert.isValidMove(robert.getLocation(), board));
		assertTrue(robert.isValidMove(new Location("e4"), board));
		assertFalse(robert.isValidMove(new Location("f4"), board));
		assertTrue(robert.isValidMove(new Location("g4"), board));
		assertFalse(robert.isValidMove(new Location("e3"), board));
		assertFalse(robert.isValidMove(new Location("g3"), board));
	}

}
