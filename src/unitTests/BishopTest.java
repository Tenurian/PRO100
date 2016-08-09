package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pieces.Bishop;
import pieces.ChessPiece;
import pieces.Peon;
import pieces.PieceColor;
import pro100.Board;
import pro100.Location;

public class BishopTest {

	@Test
	public void test() {
		Board board = new Board();
		//Fringe references. :D
		ChessPiece jones = new Peon(PieceColor.BLACK);
		ChessPiece peter = new Bishop(PieceColor.WHITE);
		ChessPiece walter = new Bishop(PieceColor.WHITE);
		board.placePiece(walter, new Location("d5"));
		board.placePiece(peter, new Location("f3"));
		board.placePiece(jones, new Location("b3"));
		System.out.println(board.toString());
		assertFalse(walter.isValidMove(walter.getLocation(), board));
		assertFalse(walter.isValidMove(new Location("d7"), board));
		assertTrue(walter.isValidMove(new Location("g8"), board));
		assertTrue(walter.isValidMove(new Location("a8"), board));
		assertFalse(walter.isValidMove(new Location("f3"), board));
		assertFalse(walter.isValidMove(new Location("g2"), board));
		assertTrue(walter.isValidMove(new Location("b3"), board));
		assertFalse(walter.isValidMove(new Location("a2"), board));
	}

}
