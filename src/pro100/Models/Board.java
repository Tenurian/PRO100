package pro100.Models;

import java.util.ArrayList;

public class Board {
	private ChessPiece[][] board;
	private King whiteKing, blackKing;

	public Board(){
		this.board = new ChessPiece[8][8];
	}

	public ChessPiece getPiece(Location loc){
		return board[loc.getRow()][loc.getColumn()];
	}

	public ArrayList<ChessPiece> getPieces(PieceColor color){
		ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				Location loc = new Location(""+("abcdefgh".charAt(i)) + "87654321".charAt(j));
				if(getPiece(loc) != null){
					if(getPiece(loc).getPieceColor() == color){ 
						pieces.add(getPiece(loc));
					}
				}
			}
		}
		return pieces;
	}

	public King getWhiteKing(){
		return this.whiteKing;
	}
	public King getBlackKing(){
		return this.blackKing;
	}

	public void placePiece(ChessPiece piece, Location loc){
		if(piece != null) {
			piece.setLocation(loc);
		}
		board[loc.getRow()][loc.getColumn()] = piece;
	}

	public String toString(){
		String out = "";
		out += ("     A   B   C   D   E   F   G   H");
		out += ("\n   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗");
		for(int i = 0; i < board.length; i++){
			String boardline = " " + ("87654321".substring(i, i+1))+" ║";
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] == null){
					boardline += "   ";
				} else {
					boardline += " " + board[i][j].getToken() + " ";
				}
				boardline += "║";
			}
			out += ("\n"+boardline);
			out += "\n"+((i == board.length-1)?"   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝":"   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣");
		}
		return out;
	}

	public ChessPiece[][] toArray(){
		return this.board;
	}

	public void initialize(){
		placePiece(new Rook(PieceColor.WHITE), new Location("a1"));
		placePiece(new Knight(PieceColor.WHITE), new Location("b1"));
		placePiece(new Bishop(PieceColor.WHITE), new Location("c1"));
		placePiece(new Queen(PieceColor.WHITE), new Location("d1"));
		this.whiteKing = new King(PieceColor.WHITE);
		placePiece(whiteKing, new Location("e1"));
		placePiece(new Bishop(PieceColor.WHITE), new Location("f1"));
		placePiece(new Knight(PieceColor.WHITE), new Location("g1"));
		placePiece(new Rook(PieceColor.WHITE), new Location("h1"));
		placePiece(new Peon(PieceColor.WHITE), new Location("a2"));
		placePiece(new Peon(PieceColor.WHITE), new Location("b2"));
		placePiece(new Peon(PieceColor.WHITE), new Location("c2"));
		placePiece(new Peon(PieceColor.WHITE), new Location("d2"));
		placePiece(new Peon(PieceColor.WHITE), new Location("e2"));
		placePiece(new Peon(PieceColor.WHITE), new Location("f2"));
		placePiece(new Peon(PieceColor.WHITE), new Location("g2"));
		placePiece(new Peon(PieceColor.WHITE), new Location("h2"));


		placePiece(new Rook(PieceColor.BLACK), new Location("a8"));
		placePiece(new Knight(PieceColor.BLACK), new Location("b8"));
		placePiece(new Bishop(PieceColor.BLACK), new Location("c8"));
		placePiece(new Queen(PieceColor.BLACK), new Location("d8"));
		this.blackKing = new King(PieceColor.BLACK);
		placePiece(blackKing, new Location("e8"));
		placePiece(new Bishop(PieceColor.BLACK), new Location("f8"));
		placePiece(new Knight(PieceColor.BLACK), new Location("g8"));
		placePiece(new Rook(PieceColor.BLACK), new Location("h8"));
		placePiece(new Peon(PieceColor.BLACK), new Location("a7"));
		placePiece(new Peon(PieceColor.BLACK), new Location("b7"));
		placePiece(new Peon(PieceColor.BLACK), new Location("c7"));
		placePiece(new Peon(PieceColor.BLACK), new Location("d7"));
		placePiece(new Peon(PieceColor.BLACK), new Location("e7"));
		placePiece(new Peon(PieceColor.BLACK), new Location("f7"));
		placePiece(new Peon(PieceColor.BLACK), new Location("g7"));
		placePiece(new Peon(PieceColor.BLACK), new Location("h7"));
	}
}
