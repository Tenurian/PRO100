package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pro100.Models.Board;
import pro100.Models.ChessPiece;
import pro100.Models.Knight;
import pro100.Models.Location;
import pro100.Models.Peon;
import pro100.Models.PieceColor;

public class KnightTest {

	@Test
	public void test() {
		Board board = new Board();
		ChessPiece batman = new Knight(PieceColor.BLACK);
		ChessPiece brucew = new Knight(PieceColor.BLACK);
		ChessPiece joker  = new Peon(PieceColor.WHITE);
		ChessPiece oracle = new Peon(PieceColor.BLACK);
		board.placePiece(batman, new Location("d4"));
		board.placePiece(brucew, new Location("e4"));
		board.placePiece(joker, new Location("d6"));
		board.placePiece(oracle, new Location("f2"));
		System.out.println(board.toString());
		assertFalse(batman.isValidMove(batman.getLocation(), board));
		assertTrue(batman.isValidMove(new Location("c6"), board));
		assertTrue(batman.isValidMove(new Location("e6"), board));
		assertTrue(batman.isValidMove(new Location("b5"), board));
		assertTrue(batman.isValidMove(new Location("f5"), board));
		assertTrue(batman.isValidMove(new Location("b3"), board));
		assertTrue(batman.isValidMove(new Location("f3"), board));
		assertTrue(batman.isValidMove(new Location("c2"), board));
		assertTrue(batman.isValidMove(new Location("e2"), board));
		assertFalse(batman.isValidMove(new Location("b6"), board));
		assertFalse(batman.isValidMove(new Location("d6"), board));
		assertFalse(batman.isValidMove(new Location("f6"), board));
		assertFalse(batman.isValidMove(new Location("c5"), board));
		assertFalse(batman.isValidMove(new Location("d5"), board));
		assertFalse(batman.isValidMove(new Location("e5"), board));
		assertFalse(batman.isValidMove(new Location("c4"), board));
		assertFalse(batman.isValidMove(new Location("e4"), board));
		assertFalse(batman.isValidMove(new Location("c3"), board));
		assertFalse(batman.isValidMove(new Location("d3"), board));
		assertFalse(batman.isValidMove(new Location("e3"), board));
		assertFalse(batman.isValidMove(new Location("b2"), board));
		assertFalse(batman.isValidMove(new Location("d2"), board));
		assertFalse(batman.isValidMove(new Location("f2"), board));
		/*================================================*/
		assertTrue(brucew.isValidMove(new Location("d6"), board));
		assertTrue(brucew.isValidMove(new Location("f6"), board));
		assertTrue(brucew.isValidMove(new Location("c5"), board));
		assertTrue(brucew.isValidMove(new Location("g5"), board));
		assertTrue(brucew.isValidMove(new Location("c3"), board));
		assertTrue(brucew.isValidMove(new Location("g3"), board));
		assertTrue(brucew.isValidMove(new Location("d2"), board));
		assertFalse(brucew.isValidMove(new Location("f2"), board));
		
	}

}
