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
	
	//public Level level; //Current level
	/*Display Level*/
    /*public int displayLevel(){
		return level.currentLevel;
	}*/
	
	/*Display XP needed to level*/
	/*public int displayXPNeeded(){
		return level.xpNeeded;
	}*/
	
	/*Display Current XP*/
	/*public int displayCurrentXP(){
		return level.currentXP;
	}*/
	
	/*Matchmaking method*/
	/*public void findGame(){
		
		numCurrentGames++;
	}*/
	
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
	
	
}
