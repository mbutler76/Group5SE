import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame {
	private int height = 600;
	private int width = 800;
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	
	public View()
	{
		this.setTitle("Ultimate Tic Tac Toe");
		this.setVisible(true);
		this.setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label.setIcon(new ImageIcon("MasterBoard.png"));
		panel.add(label);
		add(panel);
		
		validate();
	}
			
}