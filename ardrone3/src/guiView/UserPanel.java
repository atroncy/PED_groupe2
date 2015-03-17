package guiView;


import guiListener.MouseUser;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import systeme_de_drone_de_facade.Automatic;

public class UserPanel extends JPanel{


	
	public UserPanel(Automatic auto){
		
		this.setLayout(new BorderLayout());
		
		JLabel labelAltitude = new JLabel("Altitude Max");
		JLabel labelPanAngle = new JLabel("Panning");
		JLabel labelSpeed = new JLabel("Vertical Speed");
		JTextField tfAltitude = new JTextField();
		JTextField tfPanAngle = new JTextField();
		JTextField tfSpeed = new JTextField();
		JButton launch = new JButton("Launch");
		JButton stop = new JButton("STOPEU");
		JButton video = new JButton("Video");
		
		
		Box left = Box.createVerticalBox();
		Box right = Box.createVerticalBox();
		Box filler = Box.createVerticalBox();
		Box filler2 = Box.createVerticalBox();
		Box filler3 = Box.createVerticalBox();
		Box oui = Box.createHorizontalBox();
		Box bot = Box.createHorizontalBox();
		
		left.add(labelAltitude);
		left.add(Box.createVerticalStrut(50));
		left.add(labelPanAngle);
		left.add(Box.createVerticalStrut(50));
		left.add(labelSpeed);
		
		filler.add(Box.createHorizontalStrut(50));
		filler2.add(Box.createHorizontalStrut(50));
		filler3.add(Box.createHorizontalStrut(50));
		
		right.add(Box.createVerticalStrut(40));
		right.add(tfAltitude);
		right.add(Box.createVerticalStrut(30));
		right.add(tfPanAngle);
		right.add(Box.createVerticalStrut(30));
		right.add(tfSpeed);
		right.add(Box.createVerticalStrut(40));
		
		oui.add(filler);
		oui.add(left);
		oui.add(filler2);
		oui.add(right);
		oui.add(filler3);
		bot.add(launch);
		bot.add(stop);
		bot.add(video);
		
		MouseUser m = new MouseUser(launch, stop, video,tfPanAngle,tfAltitude,tfSpeed, auto);
		
		launch.addMouseListener(m);
		stop.addMouseListener(m);
		video.addMouseListener(m);
		this.add(oui,BorderLayout.CENTER);
		this.add(bot,BorderLayout.SOUTH);
		
	}
	
	 
	
}
