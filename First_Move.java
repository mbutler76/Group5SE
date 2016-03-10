
public class First_Move {
	private int player1Choice;
	private int player2Choice;
	
	//setters
	public void setPlayer1Choice(int Choice)
	{
		player1Choice = Choice;
	}	
	public void setPlayer2Choice(int Choice)
	{
		player2Choice = Choice;
	}
	//getter
	public int getChoice(int player)
	{
		if (player == 1)
			return player1Choice;
		else 
			return player2Choice;
	}
	//method to decide winner after choices have been input
	public int Winner(int player1Choice, int player2Choice)
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
}
