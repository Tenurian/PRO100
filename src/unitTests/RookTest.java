package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pieces.ChessPiece;
import pieces.PieceColor;
import pieces.Rook;
import pro100.Board;
import pro100.Location;

public class RookTest {

	@Test
	public void test() {
		Board board = new Board();
		ChessPiece rook = new Rook(PieceColor.WHITE, board);
		ChessPiece ally = new Rook(PieceColor.WHITE, board);
		ChessPiece enemy = new Rook(PieceColor.BLACK, board);
		board.placePiece(rook, new Location("e5"));
		board.placePiece(enemy, new Location("e2"));
		board.placePiece(ally, new Location("b5"));
		System.out.println(board.toString());
		assertFalse(rook.isValidMove(rook.getLocation()));
		assertFalse(rook.isValidMove(new Location("d6")));
		assertFalse(rook.isValidMove(new Location("f6")));
		assertFalse(rook.isValidMove(new Location("d4")));
		assertFalse(rook.isValidMove(new Location("f4")));
		assertTrue(rook.isValidMove(new Location("c5")));
		assertTrue(rook.isValidMove(new Location("e8")));
		assertTrue(rook.isValidMove(new Location("h5")));
		assertTrue(rook.isValidMove(new Location("e3")));
		assertFalse(rook.isValidMove(new Location("a5")));
		assertFalse(rook.isValidMove(new Location("b5")));
		assertFalse(rook.isValidMove(new Location("e1")));
		assertTrue(rook.isValidMove(new Location("e2")));
	}

}
