package pro100;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessFileReader {

	private File file;
	private ChessPiece[][] board;
	private static final boolean verbal = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "";
		if(args.length == 0){
			System.err.println("No Filename provided... would you like to manually input the values? (y/n)");
			Scanner scanLee = new Scanner(System.in);
			if(scanLee.nextLine().equalsIgnoreCase("y")){
				boolean run = true;
				ArrayList<String> inputs = new ArrayList<String>();
				System.out.println("\nPlease input the values now.");
				while(run){
					String line = scanLee.nextLine();
					if(line.length() != 0){
						inputs.add(line);
					} else {
						new ChessFileReader().processLines(inputs.iterator());
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
			cf.processLines(cf.getLines());
		}
	}
	
	public ChessFileReader(){
		board = new ChessPiece[8][8];
	}

	public ChessFileReader(String chessFileName) {
		file = new File(chessFileName);
		board = new ChessPiece[8][8];
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
				PieceType pt = null;
				switch(placementMatcher.group(1).charAt(0)){
				case 'k':
					pt = PieceType.King;
					break;
				case 'q':
					pt = PieceType.Queen;
					break;
				case 'b':
					pt = PieceType.Bishop;
					break;
				case 'n':
					pt = PieceType.Knight;
					break;
				case 'r':
					pt = PieceType.Rook;
					break;
				case 'p':
					pt = PieceType.Pawn;
					break;
				default:
					break;
				}
				ChessPiece p = new ChessPiece(pt, (placementMatcher.group(1).charAt(1)=='l'?PieceColor.White:PieceColor.Black));
				System.out.println("Place a " + p.getPieceColor().toString() + " " + p.getPieceType().getName() + " on " + placementMatcher.group(2));
				board["87654321".indexOf(placementMatcher.group(2).charAt(1))]["abcdefgh".indexOf(placementMatcher.group(2).charAt(0))] = p;
			} else if(moveTwoMatcher.find()){
				System.out.println("Move the piece at " + moveTwoMatcher.group(1) + " to " + moveTwoMatcher.group(2) + ", and move the piece at " + moveTwoMatcher.group(3) + " to " + moveTwoMatcher.group(4));
				ChessPiece pieceOne = board["87654321".indexOf(moveTwoMatcher.group(1).charAt(1))]["abcdefgh".indexOf(moveTwoMatcher.group(1).charAt(0))];
				ChessPiece pieceTwo = board["87654321".indexOf(moveTwoMatcher.group(3).charAt(1))]["abcdefgh".indexOf(moveTwoMatcher.group(3).charAt(0))];
				//				String destOne = board["87654321".indexOf(moveTwoMatcher.group(2).charAt(1))]["abcdefgh".indexOf(moveTwoMatcher.group(2).charAt(0))];
				//				String destTwo = board["87654321".indexOf(moveTwoMatcher.group(4).charAt(1))]["abcdefgh".indexOf(moveTwoMatcher.group(4).charAt(0))];
				if(pieceOne != null && pieceTwo != null){
					board["87654321".indexOf(moveTwoMatcher.group(1).charAt(1))]["abcdefgh".indexOf(moveTwoMatcher.group(1).charAt(0))] = null;
					board["87654321".indexOf(moveTwoMatcher.group(2).charAt(1))]["abcdefgh".indexOf(moveTwoMatcher.group(2).charAt(0))] = pieceOne;
					board["87654321".indexOf(moveTwoMatcher.group(3).charAt(1))]["abcdefgh".indexOf(moveTwoMatcher.group(3).charAt(0))] = null;
					board["87654321".indexOf(moveTwoMatcher.group(4).charAt(1))]["abcdefgh".indexOf(moveTwoMatcher.group(4).charAt(0))] = pieceTwo;
				}
			} else if(moveOneMatcher.find()){
				System.out.println("Move the piece at " + moveOneMatcher.group(1) + " to " + moveOneMatcher.group(2));
				ChessPiece piece = board["87654321".indexOf(moveOneMatcher.group(1).charAt(1))]["abcdefgh".indexOf(moveOneMatcher.group(1).charAt(0))];
				if(piece != null){
					board["87654321".indexOf(moveOneMatcher.group(1).charAt(1))]["abcdefgh".indexOf(moveOneMatcher.group(1).charAt(0))] = null;
					board["87654321".indexOf(moveOneMatcher.group(2).charAt(1))]["abcdefgh".indexOf(moveOneMatcher.group(2).charAt(0))] = piece;
				}
			} else {
				System.err.println("Invalid Input: {"+l+"}");
			}

			//other code will work fine
			if(verbal){
				System.out.println("     A   B   C   D   E   F   G   H");
				System.out.println("   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗");
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
					System.out.println(boardline);
					System.out.println((i == board.length-1)?"   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝":"   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣");
				}
			}
		});
		if(!verbal){
			System.out.println("\n");
			System.out.println("     A   B   C   D   E   F   G   H");
			System.out.println("   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗");
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
				System.out.println(boardline);
				System.out.println((i == board.length-1)?"   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝":"   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣");
			}
		}
	}

}
