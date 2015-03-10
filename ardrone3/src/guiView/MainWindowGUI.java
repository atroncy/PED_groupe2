package guiView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainWindowGUI extends JFrame{
	private static final int WIDTH 	= 1200;
	private static final int HEIGHT	= 300;
	private static final String TITLE = "Oui";
	
	public MainWindowGUI(){
		
		this.setTitle(TITLE);
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(1,3));
		
		DroneGUI drone1 = new DroneGUI("Drone Uno");
		DroneGUI drone2 = new DroneGUI("Drone Duo");
		UserPanel user = new UserPanel();
		
		this.add(user);
		this.add(drone1);
		this.add(drone2);
		this.setResizable(false);
		this.setVisible(true);
	}
}
