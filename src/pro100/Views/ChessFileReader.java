package pro100.Views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import pro100.Controllers.GameController;

public class ChessFileReader {
	private GameController manager;
	private File file;
	private boolean verbal = true;
	public static void main(String[] args) {
		String filename = "";
		if(args.length == 0){
			System.err.println("No Filename provided... would you like to manually input the values? (y/n)");
			Scanner scanLee = new Scanner(System.in);
			if(scanLee.nextLine().equalsIgnoreCase("y")){
				boolean run = true;
				ChessFileReader fileReader = new ChessFileReader();
				System.out.println("\nPlease input the values now.");
				while(run){
					ArrayList<String> inputs = new ArrayList<String>();
					String line = scanLee.nextLine();
					line = line.toLowerCase();
					if(line.length() != 0){
						inputs.add(line);
						fileReader.processLines(inputs.iterator());
					} else {
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
		manager = new GameController();
	}

	public ChessFileReader(String chessFileName) {
		file = new File(chessFileName);
		manager = new GameController();
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
	
	public void processLines(Iterator<String> lines){
		lines.forEachRemaining(l -> {
			boolean running = true;
			if(running){
				running = manager.parseLine(l);
				//other code will work fine
				if(verbal){
					System.out.println(manager.getBoard().toString());
				}
			}
		});
		if(!verbal){
			System.out.println("\n");
			System.out.println("     A   B   C   D   E   F   G   H");
			System.out.println("   ╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗");
			for(int i = 0; i < manager.getBoard().toArray().length; i++){
				String boardline = " " + ("87654321".substring(i, i+1))+" ║";
				for(int j = 0; j < manager.getBoard().toArray()[i].length; j++){
					if(manager.getBoard().toArray()[i][j] == null){
						boardline += "   ";
					} else {
						boardline += " " + manager.getBoard().toArray()[i][j].getToken() + " ";
					}
					boardline += "║";
				}
				System.out.println(boardline);
				System.out.println((i == manager.getBoard().toArray().length-1)?"   ╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝":"   ╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣");
			}
		}
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

}
