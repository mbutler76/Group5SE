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