import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JWindow {
  private int duration;
  public SplashScreen(int d) {
    duration = d;
  }


  public void showSplash() {
    JPanel content = (JPanel)getContentPane();
    content.setBackground(Color.white);


    int width = 450;
    int height =115;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width-width)/2;
    int y = (screen.height-height)/2;
    setBounds(x,y,width,height);


    JLabel label = new JLabel(new ImageIcon("splash.png"));
    JLabel copyrt = new JLabel
      ("Copyright 2016, Team 5", JLabel.CENTER);
    copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
    content.add(label, BorderLayout.CENTER);
    content.add(copyrt, BorderLayout.SOUTH);
    content.setBorder(BorderFactory.createLineBorder(Color.blue, 5));


    setVisible(true);


    try { Thread.sleep(duration); } catch (Exception e) {}

    setVisible(false);
  }

  public void showSplashAndExit() {
    showSplash();
    System.exit(0);
  }
}