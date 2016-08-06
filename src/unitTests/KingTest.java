package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Peon;
import pieces.PieceColor;
import pro100.Board;
import pro100.Location;

public class KingTest {

	@Test
	public void test() {
		Board board = new Board();
		ChessPiece robert = new King(PieceColor.WHITE, board);
		ChessPiece verys = new Bishop(PieceColor.BLACK, board);
		board.placePiece(robert, new Location("f3"));
		board.placePiece(verys, new Location("d6"));
		board.placePiece(new Peon(PieceColor.BLACK, board ), new Location("g3"));
		board.placePiece(new Peon(PieceColor.WHITE, board ), new Location("e3"));
		board.placePiece(new Peon(PieceColor.BLACK, board ), new Location("g2"));
		System.out.println(board.toString());
		assertFalse(robert.isValidMove(robert.getLocation()));
		assertTrue(robert.isValidMove(new Location("e4")));
		assertFalse(robert.isValidMove(new Location("f4")));
		assertTrue(robert.isValidMove(new Location("g4")));
		assertFalse(robert.isValidMove(new Location("e3")));
		assertFalse(robert.isValidMove(new Location("g3")));
	}

}
