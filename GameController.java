import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class GameController {
	private Masterboard mboard = new Masterboard();
	private int boardNumber;
	private int Player;
	private First_Move firstMove;
	
	private void initialize()
	{
		mboard.initialize();
	}
	
	private void printGame()
	{
		System.out.println("\n\n\n\n");
		//mboard.printBoard();
	}
	
	private void choiceTurn()
	{
		int firstNumber = Integer.parseInt(JOptionPane.showInputDialog("Player " + Player + ": Pick the number of the board you want to play inside.\n"));
		while(mboard.sboards[firstNumber - 1].checkWin())
		{
			firstNumber = Integer.parseInt(JOptionPane.showInputDialog("Player " + Player + ": Pick the number of the board you want to play inside.\n"));
		}
		int location = Integer.parseInt(JOptionPane.showInputDialog("Player " + Player + ": Pick the number of the spot you want to play inside that board.\n"));
		if (Player == 1)
		{
			boardNumber = firstNumber - 1;
			turn('X', location);
		}
		else if (Player == 2)
		{
			boardNumber = firstNumber - 1;
			turn('O', location);
		}
	}
	
	
	private void turn(char letter, int location)
	{
		mboard.sboards[boardNumber].setContent(letter, location - 1);
		boardNumber = location - 1;
		if (Player == 1)
			Player++;
		else if (Player == 2)
			Player--;
	}
	
	private void playerTurn()
	{
		int location;
		if (mboard.sboards[boardNumber].checkWin())
		{
			choiceTurn();
			printGame();
		}
		if (Player == 1)
		{
			location = Integer.parseInt(JOptionPane.showInputDialog("Player 1: Pick the number of the spot you want to play inside board number " + (boardNumber + 1) + ".\n"));
			turn('X', location);
		}
		else if (Player == 2)
		{
			location = Integer.parseInt(JOptionPane.showInputDialog("Player 2: Pick the number of the spot you want to play inside board number " + (boardNumber + 1) + ".\n"));
			turn('O', location);
		}
	}
	
	public int firstMove(int player1Choice, int player2Choice)
	{
		if (player1Choice == 2)
		{
			if (player2Choice == 2) {return 0;}
			else if (player2Choice == 0) {return 2;}
			else {return 1;}			
		}
		else if (player2Choice == 2)
		{
			if (player1Choice == 2) {return 0;}
			else if (player1Choice == 0) {return 1;}
			else {return 2;}			
		}
		else if (player1Choice > player2Choice)
		{
			return 1;
		}
		else if (player2Choice > player1Choice)
		{
			return 2;
		}
		else {return 0;}
	}
	
	public void run()
	{
		initialize();
		printGame();
		int player1Choice = Integer.parseInt(JOptionPane.showInputDialog("Player 1: Pick 0, 1 or 2 (rock, paper, scisors)"));
		int player2Choice = Integer.parseInt(JOptionPane.showInputDialog("Player 2: Pick 0, 1 or 2 (rock, paper, scisors)"));
		Player = firstMove(player1Choice, player2Choice);
		while(Player != 1 && Player != 2)
		{
			player1Choice = Integer.parseInt(JOptionPane.showInputDialog("Player 1: Pick 0, 1 or 2 (rock, paper, scisors)"));
			player2Choice = Integer.parseInt(JOptionPane.showInputDialog("Player 2: Pick 0, 1 or 2 (rock, paper, scisors)"));
			Player = firstMove(player1Choice, player2Choice);
		}
		choiceTurn();
		printGame();
		while(!mboard.checkWin())
		{
			playerTurn();
			printGame();
		}
	}
	
	public static void main(String[] args)
	{
		View view = new View();
		GameController game = new GameController();
		game.run();
	}
}
