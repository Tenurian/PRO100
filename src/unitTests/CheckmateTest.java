package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import pieces.*;
import pro100.Board;
import pro100.Location;

public class CheckmateTest {
	
	@Test
	public void test(){
		Board board = new Board();
		King king = new King(PieceColor.WHITE, board);
		board.placePiece(king, new Location("d4"));
		System.out.println(board);
		assertFalse(king.isInCheck());
		assertFalse(king.isCheckMate());
		Queen enemyQueen = new Queen(PieceColor.BLACK, board);
		board.placePiece(enemyQueen, new Location("f2"));
		System.out.println(board);
		assertTrue(king.isInCheck());
		assertFalse(king.isCheckMate());
		Rook r1 = new Rook(PieceColor.BLACK, board);
		Rook r2 = new Rook(PieceColor.BLACK, board);
		Rook r3 = new Rook(PieceColor.BLACK, board);
		Rook r4 = new Rook(PieceColor.BLACK, board);
		board.placePiece(r1, new Location("c8"));
		board.placePiece(r2, new Location("e8"));
		board.placePiece(r3, new Location("a5"));
		board.placePiece(r4, new Location("a3"));
		System.out.println(board);
		assertTrue(king.isInCheck());
		assertTrue(king.isCheckMate());
		Rook ally = new Rook(PieceColor.WHITE, board);
		board.placePiece(ally, new Location("e1"));
		System.out.println(board);
		assertTrue(king.isInCheck());
		assertFalse(king.isCheckMate());
	}

}
