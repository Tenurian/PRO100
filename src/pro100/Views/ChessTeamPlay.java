package pro100.Views;

import java.util.Scanner;

import pro100.Controllers.GameController;

public class ChessTeamPlay {

	public ChessTeamPlay() {
	}

	public void run(){
		GameController manager = new GameController();
		Scanner scanLee = new Scanner(System.in);
		boolean run = true;
		System.out.println("\nThe Game has started.\nIt is the White Player's turn.");
		while(run){
			System.out.println("\n"+manager.getBoard().toString());
			System.out.print("Please Input a move: ");
			String line = scanLee.nextLine();
			line = line.toLowerCase();
			if(line.length() == 0){
				run = false;
			} else {
				manager.parseLine(line);
			}
		}
		scanLee.close();
	}

	public static void main(String[] args) {
		new ChessTeamPlay().run();
	}

}
