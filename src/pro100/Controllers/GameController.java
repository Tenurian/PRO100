package pro100.Controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pro100.Models.Board;
import pro100.Models.ChessPiece;
import pro100.Models.King;
import pro100.Models.Location;
import pro100.Models.PieceColor;
import pro100.Models.PieceType;

public class GameController {
	private Board board;
	private PieceColor turn;
	private King whiteKing, blackKing;

	public GameController() {
		board = new Board();
		board.initialize();
		this.turn = PieceColor.WHITE;
		this.whiteKing = board.getWhiteKing();
		this.blackKing = board.getBlackKing();
	}

	public Board getBoard(){
		return this.board;
	}

	public boolean parseLine(String line){
		boolean running = true;

		//		Matcher placementMatcher = Pattern.compile("([kqbnrp][dl])([a-h][0-8])").matcher(line);
		Matcher moveOneMatcher  = Pattern.compile("([a-h][0-8])([a-h][0-8])").matcher(line);
		Matcher moveTwoMatcher  = Pattern.compile("([a-h][0-8])([a-h][0-8])([a-h][0-8])([a-h][0-8])").matcher(line);
		//placement is disabled
		//		if(placementMatcher.find()){
		//			ChessPiece p;
		//			switch(placementMatcher.group(1).charAt(0)){
		//			case 'k':
		//				p = new King((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK));
		//				break;
		//			case 'q':
		//				p = new Queen((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK));
		//				break;
		//			case 'b':
		//				p = new Bishop((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK));
		//				break;
		//			case 'n':
		//				p = new Knight((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK));
		//				break;
		//			case 'r':
		//				p = new Rook((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK));
		//				break;
		//			case 'p':
		//				p = new Peon((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK));
		//				break;
		//			default:
		//				p = null;
		//				break;
		//			}
		//			System.out.println("Place a " + p.getPieceColor().toString() + " " + p.getPieceType().getName() + " on " + placementMatcher.group(2));
		//			board.placePiece(p, new Location(placementMatcher.group(2)));
		//		} else 
		if(moveTwoMatcher.find()){
			ChessPiece pieceOne = board.getPiece(new Location(moveTwoMatcher.group(1)));
			ChessPiece pieceTwo = board.getPiece(new Location(moveTwoMatcher.group(3)));
			if(pieceOne != null && pieceTwo != null){
				System.out.println("Move the " + pieceOne.toString() + " to " + moveTwoMatcher.group(2) + ", and move the  " + pieceTwo.toString() + " to " + moveTwoMatcher.group(4));
				castle(pieceOne, pieceTwo, new Location(moveTwoMatcher.group(2)), new Location(moveTwoMatcher.group(4)));
				//					board.movePiece(pieceOne, new Location(moveTwoMatcher.group(2)));
				//					board.movePiece(pieceTwo, new Location(moveTwoMatcher.group(4)));
			} else {
				System.err.println("There are no pieces at one or both of the desired locations.");
			}
		} else if(moveOneMatcher.find()){
			ChessPiece piece = board.getPiece(new Location(moveOneMatcher.group(1)));
			if(piece != null){
				System.out.println("Move the " + piece.toString() + " to " + moveOneMatcher.group(2));
				running = movePiece(piece, new Location(moveOneMatcher.group(2)));
			} else {
				System.err.println("There is no piece at the desired location");
			}
		} else {
			System.err.println("Invalid Input: {"+line+"}");
		}
		return running;
	}

	public boolean movePiece(ChessPiece piece, Location destination){
		ChessPiece possibleEnemy = board.getPiece(destination);
		if(piece.getPieceColor() == turn){
			if(piece.isValidMove(destination, board)){
				boolean kingInCheck = false;
				Location oldLocation = piece.getLocation();
				board.toArray()[piece.getLocation().getRow()][piece.getLocation().getColumn()] = null;
				piece.setLocation(destination);
				board.toArray()[destination.getRow()][destination.getColumn()] = piece;
//				System.out.println(piece);
				for(ChessPiece p : board.getPieces((turn == PieceColor.WHITE)?PieceColor.BLACK:PieceColor.WHITE)){
					switch(turn){
					case BLACK:
						if(p.isValidMove(blackKing.getLocation(), board)){
							kingInCheck = true;
						}
						break;
					case WHITE:
						if(p.isValidMove(whiteKing.getLocation(), board)){
							kingInCheck = true;
						}
						break;
					}
				}
				if(kingInCheck){
					//instead of setting it to null, set it to whatever was at the destination.
					board.toArray()[destination.getRow()][destination.getColumn()] = possibleEnemy;
					board.toArray()[oldLocation.getRow()][oldLocation.getColumn()] = piece;
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
					if(whiteKing.isCheckMate(board)){
						System.out.println("The "+whiteKing.toString() + " is in checkmate. Black Player Wins!");
						return false;
					} else {
						if(whiteKing.isInCheck(board)){
							board.getPieces(PieceColor.BLACK).iterator().forEachRemaining(p -> {
								if(p.isValidMove(whiteKing.getLocation(), board)){
									System.out.println("The " + whiteKing.toString() + " is in check from the " + p.toString());
								}
							});
						} 
					}
				} else {
					if(blackKing.isCheckMate(board)){
						System.out.println("The "+blackKing.toString() + " is in checkmate. White Player Wins!");
						return false;
					} else {
						if(blackKing.isInCheck(board)){
							board.getPieces(PieceColor.WHITE).iterator().forEachRemaining(p -> {
								if(p.isValidMove(blackKing.getLocation(), board)){
									System.out.println("The " + blackKing.toString() + " is in check from the " + p.toString());
								}
							});
						}
					}
				}
			} else {
				System.out.println("Cannot move the " + piece.toString() + " to " + destination.toString());
			}
		} else {
			System.out.println("It is the "+ turn.toString() +" player's turn.");
		}
		return true;
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
					if(board.getPiece(destOne) == null && board.getPiece(destTwo) == null){
						if(board.getPiece(new Location(((king.getPieceColor() == PieceColor.WHITE)?"f2":"f7"))).getPieceType() != null &&
						   board.getPiece(new Location(((king.getPieceColor() == PieceColor.WHITE)?"g2":"g7"))).getPieceType() != null &&
						   board.getPiece(new Location(((king.getPieceColor() == PieceColor.WHITE)?"h2":"h7"))).getPieceType() != null){
							Location locOne = pieceOne.getLocation();
							Location locTwo = pieceTwo.getLocation();
							this.board.placePiece(pieceOne, destOne);
							this.board.placePiece(pieceTwo, destTwo);
							this.board.placePiece(null, locOne);
							this.board.placePiece(null, locTwo);
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

}
