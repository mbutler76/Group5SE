import javax.swing.JOptionPane;

public class SmallBoard {
	private char[] boardContents = new char[9];
	private String topRow = "";
	private String midRow = "";
	private String botRow = "";
	private String board = "";
	
	public void initialize()
	{
		for (int i = 0; i < 9; i++)
		{
			boardContents[i] = 'Z';
		}
	}
	
	public boolean checkEmpty(int location)
	{
		boolean isEmpty = false;
		if (boardContents[location] == 'Z')
			isEmpty = true;
		return isEmpty;
	}
	
	public boolean checkWin()
	{
		boolean didWin = false;
		if (checkRow() || checkColumn() || checkDiag())
			didWin = true;
		return didWin;
			
	}
	
	public boolean checkRow()
	{
		boolean didWin = false;
		if(boardContents[0] != 'Z')
		{
			if (boardContents[0] == boardContents[1] && boardContents[1] == boardContents[2])
				didWin = true;
		}
		
		if(boardContents[3] != 'Z')
		{
			if (boardContents[3] == boardContents[4] && boardContents[4] == boardContents[5])
				didWin = true;
		}
		
		if(boardContents[6] != 'Z')
		{
			if (boardContents[6] == boardContents[7] && boardContents[7] == boardContents[8])
				didWin = true;
		}
		return didWin;
	}
	
	public boolean checkColumn()
	{
		boolean didWin = false;
		if(boardContents[0] != 'Z')
		{
			if (boardContents[0] == boardContents[3] && boardContents[3] == boardContents[6])
				didWin = true;
		}
		
		if(boardContents[1] != 'Z')
		{
			if (boardContents[1] == boardContents[4] && boardContents[4] == boardContents[7])
				didWin = true;
		}
		
		if(boardContents[2] != 'Z')
		{
			if (boardContents[2] == boardContents[5] && boardContents[5] == boardContents[8])
				didWin = true;
		}
		return didWin;
	}
	
	public boolean checkDiag()
	{
		boolean didWin = false;
		if(boardContents[0] != 'Z')
		{
			if (boardContents[0] == boardContents[4] && boardContents[4] == boardContents[8])
				didWin = true;
		}
		
		if(boardContents[2] != 'Z')
		{
			if (boardContents[2] == boardContents[4] && boardContents[4] == boardContents[6])
				didWin = true;
		}
		return didWin;
	}
	
	public void setContent(char letter, int location)
	{
		boardContents[location] = letter;
	}
	
	public char getContent(int location)
	{
		return boardContents[location];
	}
	
	public String getTopRow()
	{
		topRow = boardContents[0] + "|" + boardContents[1] + "|" + boardContents[2];
		return topRow;
	}
	
	public String getMidRow()
	{
		midRow = boardContents[3] + "|" + boardContents[4] + "|" + boardContents[5];
		return midRow;
	}
	
	public String getBotRow()
	{
		botRow = boardContents[6] + "|" + boardContents[7] + "|" + boardContents[8];
		return botRow;
	}
}