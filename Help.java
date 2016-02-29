import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Help extends JFrame{
ImageIcon img = new ImageIcon("help.png");
Image i = img.getImage();
	public Help()
	{
		setLayout(new BorderLayout());
		setSize(600, 566);
		setVisible(true);
		JLabel bg = new JLabel(new ImageIcon(i));
		
		add(bg);
		
	}
}
