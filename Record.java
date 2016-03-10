public class Record 
{
	private int wins;
	private int losses;
	private int draws;
	
	public String displayRecord()
	{
		String rec = "Wins: " + wins +"\nLosses: " + losses + "\nDraws: " + draws;
		
		return rec;
	}
}
