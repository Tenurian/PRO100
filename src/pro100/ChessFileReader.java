package pro100;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Peon;
import pieces.PieceColor;
import pieces.Queen;
import pieces.Rook;

public class ChessFileReader {

	private File file;
//	private ChessPiece[][] board;
	private boolean verbal = true;
	public Board board;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "";
		if(args.length == 0){
			System.err.println("No Filename provided... would you like to manually input the values? (y/n)");
			Scanner scanLee = new Scanner(System.in);
			if(scanLee.nextLine().equalsIgnoreCase("y")){
				boolean run = true;
				ArrayList<String> inputs = new ArrayList<String>();
				ChessFileReader fileReader = new ChessFileReader();
				System.out.println("Run the initializer? y/n");
				if(scanLee.nextLine().equalsIgnoreCase("y")){
					fileReader.board.initialize();
				}
				System.out.println("\nPlease input the values now.");
				while(run){
					String line = scanLee.nextLine();
					line = line.toLowerCase();
					if(line.length() != 0){
						inputs.add(line);
					} else {
						fileReader.processLines(inputs.iterator());
						run = false;
					}
				}
			} else {
				System.err.println("Exiting program...");
			}
			scanLee.close();
		} else {
			filename = args[0];
			ChessFileReader cf = new ChessFileReader(filename);
			cf.board.initialize();
			cf.processLines(cf.getLines());
		}
	}

	public ChessFileReader(){
		board = new Board();
	}

	public ChessFileReader(String chessFileName) {
		file = new File(chessFileName);
		board = new Board();
	}

	public Iterator<String> getLines(){
		ArrayList<String> lines = new ArrayList<String>();
		if(file != null){
			try(FileReader fis = new FileReader(file); BufferedReader buffy = new BufferedReader(fis)){
				String line = "";
				while((line = buffy.readLine()) != null){
					line = line.toLowerCase().replaceAll("[^a-z0-9]", "");
					if(line.length() != 0){
						lines.add(line);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return lines.iterator();
	}

	/*
     1 . 2 . 3 . 4 . 5 . 6 . 7 . 8
   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗
 a ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║
 . ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣
 b ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║
 . ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣
 c ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║
 . ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣
 d ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║
 . ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣
 e ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║
 . ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣
 f ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║
 . ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣
 g ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║
 . ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣
 h ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║ x ║
   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝
	 */

	public void processLines(Iterator<String> lines){
		lines.forEachRemaining(l -> {
			//write parser code here
			System.out.print("\n");
			System.out.println(l);

			Matcher placementMatcher = Pattern.compile("([kqbnrp][dl])([a-h][0-8])").matcher(l);
			Matcher moveOneMatcher  = Pattern.compile("([a-h][0-8])([a-h][0-8])").matcher(l);
			Matcher moveTwoMatcher  = Pattern.compile("([a-h][0-8])([a-h][0-8])([a-h][0-8])([a-h][0-8])").matcher(l);

			if(placementMatcher.find()){
				ChessPiece p;
				switch(placementMatcher.group(1).charAt(0)){
				case 'k':
					p = new King((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK), board);
					break;
				case 'q':
					p = new Queen((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK), board);
					break;
				case 'b':
					p = new Bishop((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK), board);
					break;
				case 'n':
					p = new Knight((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK), board);
					break;
				case 'r':
					p = new Rook((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK), board);
					break;
				case 'p':
					p = new Peon((placementMatcher.group(1).charAt(1)=='l'?PieceColor.WHITE:PieceColor.BLACK), board);
					break;
				default:
					p = null;
					break;
				}
				System.out.println("Place a " + p.getPieceColor().toString() + " " + p.getPieceType().getName() + " on " + placementMatcher.group(2));
				board.placePiece(p, new Location(placementMatcher.group(2)));
			} else if(moveTwoMatcher.find()){
				ChessPiece pieceOne = board.getPiece(new Location(moveTwoMatcher.group(1)));
				ChessPiece pieceTwo = board.getPiece(new Location(moveTwoMatcher.group(3)));
				if(pieceOne != null && pieceTwo != null){
					System.out.println("Move the " + pieceOne.toString() + " to " + moveTwoMatcher.group(2) + ", and move the  " + pieceTwo.toString() + " to " + moveTwoMatcher.group(4));
					board.castle(pieceOne, pieceTwo, new Location(moveTwoMatcher.group(2)), new Location(moveTwoMatcher.group(4)));
//					board.movePiece(pieceOne, new Location(moveTwoMatcher.group(2)));
//					board.movePiece(pieceTwo, new Location(moveTwoMatcher.group(4)));
				} else {
					System.err.println("There are no pieces at one or both of the desired locations.");
				}
			} else if(moveOneMatcher.find()){
				ChessPiece piece = board.getPiece(new Location(moveOneMatcher.group(1)));
				if(piece != null){
					System.out.println("Move the " + piece.toString() + " to " + moveOneMatcher.group(2));
					board.movePiece(piece, new Location(moveOneMatcher.group(2)));
				} else {
					System.err.println("There is no piece at the desired location");
				}
			} else {
				System.err.println("Invalid Input: {"+l+"}");
			}

			//other code will work fine
			if(verbal){
				System.out.println("     A   B   C   D   E   F   G   H");
				System.out.println("   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗");
				for(int i = 0; i < board.getBoard().length; i++){
					String boardline = " " + ("87654321".substring(i, i+1))+" ║";
					for(int j = 0; j < board.getBoard()[i].length; j++){
						if(board.getBoard()[i][j] == null){
							boardline += "   ";
						} else {
							boardline += " " + board.getBoard()[i][j].getToken() + " ";
						}
						boardline += "║";
					}
					System.out.println(boardline);
					System.out.println((i == board.getBoard().length-1)?"   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝":"   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣");
				}
			}
		});
		if(!verbal){
			System.out.println("\n");
			System.out.println("     A   B   C   D   E   F   G   H");
			System.out.println("   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗");
			for(int i = 0; i < board.getBoard().length; i++){
				String boardline = " " + ("87654321".substring(i, i+1))+" ║";
				for(int j = 0; j < board.getBoard()[i].length; j++){
					if(board.getBoard()[i][j] == null){
						boardline += "   ";
					} else {
						boardline += " " + board.getBoard()[i][j].getToken() + " ";
					}
					boardline += "║";
				}
				System.out.println(boardline);
				System.out.println((i == board.getBoard().length-1)?"   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝":"   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣");
			}
		}
	}

}
