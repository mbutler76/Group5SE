import java.awt.Image;

public class PlayerInfo {

	public String username; //Displayed to everyone
	private int playerID; //Used for matchmaking and adding friends
	private String email; 
	private String password;
	public Image avatar; //Image displayed for each account
	
	public int maxGames = 10; //Maximum number of games playable at once
	public int numCurrentGames; //Number of games currently being played
	public int numMovesToMake; //Number of games that have a move to be made
	private static final int MAXFRIENDS = 100; //Maximum number of friends
	private int numFriends = 0; //Current number of friends
	public int friendList[] = new int[MAXFRIENDS]; //Friendlist stored as Player ID's
	//public Record record; //Players current Record
	
	private static final int MAX_LEVEL = 100; //Maximum level that a player can obtain
	private int currentXP; //Current amount of XP that the player has
	private int currentLevel; //Current player level
	private int xpNeeded; //Amount of XP needed for the player to advance to the next level
	
	private int wins = 0; //Number of wins
	private int losses = 0; //Number of losses
	private int draws = 0; //Number of draws/ties
	
	/*Getters and Setters*/
	public int getPlayerID(){
		return playerID;
	}
	
	public void setPlayerID(int id){
		playerID = id;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String em){
		email = em;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String pw){
		password = pw;
	}
	
	/*Changes player's username*/
	public void changeUsername(String newName){
		username = newName;
	}
	
	@Override
	public String toString(){
		return username + " #" + playerID;
	}
	
	public void howManyMoves(int n){
		n = numMovesToMake;
		System.out.println("You have " + n + " move to make");
	}
	
	/*Add friend*/
	public void addFriend(int id){
		if(numFriends < MAXFRIENDS)
		{
			friendList[numFriends] = id;
			numFriends++;
		}
	}
	
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
