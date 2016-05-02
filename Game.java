import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Game
{
    private Player[] board =
        {
        null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null
        };

    public Player currentPlayer;
    private SmallBoard[] sBoards = new SmallBoard[9];
    
    public Game()
    {
        for(int i = 0; i < 9; i++)
        {
            sBoards[i] = new SmallBoard(i);
        }
    }
    
    public boolean bigHasWinner()
    {
        for(int i = 0; i < 9; i++)
        {
            sBoards[i].update();
        }
        Player s0Winner = sBoards[0].smallWinner();
        Player s1Winner = sBoards[1].smallWinner();
        Player s2Winner = sBoards[2].smallWinner();
        Player s3Winner = sBoards[3].smallWinner();
        Player s4Winner = sBoards[4].smallWinner();
        Player s5Winner = sBoards[5].smallWinner();
        Player s6Winner = sBoards[6].smallWinner();
        Player s7Winner = sBoards[7].smallWinner();
        Player s8Winner = sBoards[8].smallWinner();
        if (s0Winner != null && s0Winner == s1Winner && s0Winner == s2Winner)
            {return true;}
        else if (s0Winner != null && s0Winner == s3Winner && s0Winner == s6Winner)
            {return true;}
        else if (s0Winner != null && s0Winner == s4Winner && s0Winner == s8Winner)
            {return true;}
        else if (s1Winner != null && s1Winner == s4Winner && s1Winner == s7Winner)
            {return true;}
        else if (s2Winner != null && s2Winner == s5Winner && s2Winner == s8Winner)
            {return true;}
        else if (s2Winner != null && s2Winner == s4Winner && s2Winner == s6Winner)
            {return true;}
        else if (s3Winner != null && s3Winner == s4Winner && s3Winner == s5Winner)
            {return true;}
        else if (s6Winner != null && s6Winner == s7Winner && s6Winner == s8Winner)
            {return true;}
        else return false;
    }
    
    public boolean boardFilledUp()
    {
        for (int i = 0; i < board.length; i++)
        {
            if (board[i] == null)
            {
                return false;
            }
        }
        return true;
    }

    public synchronized boolean legalMove(int location, Player player)
    {
        if (player == currentPlayer && board[location] == null && sBoards[this.getBoardNumber(location)].getPlayable())
        {
            board[location] = currentPlayer;
            currentPlayer = currentPlayer.opponent;
            currentPlayer.otherPlayerMoved(location);
            this.changePlayable(this.getDestBoard(location));
            //System.out.println("Dest board is " + this.getDestBoard(location));
            //System.out.println("boo");
            return true;
        }
        return false;
    }
    
    public void changePlayable(int destBoard)
        {
            if (sBoards[destBoard].smallHasWinner())
            {
                for(int i = 0; i < 9; i++)
                {
                    sBoards[i].setPlayable(true);
                }
            }
            else
            {
                for(int i = 0; i < 9; i++)
                {
                    if (i != destBoard)
                        sBoards[i].setPlayable(false);
                    else
                        sBoards[i].setPlayable(true);
                }
            }
            //System.out.println("boo");       
        }
    
    public int getBoardNumber(int num)
    {
        if (num < 27)
        {
            if (num < 3 || (num < 12 && num > 8) || (num < 21 && num > 17))
                return 0;
            else if ((num > 5 && num < 9) || (num > 14 && num < 18) || num > 23)
                return 2;
            else
                return 1;
        }
        else if (num > 53)
        {
            if (num < 57 || (num > 62 && num < 66) || (num > 71 && num < 75))
                return 6;
            else if ((num > 59 && num < 63) || (num > 68 && num < 72) || num > 77)
                return 8;
            else
                return 7;
        }
        else
        {
            if (num < 30 || (num > 35 && num < 39) || (num > 44 && num < 48))
                return 3;
            else if ((num > 32 && num < 36) || (num > 41 && num < 45) || num > 50)
                return 5;
            else
                return 4;
        }
    }
    
    public int getDestBoard(int num)
    {
        if (num == 0 || num == 3 || num == 6 || num == 27 || num == 30 || num == 33 || num == 54 || num == 57 || num == 60)
            return 0;
        else if (num == 1 || num == 4 || num == 7 || num == 28 || num == 31 || num == 34 || num == 55 || num == 58 || num == 61)
            return 1;
        else if (num == 2 || num == 5 || num == 8 || num == 29 || num == 32 || num == 35 || num == 56 || num == 59 || num == 62)
            return 2;
        else if (num == 9 || num == 12 || num == 15 || num == 36 || num == 39 || num == 42 || num == 63 || num == 66 || num == 69)
            return 3;
        else if (num == 10 || num == 13 || num == 16 || num == 37 || num == 40 || num == 43 || num == 64 || num == 67 || num == 70)
            return 4;
        else if (num == 11 || num == 14 || num == 17 || num == 38 || num == 41|| num == 44 || num == 65 || num == 68 || num == 71)
            return 5;
        else if (num == 18 || num == 21 || num == 24 || num == 45 || num == 48 || num == 51 || num == 72 || num == 75 || num == 78)
            return 6;
        else if (num == 19 || num == 22 || num == 25 || num == 46 || num == 49 || num == 52 || num == 73 || num == 76 || num == 79)
            return 7;
        else
            return 8;
    }
    
    public void setCurrentPlayer(Player player)
    {
        currentPlayer = player;
    }
    
    class SmallBoard
    {
        private Player[] sBoard = 
        {
            null, null, null,
            null, null, null,
            null, null, null
        };
        private int boardNumber;
        private boolean playable = true;
        
        public void setPlayable(boolean bool)
        {
            playable = bool;
        }
        
        public boolean getPlayable()
        {
            return playable;
        }
                
        public SmallBoard(int bn)
        {
            boardNumber = bn;
        }
        
        public void setBig(Player smallWinner)
        {
            if (boardNumber < 3)
            {
                board[boardNumber * 3] = smallWinner; board[boardNumber * 3 + 1] = smallWinner; board[boardNumber * 3 + 2] = smallWinner;
                board[boardNumber * 3 + 9] = smallWinner; board[boardNumber * 3 + 10] = smallWinner; board[boardNumber * 3 + 11] = smallWinner;
                board[boardNumber * 3 + 18] = smallWinner; board[boardNumber * 3 + 19] = smallWinner; board[boardNumber * 3 + 20] = smallWinner;
            }
            else if (boardNumber == 3 || boardNumber == 6)
            {
                board[boardNumber * 9] = smallWinner; board[boardNumber * 9 + 1] = smallWinner; board[boardNumber * 9 + 2] = smallWinner;
                board[boardNumber * 9 + 9] = smallWinner; board[boardNumber * 9 + 10] = smallWinner; board[boardNumber * 9 + 11] = smallWinner;
                board[boardNumber * 9 + 18] = smallWinner; board[boardNumber * 9 + 19] = smallWinner; board[boardNumber * 9 + 20] = smallWinner;
            }
            else if (boardNumber == 4 || boardNumber == 7)
            {
                board[boardNumber * 9 - 6] = smallWinner; board[boardNumber * 9 - 5] = smallWinner; board[boardNumber * 9 - 4] = smallWinner;
                board[boardNumber * 9 + 3] = smallWinner; board[boardNumber * 9 + 4] = smallWinner; board[boardNumber * 9 + 5] = smallWinner;
                board[boardNumber * 9 + 12] = smallWinner; board[boardNumber * 9 + 13] = smallWinner; board[boardNumber * 9 + 14] = smallWinner;
            }
            else if (boardNumber == 5)
            {
                board[boardNumber * 6 + 3] = smallWinner; board[boardNumber * 6 + 4] = smallWinner; board[boardNumber * 6 + 5] = smallWinner;
                board[boardNumber * 9 - 3] = smallWinner; board[boardNumber * 9 - 2] = smallWinner; board[boardNumber * 9 - 1] = smallWinner;
                board[boardNumber * 9 + 6] = smallWinner; board[boardNumber * 9 + 7] = smallWinner; board[boardNumber * 9 + 8] = smallWinner;
            }
            else
            {
                board[boardNumber * 9 - 12] = smallWinner; board[boardNumber * 9 - 11] = smallWinner; board[boardNumber * 9 - 10] = smallWinner;
                board[boardNumber * 9 - 3] = smallWinner; board[boardNumber * 9 - 2] = smallWinner; board[boardNumber * 9 - 1] = smallWinner;
                board[boardNumber * 9 + 6] = smallWinner; board[boardNumber * 9 + 7] = smallWinner; board[boardNumber * 9 + 8] = smallWinner;
            }
        }
        
        public void update()
        {
            if (boardNumber < 3)
            {
                sBoard[0] = board[boardNumber * 3]; sBoard[1] = board[boardNumber * 3 + 1];  sBoard[2] = board[boardNumber * 3 + 2];
                sBoard[3] = board[boardNumber * 3 + 9]; sBoard[4] = board[boardNumber * 3 + 10];  sBoard[5] = board[boardNumber * 3 + 11];
                sBoard[6] = board[boardNumber * 3 + 18]; sBoard[7] = board[boardNumber * 3 + 19];  sBoard[8] = board[boardNumber * 3 + 20];
            }
            else if (boardNumber == 3 || boardNumber == 6)
            {
                sBoard[0] = board[boardNumber * 9]; sBoard[1] = board[boardNumber * 9 + 1];  sBoard[2] = board[boardNumber * 9 + 2];
                sBoard[3] = board[boardNumber * 9 + 9]; sBoard[4] = board[boardNumber * 9 + 10];  sBoard[5] = board[boardNumber * 9 + 11];
                sBoard[6] = board[boardNumber * 9 + 18]; sBoard[7] = board[boardNumber * 9 + 19];  sBoard[8] = board[boardNumber * 9 + 20];
            }
            else if (boardNumber == 4 || boardNumber == 7)
            {
                sBoard[0] = board[boardNumber * 9 - 6]; sBoard[1] = board[boardNumber * 9 - 5];  sBoard[2] = board[boardNumber * 9 - 4];
                sBoard[3] = board[boardNumber * 9 + 3]; sBoard[4] = board[boardNumber * 9 + 4];  sBoard[5] = board[boardNumber * 9 + 5];
                sBoard[6] = board[boardNumber * 9 + 12]; sBoard[7] = board[boardNumber * 9 + 13];  sBoard[8] = board[boardNumber * 9 + 14];
            }
            else if (boardNumber == 5)
            {
                sBoard[0] = board[boardNumber * 6 + 3]; sBoard[1] = board[boardNumber * 6 + 4];  sBoard[2] = board[boardNumber * 6 + 5];
                sBoard[3] = board[boardNumber * 9 - 3]; sBoard[4] = board[boardNumber * 9 - 2];  sBoard[5] = board[boardNumber * 9 - 1];
                sBoard[6] = board[boardNumber * 9 + 6]; sBoard[7] = board[boardNumber * 9 + 7];  sBoard[8] = board[boardNumber * 9 + 8];
            }
            else
            {
                sBoard[0] = board[boardNumber * 9 - 12]; sBoard[1] = board[boardNumber * 9 - 11];  sBoard[2] = board[boardNumber * 9 - 10];
                sBoard[3] = board[boardNumber * 9 - 3]; sBoard[4] = board[boardNumber * 9 - 2];  sBoard[5] = board[boardNumber * 9 - 1];
                sBoard[6] = board[boardNumber * 9 + 6]; sBoard[7] = board[boardNumber * 9 + 7];  sBoard[8] = board[boardNumber * 9 + 8];
            }
        }
        
        public Player smallWinner()
        {
            if (sBoard[0] != null && sBoard[0] == sBoard[1] && sBoard[0] == sBoard[2])
                {
                    
                    this.setBig(sBoard[0]);
                    return sBoard[0];
                }
            else if (sBoard[3] != null && sBoard[3] == sBoard[4] && sBoard[3] == sBoard[5])
                {
                    this.setBig(sBoard[3]);
                    return sBoard[3];
                }
            else if (sBoard[6] != null && sBoard[6] == sBoard[7] && sBoard[6] == sBoard[8])
                {
                    this.setBig(sBoard[6]);
                    return sBoard[6];
                }
            else if (sBoard[0] != null && sBoard[0] == sBoard[3] && sBoard[0] == sBoard[6])
                {
                    this.setBig(sBoard[0]);
                    return sBoard[0];
                }
            else if (sBoard[1] != null && sBoard[1] == sBoard[4] && sBoard[1] == sBoard[7])
                {
                    this.setBig(sBoard[1]);
                    return sBoard[1];
                }
            else if (sBoard[2] != null && sBoard[2] == sBoard[5] && sBoard[2] == sBoard[8])
                {
                    this.setBig(sBoard[2]);
                    return sBoard[2];
                }
            else if (sBoard[0] != null && sBoard[0] == sBoard[4] && sBoard[0] == sBoard[8])
                {
                    this.setBig(sBoard[0]);
                    return sBoard[0];
                }
            else if (sBoard[2] != null && sBoard[2] == sBoard[4] && sBoard[2] == sBoard[6])
                {
                    this.setBig(sBoard[2]);
                    return sBoard[2];
                }
            else return null;
        }
        
        public boolean smallHasWinner()
        {
            if (sBoard[0] != null && sBoard[0] == sBoard[1] && sBoard[0] == sBoard[2])
                {
                    return true;
                }
            else if (sBoard[3] != null && sBoard[3] == sBoard[4] && sBoard[3] == sBoard[5])
                {
                    this.setBig(sBoard[3]);
                    return true;
                }
            else if (sBoard[6] != null && sBoard[6] == sBoard[7] && sBoard[6] == sBoard[8])
                {
                    this.setBig(sBoard[6]);
                    return true;
                }
            else if (sBoard[0] != null && sBoard[0] == sBoard[3] && sBoard[0] == sBoard[6])
                {
                    return true;
                }
            else if (sBoard[1] != null && sBoard[1] == sBoard[4] && sBoard[1] == sBoard[7])
                {
                    return true;
                }
            else if (sBoard[2] != null && sBoard[2] == sBoard[5] && sBoard[2] == sBoard[8])
                {
                    return true;
                }
            else if (sBoard[0] != null && sBoard[0] == sBoard[4] && sBoard[0] == sBoard[8])
                {
                    return true;
                }
            else if (sBoard[2] != null && sBoard[2] == sBoard[4] && sBoard[2] == sBoard[6])
                {
                    return true;
                }
            else return false;
        }
    }
    
    class Player extends Thread
    {
        public char mark;
        Player opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        int choice = 3;

        String url = "jdbc:mysql://localhost:3306/javabase";
        String dbUsername = "java";
        String dbPassword = "password";

        
        public Player(Socket socket, char mark)
        {
            this.socket = socket;
            this.mark = mark;
            try
            {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                
                boolean existing = Boolean.parseBoolean(input.readLine());
                System.out.println(existing);
                String forMatchPass = "trash";
                String password = "garbage";

                //login validation
                if (existing){
                    while(true){
                        String email = input.readLine();
                        System.out.println(email); /*DELETE LATER*/
                        password = input.readLine();
                        System.out.println(password); /*DELETE LATER*/


                        System.out.println("Connecting database...");

                        try {
                            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                            Statement statement = connection.createStatement();

                            String sql = "SELECT password FROM user WHERE email = '"+email+"'";

                            ResultSet results = statement.executeQuery(sql);
                            if(results.next()){
                                forMatchPass = results.getString("password"); 
                                System.out.println("incPass= " + forMatchPass); /* DEFINITELY DELETE LATER*/
                            }     

                            System.out.println("Database connected!");
                        } catch (SQLException e) {
                            throw new IllegalStateException("Cannot connect the database!", e);
                        }
                        
                        if(forMatchPass.equals(password)){
                            output.println("match");
                            break;
                        }
                        else
                            output.println("noMatch");
                    }
                }

                else{
                    String email = input.readLine();
                    System.out.println(email); /*DELETE LATER*/
                    String username = input.readLine();
                    System.out.println(username); /*DELETE LATER*/
                    password = input.readLine();
                    System.out.println(password); /*DELETE LATER*/

                    System.out.println("Connecting database...");

                    try {
                        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                        Statement statement = connection.createStatement();

                        String sql = "insert into user "
                                    + "(email, password, username, exp, level, record, avatar)"
                                    + "values ('"+email+"', '"+password+"', '"+username+"', 0, 0, '000/000/000', 0)";

                        statement.executeUpdate(sql);

                        System.out.println("Database connected!");
                    } catch (SQLException e) {
                        throw new IllegalStateException("Cannot connect the database!", e);
                    }
                }

                output.println("WELCOME " + mark);
                output.println("MESSAGE Waiting for opponent to connect");
            } catch (IOException e)
            {
                System.out.println("Player died: " + e);
            }
        }
        
        public int ticTacToe()
        {
            if (this.choice == 3 || opponent.choice == 3)
            {
                    return 3;
            }
            if (this.choice == 2)
            {
                if (opponent.choice == 2) {return 0;}
                else if (opponent.choice == 0) {return 2;}
                else {return 1;}            
            }
            else if (opponent.choice == 2)
            {
                if (this.choice == 2) {return 0;}
                else if (this.choice == 0) {return 1;}
                else {return 2;}            
            }
            else if (this.choice > opponent.choice)
            {
                return 1;
            }
            else if (opponent.choice > this.choice)
            {
                return 2;
            }
            else {return 0;}
        }

        
        public void setOpponent(Player opponent)
        {
            this.opponent = opponent;
        }

        
        public void otherPlayerMoved(int location)
        {
            output.println("OPPONENT_MOVED " + location);
            output.println(bigHasWinner() ? "DEFEAT" : boardFilledUp() ? "TIE" : "");
            //System.out.println("boo");
        }
        
        public void run()
        {                      
            try
            {
                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");
                
                int num;
                
                // Get commands from the client and process them.
                while (true)
                {
                    String command = input.readLine();
                    System.out.println(command);
                    
                    if (command.startsWith("CHOICE"))
                    {
                        choice = Integer.parseInt(command.substring(7));
                        //System.out.println(mark + " " + choice);
                        
                        if (opponent.choice == 3)
                        {
                            output.println("MESSAGE waiting for opponent");
                        }
                        else
                        {
                            num = ticTacToe();
                            if (num == 1)
                            {
                                setCurrentPlayer(this);
                                output.println("MESSAGE Your move");
                                opponent.output.println("MESSAGE opponent's move");
                            }
                            else if (num == 2)
                            {
                                setCurrentPlayer(opponent);
                                output.println("MESSAGE opponent's move");
                                opponent.output.println("MESSAGE your move");
                            }
                            else if (num == 0)
                            {
                                //System.out.println(num);
                                choice = 3;
                                opponent.choice = 3;
                                output.println("CHOICE");
                                opponent.output.println("CHOICE");
                            }
                        }
                    }
                    
                    else if (command.startsWith("MOVE"))
                    {
                        int location = Integer.parseInt(command.substring(5));
                        //System.out.println("board number " + this.getBoardNumber(location));
                        if (legalMove(location, this))
                        {
                            output.println("VALID_MOVE");
                            output.println(bigHasWinner() ? "VICTORY" : boardFilledUp() ? "TIE" : "");
                        } else
                        {
                            output.println("MESSAGE dont be dumb");
                        }
                    }
                    
                    else if (command.startsWith("QUIT"))
                    {
                        return;
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("Player died: " + e);
            }
            finally
            {
                try {socket.close();} catch (IOException e) {}
            }
        }
    }
}