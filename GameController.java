import javax.swing.JOptionPane;
import java.util.Scanner;

public class GameController {
	public Masterboard mboard = new Masterboard();
	public int boardNumber = 9;
	
	private void initialize()
	{
		mboard.initialize();
	}
	
	private void printGame()
	{
		System.out.println("\n\n\n\n");
		mboard.printBoard();
	}
	
	private void firstTurn()
	{
		int firstNumber = Integer.parseInt(JOptionPane.showInputDialog("Pick the number of the board you want to play inside.\n"));
		int location = Integer.parseInt(JOptionPane.showInputDialog("Pick the number of the spot you want to play inside that board.\n"));
		mboard.sboards[firstNumber - 1].setContent('X', location - 1);
		boardNumber = location - 1;
	}
	
	private void turn(char letter, int location)
	{
		mboard.sboards[boardNumber].setContent(letter, location - 1);
		boardNumber = location - 1;
	}
	
	private void playerTurn(int Player)
	{
		int location;
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
	
	public void run()
	{
		initialize();
		printGame();
		int Player = 1;
		firstTurn();
		printGame();
		Player++;
		while(true)
		{
			playerTurn(Player);
			printGame();
			Player--;
			playerTurn(Player);
			printGame();
			Player++;	
		}
	}
	
	public static void main(String[] args)
	{
		GameController game = new GameController();
		game.run();
	}
}
