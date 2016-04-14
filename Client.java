import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
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

    private Square[] board = new Square[81];
    private Square currentSquare;

    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    
    public Client(String serverAddress) throws Exception
    {

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
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        
        class newGameAction implements ActionListener {
        	public void actionPerformed (ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "New Game!");
        	}
        } 
        newGame.addActionListener(new newGameAction());
        
        class tutorialAction implements ActionListener {
        	public void actionPerformed (ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "This is how you play!");
        	}
        }       
        tutorial.addActionListener(new tutorialAction());
        
        class exitAction implements ActionListener {
        	public void actionPerformed (ActionEvent e) {
        		System.exit(0);
        	}
        }       
        exit.addActionListener(new exitAction());

        
        //Login
        int reply = JOptionPane.showConfirmDialog(null, "Do you have an account?", "Account?", JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.YES_OPTION)
        {
        	JLabel jUserName = new JLabel("User Name");
            JTextField userName = new JTextField();
            JLabel jPassword = new JLabel("Password");
            JTextField password = new JPasswordField();
            Object[] ob = {jUserName, userName, jPassword, password};
            int result = JOptionPane.showConfirmDialog(null, ob, "Login", JOptionPane.OK_CANCEL_OPTION);
     
            if (result == JOptionPane.OK_OPTION) {
                String userNameValue = userName.getText();
                out.println(userNameValue);
                String passwordValue = password.getText();
                out.println(passwordValue);
            }
        }
        else
        {
        	String name = null, password = null, password2 = null;
        	boolean match = false;
        	while (!match)
        	{
        	JLabel jUserName = new JLabel("User Name");
            JTextField userName = new JTextField();
            JLabel jPassword = new JLabel("Password");
            JTextField pw = new JPasswordField();
            JLabel jPassword2 = new JLabel("Please confirm password");
            JTextField pw2 = new JPasswordField();
            Object[] ob = {jUserName, userName, jPassword, pw, jPassword2, pw2};
            int result = JOptionPane.showConfirmDialog(null, ob, "Create Account", JOptionPane.OK_CANCEL_OPTION);
        		
            	if (result == JOptionPane.OK_OPTION) 
        		{
        			name = userName.getText();
        			password = pw.getText();
        			password2 = pw2.getText();
        		}
        		
        		if (password.equals(password2))
        			match = true;
        		else
        			JOptionPane.showMessageDialog(null, "Passwords did not match, please try again");
        	}
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
    		board[i].setBackground(Color.white);
    	}
    	 for(int i = 3; i < 6; i++)
         {
             board[i].setBackground(Color.gray);
             board[i+9].setBackground(Color.gray);
             board[i+18].setBackground(Color.gray);
         }
         for(int i = 27; i < 30; i++)
         {
             board[i].setBackground(Color.gray);
             board[i+9].setBackground(Color.gray);
             board[i+18].setBackground(Color.gray);
         }
         for(int i = 33; i < 36; i++)
         {
             board[i].setBackground(Color.gray);
             board[i+9].setBackground(Color.gray);
             board[i+18].setBackground(Color.gray);
         }
          for(int i = 57; i < 60; i++)
          {
             board[i].setBackground(Color.gray);
             board[i+9].setBackground(Color.gray);
             board[i+18].setBackground(Color.gray);
         }
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
                if (response.startsWith("VALID_MOVE"))
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
                    break;
                }
                else if (response.startsWith("DEFEAT"))
                {
                    messageLabel.setText("You lose");
                    break;
                }
                else if (response.startsWith("TIE"))
                {
                    messageLabel.setText("You tied");
                    break;
                }
                else if (response.startsWith("MESSAGE"))
                {
                    messageLabel.setText(response.substring(8));
                }
            }
            out.println("QUIT");
        }
        finally
        {
            socket.close();
        }
    }

    private boolean wantsToPlayAgain()
    {
        int response = JOptionPane.showConfirmDialog(frame, "Want to play again?", "Tic Tac Toe is Fun Fun Fun", JOptionPane.YES_NO_OPTION);
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
        while (true)
        {
            String serverAddress = (args.length == 0) ? "localhost" : args[0];
            Client client = new Client(serverAddress);
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.frame.setSize(600, 400);
            client.frame.setVisible(true);
            client.frame.setResizable(false);
            client.play();
            if (!client.wantsToPlayAgain())
            {
                break;
            }
        }
    }
}