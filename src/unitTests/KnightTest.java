package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pieces.ChessPiece;
import pieces.Knight;
import pieces.Peon;
import pieces.PieceColor;
import pro100.Board;
import pro100.Location;

public class KnightTest {

	@Test
	public void test() {
		Board board = new Board();
		ChessPiece batman = new Knight(PieceColor.BLACK, board);
		ChessPiece brucew = new Knight(PieceColor.BLACK, board);
		ChessPiece joker  = new Peon(PieceColor.WHITE, board);
		ChessPiece oracle = new Peon(PieceColor.BLACK, board);
		board.placePiece(batman, new Location("d4"));
		board.placePiece(brucew, new Location("e4"));
		board.placePiece(joker, new Location("d6"));
		board.placePiece(oracle, new Location("f2"));
		System.out.println(board.toString());
		assertFalse(batman.isValidMove(batman.getLocation()));
		assertTrue(batman.isValidMove(new Location("c6")));
		assertTrue(batman.isValidMove(new Location("e6")));
		assertTrue(batman.isValidMove(new Location("b5")));
		assertTrue(batman.isValidMove(new Location("f5")));
		assertTrue(batman.isValidMove(new Location("b3")));
		assertTrue(batman.isValidMove(new Location("f3")));
		assertTrue(batman.isValidMove(new Location("c2")));
		assertTrue(batman.isValidMove(new Location("e2")));
		assertFalse(batman.isValidMove(new Location("b6")));
		assertFalse(batman.isValidMove(new Location("d6")));
		assertFalse(batman.isValidMove(new Location("f6")));
		assertFalse(batman.isValidMove(new Location("c5")));
		assertFalse(batman.isValidMove(new Location("d5")));
		assertFalse(batman.isValidMove(new Location("e5")));
		assertFalse(batman.isValidMove(new Location("c4")));
		assertFalse(batman.isValidMove(new Location("e4")));
		assertFalse(batman.isValidMove(new Location("c3")));
		assertFalse(batman.isValidMove(new Location("d3")));
		assertFalse(batman.isValidMove(new Location("e3")));
		assertFalse(batman.isValidMove(new Location("b2")));
		assertFalse(batman.isValidMove(new Location("d2")));
		assertFalse(batman.isValidMove(new Location("f2")));
		/*================================================*/
		assertTrue(brucew.isValidMove(new Location("d6")));
		assertTrue(brucew.isValidMove(new Location("f6")));
		assertTrue(brucew.isValidMove(new Location("c5")));
		assertTrue(brucew.isValidMove(new Location("g5")));
		assertTrue(brucew.isValidMove(new Location("c3")));
		assertTrue(brucew.isValidMove(new Location("g3")));
		assertTrue(brucew.isValidMove(new Location("d2")));
		assertFalse(brucew.isValidMove(new Location("f2")));
		
	}

}
