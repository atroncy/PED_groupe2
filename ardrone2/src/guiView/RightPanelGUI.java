package guiView;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RightPanelGUI extends JPanel{
	
	private JLabel _camLabel;

	public RightPanelGUI(){
		
		this.setLayout(new BorderLayout());
		
		BufferedImage img=null;
		try {
			img = ImageIO.read(new File("res/miniyes.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(img);
		_camLabel = new JLabel(icon);
		this.add(_camLabel, BorderLayout.NORTH);
		
	}
}