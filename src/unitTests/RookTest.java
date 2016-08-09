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
		ChessPiece rook = new Rook(PieceColor.WHITE);
		ChessPiece ally = new Rook(PieceColor.WHITE);
		ChessPiece enemy = new Rook(PieceColor.BLACK);
		ChessPiece enemy2 = new Rook(PieceColor.BLACK);
		board.placePiece(rook, new Location("e5"));
		board.placePiece(enemy, new Location("e2"));
		board.placePiece(ally, new Location("b5"));
		board.placePiece(enemy2, new Location("a5"));
		System.out.println(board.toString());
		assertFalse(rook.isValidMove(rook.getLocation(), board));
		assertFalse(rook.isValidMove(new Location("d6"), board));
		assertFalse(rook.isValidMove(new Location("f6"), board));
		assertFalse(rook.isValidMove(new Location("d4"), board));
		assertFalse(rook.isValidMove(new Location("f4"), board));
		assertTrue(rook.isValidMove(new Location("c5"), board));
		assertTrue(rook.isValidMove(new Location("e8"), board));
		assertTrue(rook.isValidMove(new Location("h5"), board));
		assertTrue(rook.isValidMove(new Location("e3"), board));
		assertFalse(rook.isValidMove(new Location("a5"), board));
		assertFalse(rook.isValidMove(new Location("b5"), board));
		assertFalse(rook.isValidMove(new Location("e1"), board));
		assertTrue(rook.isValidMove(new Location("e2"), board));
	}

}
