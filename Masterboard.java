import javax.swing.JOptionPane;

public class Masterboard {
	public SmallBoard[] sboards = new SmallBoard[9];
	private String topRow = "";
	private String midRow = "";
	private String botRow = "";
	private String board = "";
	
	public void initialize()
	{
		for (int i = 0; i < 9; i++)
		{
			sboards[i] = new SmallBoard();
			sboards[i].initialize();
		}
	}
	
	private String getTopRow()
	{
		topRow = sboards[0].getTopRow() + "| | |" + sboards[1].getTopRow() + "| | |" + sboards[2].getTopRow();
		topRow = topRow + "\n-----| | |-----| | |-----\n";
		topRow = topRow + sboards[0].getMidRow() + "| | |" + sboards[1].getMidRow() + "| | |" + sboards[2].getMidRow();
		topRow = topRow + "\n-----| | |-----| | |-----\n";
		topRow = topRow + sboards[0].getBotRow() + "| | |" + sboards[1].getBotRow() + "| | |" + sboards[2].getBotRow();
		return topRow;
	}
	
	private String getMidRow()
	{
		midRow = sboards[3].getTopRow() + "| | |" + sboards[4].getTopRow() + "| | |" + sboards[5].getTopRow();
		midRow = midRow + "\n-----| | |-----| | |-----\n";
		midRow = midRow + sboards[3].getMidRow() + "| | |" + sboards[4].getMidRow() + "| | |" + sboards[5].getMidRow();
		midRow = midRow + "\n-----| | |-----| | |-----\n";
		midRow = midRow + sboards[3].getBotRow() + "| | |" + sboards[4].getBotRow() + "| | |" + sboards[5].getBotRow();
		return midRow;
	}
	
	private String getBotRow()
	{
		botRow = sboards[6].getTopRow() + "| | |" + sboards[7].getTopRow() + "| | |" + sboards[8].getTopRow();
		botRow = botRow + "\n-----| | |-----| | |-----\n";
		botRow = botRow + sboards[6].getMidRow() + "| | |" + sboards[7].getMidRow() + "| | |" + sboards[8].getMidRow();
		botRow = botRow + "\n-----| | |-----| | |-----\n";
		botRow = botRow + sboards[6].getBotRow() + "| | |" + sboards[7].getBotRow() + "| | |" + sboards[8].getBotRow();
		return botRow;
	}
	
	public void printBoard()
	{
		String border = "\n-------------------------\n";
		board = getTopRow() + border + getMidRow() + border + getBotRow();
		//JOptionPane.showMessageDialog(null, board);
		System.out.println(board);
	}
}
