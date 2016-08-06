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
		ChessPiece jones = new Peon(PieceColor.BLACK, board);
		ChessPiece peter = new Bishop(PieceColor.WHITE, board);
		ChessPiece walter = new Bishop(PieceColor.WHITE, board);
		board.placePiece(walter, new Location("d5"));
		board.placePiece(peter, new Location("f3"));
		board.placePiece(jones, new Location("b3"));
		System.out.println(board.toString());
		assertFalse(walter.isValidMove(walter.getLocation()));
		assertFalse(walter.isValidMove(new Location("d7")));
		assertTrue(walter.isValidMove(new Location("g8")));
		assertTrue(walter.isValidMove(new Location("a8")));
		assertFalse(walter.isValidMove(new Location("f3")));
		assertFalse(walter.isValidMove(new Location("g2")));
		assertTrue(walter.isValidMove(new Location("b3")));
		assertFalse(walter.isValidMove(new Location("a2")));
	}

}
