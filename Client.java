import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Client
{

    private JFrame frame = new JFrame("Tic Tac Toe");
    private JMenuBar menuBar = new JMenuBar();
    private JLabel messageLabel = new JLabel("");
    private ImageIcon icon;
    private ImageIcon opponentIcon;
    private boolean ticTacToeplaying = true;

    private Square[] board = new Square[81];
    private Square currentSquare;

    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    
    public Client(String serverAddress) throws Exception
    {
        //attributes
    /*    int id;
        String email;
        String pass;
        String username;
        int exp;
        int level;
        String record; */
        //avatar
        boolean existing = false;

        // Setup networking
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        frame.setJMenuBar(menuBar);
        JMenu file = new JMenu ("File");
        menuBar.add(file);
        JMenuItem newGame = new JMenuItem("New Game");
        file.add(newGame);
        JMenuItem tutorial = new JMenuItem("Tutorial");
        file.add(tutorial);
        JMenuItem stats = new JMenuItem("Statistics");
        file.add(stats);
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        
        class newGameAction implements ActionListener {
        	public void actionPerformed (ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "New Game!");
        	}
        } 
        newGame.addActionListener(new newGameAction());
        
        //Tutorial
        class tutorialAction implements ActionListener {
        	public void actionPerformed (ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Welcome to Ultimate Tic Tac Toe!");
        		JOptionPane.showMessageDialog(null, "You have probably never played Tic Tac Toe quite like this. You will automatically be connected to another player.\nWhen the game starts you will play a short game of Rock Paper Scissors to decide who goes first. The player who wins Rock Paper Scissors gets to play first.\nThere are nine boards that are all connected in this version of Tic Tac Toe. The goal is to win 3 small boards in a row.\nThe first player gets to play in any of the 81 squares.");

        		JFrame TutorialFrame = new JFrame("Tutorial");
                TutorialFrame.setResizable(false);
                TutorialFrame.setSize(600, 400);
        		ImageIcon Pic1 = new ImageIcon("TutorialPic1.png");
                ImageIcon Pic2 = new ImageIcon("TutorialPic2.png");
                ImageIcon Pic3 = new ImageIcon("TutorialPic3.png");
                Square Picture = new Square();
                
                JPanel tutorialPanel = new JPanel();
                tutorialPanel.setBackground(Color.black);
                tutorialPanel.setLayout(new GridLayout(1, 1, 600, 400));
                Picture.setIcon(Pic1);
                tutorialPanel.add(Picture);
                    
                TutorialFrame.getContentPane().add(tutorialPanel, "Center");
                TutorialFrame.setVisible(true);
                
                JOptionPane.showMessageDialog(null, "This image shows the first move having been made. The square is yellow indicating that this is the most recent move made by the opponent.\nWhichever square that the move was made inside one of the small boards is the location of the next small board that must be played in.\nIn this case the next move will have to be played inside the first board in the middle row.\n");
                Picture.setIcon(Pic2);
                JOptionPane.showMessageDialog(null, "This image shows a few different things. The first being the Player X has won the middle board. This leaves that board unplayable now.\nNotice however, that the most recent move has been made in the center square of a small board. \nNormally, this would mean that the next move must be placed somewhere inside of the middle board.\nSince that board is won though, the next move can be played in any location that has not been played in yet.");
                Picture.setIcon(Pic3);
                JOptionPane.showMessageDialog(null, "This image shows simply how to win the game. Player X has won three small boards in a row and thus wins the overall game.\nPlease have fun and enjoy the game!");
                TutorialFrame.dispose();
        	}
        }       
        tutorial.addActionListener(new tutorialAction());
        
        class statsAction implements ActionListener {
        	public void actionPerformed (ActionEvent e) {
        		out.println("STATS");
//        		try {
//        			String response = in.readLine();
////					if (response.startsWith("STATS"))
////					{
////						String wins = response.substring(6, 8);
////						String losses = response.substring(9, 11);
////						String ties = response.substring(12, 14);
////						String exp = response.substring(15);
////						JOptionPane.showMessageDialog(null, "WINS: " + wins + "\n" + 
////															"LOSSES: " + losses + "\n" +
////															"DRAWS: "  + ties + "\n" +
////															"EXP: " + exp);
////					}
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
        		//JOptionPane.showMessageDialog(null, "Stats");
        	}
        } 
        stats.addActionListener(new statsAction());
        
        class exitAction implements ActionListener {
        	public void actionPerformed (ActionEvent e) {
        		System.exit(0);
        	}
        }       
        exit.addActionListener(new exitAction());
  
        //Login
        String name = null, password = null, password2 = null, email = null;
        boolean match = false;
        int reply = JOptionPane.showConfirmDialog(null, "Do you have an account?", "Account?", JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.YES_OPTION)
        {
            existing = true;
            out.println(existing); 
            
            while(true){
            	JLabel jEmail = new JLabel("E-mail");
                JTextField emailAddr = new JTextField();
                JLabel jPassword = new JLabel("Password");
                JTextField pw = new JPasswordField();
                Object[] ob = {jEmail, emailAddr, jPassword, pw};
                int result = JOptionPane.showConfirmDialog(null, ob, "Login", JOptionPane.OK_CANCEL_OPTION);
         
                if (result == JOptionPane.OK_OPTION) {
                    email = emailAddr.getText();
                    password = pw.getText();
                    out.println(email);
                    out.println(password);
                }

                String check = in.readLine();
                System.out.println(check);
                if (check.equals("match")){
                    System.out.println("visib");
                    break;
                }
                else
                    JOptionPane.showMessageDialog(null, "Passwords did not match, please try again");
            }          
            out.flush();
        }
        else
        {
            out.println(existing);
        	while (!match)
        	{
            JLabel jEmail = new JLabel("E-mail");
            JTextField emailAddr = new JTextField();
        	JLabel jUserName = new JLabel("User Name");
            JTextField userName = new JTextField();
            JLabel jPassword = new JLabel("Password");
            JTextField pw = new JPasswordField();
            JLabel jPassword2 = new JLabel("Please confirm password");
            JTextField pw2 = new JPasswordField();
            Object[] ob = {jEmail, emailAddr, jUserName, userName, jPassword, pw, jPassword2, pw2};
            int result = JOptionPane.showConfirmDialog(null, ob, "Create Account", JOptionPane.OK_CANCEL_OPTION);
        		
            	if (result == JOptionPane.OK_OPTION) 
        		{
        			email = emailAddr.getText();
                    name = userName.getText();
        			password = pw.getText();
        			password2 = pw2.getText();
        		}
        		
        		if (password.equals(password2))
        			match = true;
        		else
        			JOptionPane.showMessageDialog(null, "Passwords did not match, please try again");
        	}
            out.println(email);
        	out.println(name);
        	out.println(password);
        	out.flush();
        }

        // Layout GUI
        messageLabel.setBackground(Color.gray);
        frame.getContentPane().add(messageLabel, "South");

        JPanel smallBoardPanel = new JPanel();
        smallBoardPanel.setBackground(Color.black);
        smallBoardPanel.setLayout(new GridLayout(9, 9, 5, 5));
        for (int i = 0; i < 81; i++) {
            final int j = i;
            board[i] = new Square();
            board[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    currentSquare = board[j];
                    out.println("MOVE " + j);}});
            smallBoardPanel.add(board[i]);
        }
        
        //Color the boards
        colorBoards();
        
        frame.getContentPane().add(smallBoardPanel, "Center");
    }
    
    public void colorBoards()
    {
    	for(int i = 0; i < 81; i ++)
    	{
    		if(board[i].getBackground() != Color.blue && board[i].getBackground() != Color.red)
    		{board[i].setBackground(Color.white);}
    	}
    	 for(int i = 3; i < 6; i++)
         {
             if(board[i].getBackground() != Color.blue && board[i].getBackground() != Color.red)
             {board[i].setBackground(Color.gray);
             board[i+9].setBackground(Color.gray);
             board[i+18].setBackground(Color.gray);}
         }
         for(int i = 27; i < 30; i++)
         {
             if(board[i].getBackground() != Color.blue && board[i].getBackground() != Color.red)
             {board[i].setBackground(Color.gray);
             board[i+9].setBackground(Color.gray);
             board[i+18].setBackground(Color.gray);}
         }
         for(int i = 33; i < 36; i++)
         {
             if(board[i].getBackground() != Color.blue && board[i].getBackground() != Color.red)
             {board[i].setBackground(Color.gray);
             board[i+9].setBackground(Color.gray);
             board[i+18].setBackground(Color.gray);}
         }
          for(int i = 57; i < 60; i++)
          {
             if(board[i].getBackground() != Color.blue && board[i].getBackground() != Color.red)
             {board[i].setBackground(Color.gray);
             board[i+9].setBackground(Color.gray);
             board[i+18].setBackground(Color.gray);}
          }
    }
    
    public void rockPaperScissors()
    {
        JFrame ticTacToeFrame = new JFrame("Rock Paper Scisors");
        ticTacToeFrame.setResizable(false);
        ticTacToeFrame.setSize(200, 120);
        ticTacToeFrame.setAlwaysOnTop(true);
        ImageIcon rock = new ImageIcon("rock.png");
        ImageIcon paper = new ImageIcon("paper.png");
        ImageIcon scissors = new ImageIcon("scissors.png");
        Square[] choices = new Square[3];
        
        
        JPanel choicePanel = new JPanel();
            choicePanel.setBackground(Color.black);
            choicePanel.setLayout(new GridLayout(1, 3, 10, 10));
            for (int i = 0; i < 3; i++)
            {
                final int j = i;
                choices[i] = new Square();
                if (i == 0)
                    choices[i].setIcon(rock);
                else if (i == 1)
                    choices[i].setIcon(paper);
                else
                    choices[i].setIcon(scissors);
                choices[i].addMouseListener(new MouseAdapter()
                {
                    public void mousePressed(MouseEvent e)
                    {
                        currentSquare = choices[j];
                        out.println("CHOICE " + j);
                        ticTacToeFrame.dispose();
                    }
                });
                choicePanel.add(choices[i]);
            }
            
        ticTacToeFrame.getContentPane().add(choicePanel, "Center");
        ticTacToeFrame.setVisible(true);
    }
    
    
    public void play() throws Exception
    {
        String response;
        try
        {
            response = in.readLine();
            if (response.startsWith("WELCOME"))
            {
                char mark = response.charAt(8);
                icon = new ImageIcon(mark == 'X' ? "xSmall.png" : "oSmall.png");
                opponentIcon  = new ImageIcon(mark == 'X' ? "oSmall.png" : "xSmall.png");
                frame.setTitle("Tic Tac Toe - Player " + mark);
            }
            while (true)
            {
                response = in.readLine();
               	System.out.println(response);
                if (response.startsWith("CHOICE"))
                {
                    messageLabel.setText("rock paper scissors was a tie please play again");
                    rockPaperScissors();
                }
                else if (response.startsWith("VALID_MOVE"))
                {
                    messageLabel.setText("Valid move, please wait");
                    currentSquare.setIcon(icon);
                    currentSquare.repaint();
                }
                else if (response.startsWith("OPPONENT_MOVED"))
                {
                    int loc = Integer.parseInt(response.substring(15));
                    board[loc].setIcon(opponentIcon);
                    board[loc].repaint();
                    colorBoards();
                    board[loc].setBackground(Color.yellow);
                    messageLabel.setText("Opponent moved, your turn");
                }
                else if (response.startsWith("VICTORY"))
                {
                    messageLabel.setText("You win");
                    //add SQL update for records
                    break;
                }
                else if (response.startsWith("DEFEAT"))
                {
                    messageLabel.setText("You lose");
                    //add SQL update for record
                    break;
                }
                else if (response.startsWith("TIE"))
                {
                    messageLabel.setText("You tied");
                    //add SQL update for records
                    break;
                }
                else if (response.startsWith("MESSAGE"))
                {
                    messageLabel.setText(response.substring(8));
                }
                else if (response.startsWith("STATS"))
				{
					String stats = response.substring(6);
					String statsArray[] = stats.split("/");
					int level = Integer.parseInt(statsArray[3])/100;
					JOptionPane.showMessageDialog(null, "Wins: " + statsArray[0] + "\n" +  
														"Loses: " + statsArray[1] + "\n" +
														"Draws: " + statsArray[2] + "\n" +
														"Experience: " + statsArray[3] + "\n" +
														"Level: " + level);
				}
               else if (response.startsWith("SBOARD_WON"))
               {
               	int boardNumber = Integer.parseInt(response.substring(11, 12));
               	String mark = response.substring(12);
               	System.out.println("boardNum = " + response.substring(11, 12));
               	System.out.println("mark = " + mark);
               	Color color;
               	if ( mark.equals("X"))
               	{
               		color = Color.red;
               	}
               	else
               	{
               		color = Color.blue;
               	}
                   colorSboard(boardNumber, color);
               }
            }
            out.println("QUIT");
        }
        finally
        {
            socket.close();
        }
    }
    
    public void colorSboard(int boardNumber, Color color)
    {
        if (boardNumber == 0)
               	{
		               	 for(int i = 0; i < 3; i++)
		                 {
		                     board[i].setBackground(color);
		                     board[i+9].setBackground(color);
		                     board[i+18].setBackground(color);
		                 }
               	}
               	else if (boardNumber == 1)
               	{
	                	 for(int i = 3; i < 6; i++)
	                     {
	                         board[i].setBackground(color);
	                         board[i+9].setBackground(color);
	                         board[i+18].setBackground(color);
	                     }
               	}
               	else if (boardNumber == 2)
               	{
	                	 for(int i = 6; i < 9; i++)
	                     {
	                         board[i].setBackground(color);
	                         board[i+9].setBackground(color);
	                         board[i+18].setBackground(color);
	                     }
               	}
               	else if (boardNumber == 3)
               	{
	                     for(int i = 27; i < 30; i++)
	                     {
	                         board[i].setBackground(color);
	                         board[i+9].setBackground(color);
	                         board[i+18].setBackground(color);
	                     }
               	}
               	else if (boardNumber == 4)
               	{
	                	 for(int i = 30; i < 33; i++)
	                     {
	                         board[i].setBackground(color);
	                         board[i+9].setBackground(color);
	                         board[i+18].setBackground(color);
	                     }
               	}
               	else if (boardNumber == 5)
               	{
	                     for(int i = 33; i < 36; i++)
	                     {
	                         board[i].setBackground(color);
	                         board[i+9].setBackground(color);
	                         board[i+18].setBackground(color);
	                     }
               	}
               	else if (boardNumber == 6)
               	{
	                	 for(int i = 54; i < 57; i++)
	                     {
	                         board[i].setBackground(color);
	                         board[i+9].setBackground(color);
	                         board[i+18].setBackground(color);
	                     }
               	}
               	else if (boardNumber == 7)
               	{
	                      for(int i = 57; i < 60; i++)
	                      {
	                         board[i].setBackground(color);
	                         board[i+9].setBackground(color);
	                         board[i+18].setBackground(color);
	                     }
               	}
               	else if (boardNumber == 8)
               	{
	                 	 for(int i = 60; i < 63; i++)
	                     {
	                         board[i].setBackground(color);
	                         board[i+9].setBackground(color);
	                         board[i+18].setBackground(color);
	                     }
               	}
    }

    private boolean wantsToPlayAgain()
    {
        int response = JOptionPane.showConfirmDialog(frame, "Want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
        frame.dispose();
        return response == JOptionPane.YES_OPTION;
    }

    
    static class Square extends JPanel
    {
        JLabel label = new JLabel((Icon)null);

        public Square()
        {
            setBackground(Color.white);
            add(label);
        }

        public void setIcon(Icon icon)
        {
            label.setIcon(icon);
        }
    }

    
    public static void main(String[] args) throws Exception
    {
        int num = 0;
        while (true)
        {
           // SplashDemo test = new SplashDemo();
            SplashScreen splash = new SplashScreen(5000);
            splash.showSplash();
            String serverAddress = "70.178.92.117";
            Client client = new Client(serverAddress);
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.frame.setSize(600, 400);
            client.frame.setVisible(true);
            client.frame.setResizable(false);
            client.rockPaperScissors();
            client.play();
            if (!client.wantsToPlayAgain())
            {
                break;
            }
        }
    }
}


