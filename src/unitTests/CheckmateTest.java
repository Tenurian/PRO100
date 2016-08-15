package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pro100.Models.*;

public class CheckmateTest {
	
	@Test
	public void test(){
		Board board = new Board();
		King king = new King(PieceColor.WHITE);
		board.placePiece(king, new Location("d4"));
		System.out.println(board);
		assertFalse(king.isInCheck(board));
		assertFalse(king.isCheckMate(board));
		Queen enemyQueen = new Queen(PieceColor.BLACK);
		board.placePiece(enemyQueen, new Location("f2"));
		System.out.println(board);
		assertTrue(king.isInCheck(board));
		assertFalse(king.isCheckMate(board));
		Rook r1 = new Rook(PieceColor.BLACK);
		Rook r2 = new Rook(PieceColor.BLACK);
		Rook r3 = new Rook(PieceColor.BLACK);
		Rook r4 = new Rook(PieceColor.BLACK);
		board.placePiece(r1, new Location("c8"));
		board.placePiece(r2, new Location("e8"));
		board.placePiece(r3, new Location("a5"));
		board.placePiece(r4, new Location("a3"));
		System.out.println(board);
		assertTrue(king.isInCheck(board));
		assertTrue(king.isCheckMate(board));
		Rook ally = new Rook(PieceColor.WHITE);
		board.placePiece(ally, new Location("e1"));
		System.out.println(board);
		assertTrue(king.isInCheck(board));
		assertFalse(king.isCheckMate(board));
	}

}
