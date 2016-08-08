package pro100;

import java.util.ArrayList;

import pieces.*;

public class Board {
	private ChessPiece[][] board;
	private PieceColor turn;

	private King whiteKing, blackKing;

	public Board(){
		this.board = new ChessPiece[8][8];
		this.turn = PieceColor.WHITE;
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

	public void castle(ChessPiece pieceOne, ChessPiece pieceTwo, Location destOne, Location destTwo){
		if(pieceOne.getPieceColor() != pieceTwo.getPieceColor()){
			System.out.println("Pieces must be same color to castle");
		} else {
			if(pieceOne.getPieceColor() == turn){
				ChessPiece king = (pieceOne.getPieceType() == PieceType.KING)?pieceOne:pieceTwo;
				ChessPiece rook = (pieceOne.getPieceType() == PieceType.KING)?pieceTwo:pieceOne;
				if(king.getLocation().equals(new Location(((king.getPieceColor() == PieceColor.WHITE)?"e1":"e8"))) && 
						rook.getLocation().equals(new Location(((king.getPieceColor() == PieceColor.WHITE)?"h1":"h8")))){
					if(getPiece(destOne) == null && getPiece(destTwo) == null){
						if(getPiece(new Location(((king.getPieceColor() == PieceColor.WHITE)?"f2":"f7"))).getPieceType() != null &&
								getPiece(new Location(((king.getPieceColor() == PieceColor.WHITE)?"g2":"g7"))).getPieceType() != null &&
								getPiece(new Location(((king.getPieceColor() == PieceColor.WHITE)?"h2":"h7"))).getPieceType() != null){
							//							this.placePiece(king, new Location(((king.getPieceColor() == PieceColor.WHITE)?"g1":"g8")));
							//							this.placePiece(rook, new Location(((rook.getPieceColor() == PieceColor.WHITE)?"f1":"f8")));
							Location locOne = pieceOne.getLocation();
							Location locTwo = pieceTwo.getLocation();
							this.placePiece(pieceOne, destOne);
							this.placePiece(pieceTwo, destTwo);
							this.placePiece(null, locOne);
							this.placePiece(null, locTwo);
							turn = (turn == PieceColor.WHITE)?PieceColor.BLACK:PieceColor.WHITE;
						} else {
							System.out.println("Invalid Setup for Castling (3)");
						}
					} else {
						System.out.println("Invalid Setup for Castling (2)");
					}
				} else {
					System.out.println("Invalid Setup for Castling (1)");
				}
			} else {
				System.out.println("It is the "+ turn.toString() +" player's turn.");
			}
		}
	}

	public PieceColor getTurn(){
		return this.turn;
	}

	public void movePiece(ChessPiece piece, Location destination){
		ChessPiece possibleEnemy = getPiece(destination);
		if(piece.getPieceColor() == turn){
			if(piece.isValidMove(destination)){
				boolean kingInCheck = false;
				Location oldLocation = piece.getLocation();
				board[piece.getLocation().getRow()][piece.getLocation().getColumn()] = null;
				piece.setLocation(destination);
				board[destination.getRow()][destination.getColumn()] = piece;
				for(ChessPiece p : getPieces((turn == PieceColor.WHITE)?PieceColor.BLACK:PieceColor.WHITE)){
					switch(turn){
					case BLACK:
						if(p.isValidMove(blackKing.getLocation())){
							kingInCheck = true;
						}
						break;
					case WHITE:
						if(p.isValidMove(whiteKing.getLocation())){
							kingInCheck = true;
						}
						break;
					}
				}
				if(kingInCheck){
					//instead of setting it to null, set it to whatever was at the destination.
					board[destination.getRow()][destination.getColumn()] = possibleEnemy;
					board[oldLocation.getRow()][oldLocation.getColumn()] = piece;
					piece.setLocation(oldLocation);
					if(turn == PieceColor.WHITE){
						System.out.println("Illegal move: The "+ whiteKing.toString() + " is still in check.");
					} else {
						System.out.println("Illegal move: The "+ blackKing.toString() + " is still in check.");
					}
				} else {
					turn = (turn == PieceColor.WHITE)? PieceColor.BLACK:PieceColor.WHITE;
					System.out.println("It is now the "+ turn.toString() +" player's turn.");
				}



				if(turn == PieceColor.WHITE){
					getPieces(PieceColor.BLACK).iterator().forEachRemaining(p -> {
						if(p.isValidMove(whiteKing.getLocation())){
							System.out.println("The " + whiteKing.toString() + " is in check from the " + p.toString());
						}
					});
				} else {
					getPieces(PieceColor.WHITE).iterator().forEachRemaining(p -> {
						if(p.isValidMove(blackKing.getLocation())){
							System.out.println("The " + blackKing.toString() + " is in check from the " + p.toString());
						}
					});
				}
			} else {
				System.out.println("Cannot move the " + piece.toString() + " to " + destination.toString());
			}
		} else {
			System.out.println("It is the "+ turn.toString() +" player's turn.");
		}
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

	public ChessPiece[][] getBoard(){
		return this.board;
	}

	public void initialize(){
		placePiece(new Rook(PieceColor.WHITE, this), new Location("a1"));
		placePiece(new Knight(PieceColor.WHITE, this), new Location("b1"));
		placePiece(new Bishop(PieceColor.WHITE, this), new Location("c1"));
		placePiece(new Queen(PieceColor.WHITE, this), new Location("d1"));
		this.whiteKing = new King(PieceColor.WHITE, this);
		placePiece(whiteKing, new Location("e1"));
		placePiece(new Bishop(PieceColor.WHITE, this), new Location("f1"));
		placePiece(new Knight(PieceColor.WHITE, this), new Location("g1"));
		placePiece(new Rook(PieceColor.WHITE, this), new Location("h1"));
		placePiece(new Peon(PieceColor.WHITE, this), new Location("a2"));
		placePiece(new Peon(PieceColor.WHITE, this), new Location("b2"));
		placePiece(new Peon(PieceColor.WHITE, this), new Location("c2"));
		placePiece(new Peon(PieceColor.WHITE, this), new Location("d2"));
		placePiece(new Peon(PieceColor.WHITE, this), new Location("e2"));
		placePiece(new Peon(PieceColor.WHITE, this), new Location("f2"));
		placePiece(new Peon(PieceColor.WHITE, this), new Location("g2"));
		placePiece(new Peon(PieceColor.WHITE, this), new Location("h2"));


		placePiece(new Rook(PieceColor.BLACK, this), new Location("a8"));
		placePiece(new Knight(PieceColor.BLACK, this), new Location("b8"));
		placePiece(new Bishop(PieceColor.BLACK, this), new Location("c8"));
		placePiece(new Queen(PieceColor.BLACK, this), new Location("d8"));
		this.blackKing = new King(PieceColor.BLACK, this);
		placePiece(blackKing, new Location("e8"));
		placePiece(new Bishop(PieceColor.BLACK, this), new Location("f8"));
		placePiece(new Knight(PieceColor.BLACK, this), new Location("g8"));
		placePiece(new Rook(PieceColor.BLACK, this), new Location("h8"));
		placePiece(new Peon(PieceColor.BLACK, this), new Location("a7"));
		placePiece(new Peon(PieceColor.BLACK, this), new Location("b7"));
		placePiece(new Peon(PieceColor.BLACK, this), new Location("c7"));
		placePiece(new Peon(PieceColor.BLACK, this), new Location("d7"));
		placePiece(new Peon(PieceColor.BLACK, this), new Location("e7"));
		placePiece(new Peon(PieceColor.BLACK, this), new Location("f7"));
		placePiece(new Peon(PieceColor.BLACK, this), new Location("g7"));
		placePiece(new Peon(PieceColor.BLACK, this), new Location("h7"));
		System.out.println(this.toString());
	}
}
