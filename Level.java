public class Level 
{
	private static final int MAX_LEVEL = 100;
	
	private int currentXP;
	private int currentLevel;
	private int xpNeeded;
	
	//generates the amount of xp needed to level up based on what level the player is currently at
	private int generateXP(int i)
	{
		return (25 * i) + 25;
	}
	
	//checks what level the player is currently at and calculates how much XP they need to level up
	public int getXpNeeded()
	{
		xpNeeded = generateXP(currentLevel) - currentXP;
		return xpNeeded;
	}
	
	//checks to see if the player meets all of the criteria to level up to the next level
	public boolean checkLevelUp()
	{
		if (currentLevel < MAX_LEVEL && currentXP > xpNeeded)
		{
			currentXP = currentXP - xpNeeded;
			currentLevel++;
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	//getter for the player's current XP
	public int getCurrentXP()
	{
		return currentXP;
	}

	//getter for the player's current level
	int getCurrentLevel()
	{
		return currentLevel;
	}
}
