package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pro100.Models.Bishop;
import pro100.Models.Board;
import pro100.Models.ChessPiece;
import pro100.Models.King;
import pro100.Models.Location;
import pro100.Models.Peon;
import pro100.Models.PieceColor;
import pro100.Models.Queen;

public class QueenTest {

	@Test
	public void test() {
		Board board = new Board();
		ChessPiece cersie = new Queen(PieceColor.WHITE);
		ChessPiece danni = new Queen(PieceColor.BLACK);
		ChessPiece tywin = new King(PieceColor.WHITE);
		ChessPiece tyrion = new Peon(PieceColor.WHITE);
		ChessPiece robb = new King(PieceColor.BLACK);
		ChessPiece varys = new Bishop(PieceColor.BLACK);
		board.placePiece(cersie, new Location("d5"));
		board.placePiece(robb, new Location("g2"));
		board.placePiece(danni, new Location("d2"));
		board.placePiece(tywin,new Location("f7"));
		board.placePiece(tyrion, new Location("d7"));
		board.placePiece(varys, new Location("a2"));
		board.placePiece(new Peon(PieceColor.BLACK), new Location("b3"));
		System.out.println(board.toString());
		assertFalse(cersie.isValidMove(cersie.getLocation(), board));
		assertFalse(cersie.isValidMove(new Location("d8"), board));
		assertFalse(cersie.isValidMove(new Location("g8"), board));
		assertFalse(cersie.isValidMove(tywin.getLocation(), board));
		assertFalse(cersie.isValidMove(tyrion.getLocation(), board));
		assertFalse(cersie.isValidMove(new Location("d1"), board));
		assertFalse(cersie.isValidMove(new Location("h1"), board));
		assertTrue(cersie.isValidMove(danni.getLocation(), board));
		assertTrue(cersie.isValidMove(robb.getLocation(), board));
		assertFalse(cersie.isValidMove(varys.getLocation(), board));
	}

}
