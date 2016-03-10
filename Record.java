public class Record 
{
	private int wins = 0;
	private int losses = 0;
	private int draws = 0;
	
	public void updateWins()
	{
		wins++;
	}
	
	public void updateLosses()
	{
		losses++;
	}
	
	public void updateDraws()
	{
		draws++;
	}
	
	public String displayRecord()
	{
		String rec = "Wins: " + wins +"\nLosses: " + losses + "\nDraws: " + draws;
		
		return rec;
	}
}
