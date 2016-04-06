import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame {
	private int height = 600;
	private int width = 600;
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	
	public View()
	{
		this.setTitle("Ultimate Tic Tac Toe");
		this.setVisible(true);
		this.setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label.setIcon(new ImageIcon("MasterBoard.png"));
		panel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                	getChoice(e.getX(), e.getY());}});
		panel.add(label);
		this.add(panel);
		
		validate();
	}
	
	public int[] getChoice(int x, int y){
		System.out.println("You clicked at " + x +", " + y);
		int[] location = {10, 10};
		int rowBig = 0;
		int columnBig = 0;
		int rowSmall = 0;
		int columnSmall = 0;
		
		//Clicked on Line
		if (x > 180 && x < 210){
			return location;
		}
		if (x > 390 && x < 420){ 
			return location;
		}
		if (y > 180 && y < 210){
			return location;
		}
		if (y > 390 && y < 420){
			return location;
		}
		
		//Get Big Column
		if(x > 0 && x < 180)
			columnBig = 1;
		if(x > 210 && x < 390)
			columnBig = 2;
		if(x > 420 && x < 600)
			columnBig = 3;
		
		//Get Big Row
		if(y > 0 && y < 180)
			rowBig = 1;
		if(y > 210 && y < 390)
			rowBig = 2;
		if(y > 420 && y < 600)
			rowBig = 3;
		
		//Set location[0]
		if(columnBig == 1){
			if (rowBig == 1)
				location[0] = 0;
			if (rowBig == 2){
				location[0] = 3;
				y = y - 210;
			}
			if (rowBig == 3){
				location[0] = 6;
				y = y - 420;
			}
		}
		else if (columnBig == 2){
			if (rowBig == 1)
				location[0] = 1;
			if (rowBig == 2){
				location[0] = 4;
				y = y - 210;
			}
			if (rowBig == 3){
				location[0] = 7;
				y = y - 420;
			}
			
			x = x - 210;
		}
		else if (columnBig == 3){
			if (rowBig == 1)
				location[0] = 2;
			if (rowBig == 2){
				location[0] = 5;
				y = y - 210;
			}
			if (rowBig == 3){
				location[0] = 8;
				y = y - 420;
			}
			
			x = x - 420;
		}
		
		//Get Small Column
		if(x > 0 && x < 70)
			columnSmall = 1;
		if(x > 75 && x < 120)
			columnSmall = 2;
		if(x > 125 && x < 180)
			columnSmall = 3;
				
		//Get Small Row
		if(y > 0 && y < 70)
			rowSmall = 1;
		if(y > 75 && y < 120)
			rowSmall = 2;
		if(y > 125 && y < 180)
			rowSmall = 3;
		
		//Set location[1]
		if(columnSmall == 1){
			if (rowSmall == 1)
				location[1] = 0;
			if (rowSmall == 2)
				location[1] = 3;
			if (rowSmall == 3)
				location[1] = 6;
		}
		else if(columnSmall == 2){
			if (rowSmall == 1)
				location[1] = 1;
			if (rowSmall == 2)
				location[1] = 4;
			if (rowSmall == 3)
				location[1] = 7;
		}
		else if(columnSmall == 3){
			if (rowSmall == 1)
				location[1] = 2;
			if (rowSmall == 2)
				location[1] = 5;
			if (rowSmall == 3)
				location[1] = 8;
		}
		
				
		System.out.println(location[0] + " " + location[1]);
		return location;
	}
	
	public void update(int big, int small){
		
	}
		
	public static void main(String args[]){
		View view = new View();
		view.setResizable(false);
	}
}