import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static int PORT = 7000;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private int bigBoardLocation;
    private int smallBoardLocation;

    public Client(String serverAddress) throws Exception {

        // Setup networking
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void play() throws Exception {
        String response;
        try {
            //response = in.readLine();
            while (true) {
                response = in.readLine();
                if (response.startsWith("OPPONENT_MOVED")) {
                	bigBoardLocation = Integer.parseInt(response.substring(15, 16));
                	smallBoardLocation = Integer.parseInt(response.substring(16));
                } else if (response.startsWith("VICTORY")) {
                    //messageLabel.setText("You win");
                    break;
                } else if (response.startsWith("DEFEAT")) {
                    //messageLabel.setText("You lose");
                    break;
                } else if (response.startsWith("TIE")) {
                    //messageLabel.setText("You tied");
                    break;
                } else if (response.startsWith("MESSAGE")) {
                    //messageLabel.setText(response.substring(8));
                }
            }
            out.println("QUIT");
        }
        finally {
            socket.close();
        }
    }

    private boolean wantsToPlayAgain() {
        //int response = JOptionPane.showConfirmDialog(frame, "Want to play again?", "Tic Tac Toe is Fun Fun Fun", JOptionPane.YES_NO_OPTION);
        //frame.dispose();
        //return response == JOptionPane.YES_OPTION;
    	return false;
    }
    
    public static void main(String[] args) throws Exception {
        while (true) {
//        	View view = new View();
            String serverAddress = (args.length == 0) ? "localhost" : args[0];
            Client client = new Client(serverAddress);
//            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            client.frame.setSize(240, 160);
//            client.frame.setVisible(true);
//            client.frame.setResizable(false);
            client.play();
            if (!client.wantsToPlayAgain()) 
            {
                break;
            }
        }
    }
}