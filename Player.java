import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player 
{
	private char mark;
	private Player opponent;
	//Do we want to use this guy's method of using input/output streams for communicating with the client?
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public Player(Socket s, char m)
	{
		this.socket = s;
		this.mark = m;
		try {
            input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + mark);
            output.println("MESSAGE Waiting for opponent to connect");
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
	}
	
	public void setOpponent(Player o)
	{
		this.opponent = o;
	}
	
	public void otherPlayerMoved(int location) {
        output.println("OPPONENT_MOVED " + location);
        output.println(
            hasWinner() ? "DEFEAT" : boardFilledUp() ? "TIE" : "");
    }

    /**
     * The run method of this thread.
     */
    public void run() {
        try {
            // The thread is only started after everyone connects.
            output.println("MESSAGE All players connected");

            // Tell the first player that it is her turn.
            if (mark == 'X') {
                output.println("MESSAGE Your move");
            }

            // Repeatedly get commands from the client and process them.
            while (true) {
                String command = input.readLine();
                if (command.startsWith("MOVE")) {
                    int location = Integer.parseInt(command.substring(5));
                    if (legalMove(location, this)) {
                        output.println("VALID_MOVE");
                        output.println(hasWinner() ? "VICTORY"
                                     : boardFilledUp() ? "TIE"
                                     : "");
                    } else {
                        output.println("MESSAGE ?");
                    }
                } else if (command.startsWith("QUIT")) {
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally {
            try {socket.close();} catch (IOException e) {}
        }
    }
}
