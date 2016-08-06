package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Peon;
import pieces.PieceColor;
import pieces.Queen;
import pro100.Board;
import pro100.Location;

public class QueenTest {

	@Test
	public void test() {
		Board board = new Board();
		ChessPiece cersie = new Queen(PieceColor.WHITE, board);
		ChessPiece danni = new Queen(PieceColor.BLACK, board);
		ChessPiece tywin = new King(PieceColor.WHITE, board);
		ChessPiece tyrion = new Peon(PieceColor.WHITE, board);
		ChessPiece robb = new King(PieceColor.BLACK, board);
		ChessPiece varys = new Bishop(PieceColor.BLACK, board);
		board.placePiece(cersie, new Location("d5"));
		board.placePiece(robb, new Location("g2"));
		board.placePiece(danni, new Location("d2"));
		board.placePiece(tywin,new Location("f7"));
		board.placePiece(tyrion, new Location("d7"));
		board.placePiece(varys, new Location("a2"));
		board.placePiece(new Peon(PieceColor.BLACK, board), new Location("b3"));
		System.out.println(board.toString());
		assertFalse(cersie.isValidMove(cersie.getLocation()));
		assertFalse(cersie.isValidMove(new Location("d8")));
		assertFalse(cersie.isValidMove(new Location("g8")));
		assertFalse(cersie.isValidMove(tywin.getLocation()));
		assertFalse(cersie.isValidMove(tyrion.getLocation()));
		assertFalse(cersie.isValidMove(new Location("d1")));
		assertFalse(cersie.isValidMove(new Location("h1")));
		assertTrue(cersie.isValidMove(danni.getLocation()));
		assertTrue(cersie.isValidMove(robb.getLocation()));
		assertFalse(cersie.isValidMove(varys.getLocation()));
	}

}
