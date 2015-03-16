package guiView;

import guiListener.KeyboardDrone;

import java.awt.GridLayout;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import ardrone3.Controller;
import ardrone3.Parser;
import ardrone3.ServerUDP;
import model.NavData;

public class MainWindowGUI extends JFrame{
	private static final int WIDTH 	= 1200;
	private static final int HEIGHT	= 300;
	private static final String TITLE = "Oui";
	
	public MainWindowGUI(NavData nav1, NavData nav2, KeyboardDrone k1, KeyboardDrone k2){
		
		this.setTitle(TITLE);
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(1,3));
		
		DroneGUI drone1 = new DroneGUI("Drone Uno",nav1,k1);
		DroneGUI drone2 = new DroneGUI("Drone Duo",nav2,k2);
		UserPanel user = new UserPanel();
		
		this.add(user);
		this.add(drone1);
		this.add(drone2);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main (String[] args){
		NavData nav1 = new NavData();
		NavData nav2 = new NavData();
		
		try {
			Controller ctrl = new Controller("192.168.42.1", 43210);
			Controller ctrl2 = new Controller("192.168.42.8", 43211);
			ctrl.init("Drone_Uno");
			KeyboardDrone k1 = new KeyboardDrone(ctrl);
			KeyboardDrone k2 = new KeyboardDrone(ctrl2);
			MainWindowGUI mw = new MainWindowGUI(nav1,nav2,k1,k2);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		nav1.triggerChange();
		nav2.triggerChange();
		
		
		Parser p1 = new Parser(nav1);
		ServerUDP s1 = new ServerUDP(p1,43210);
		s1.start();
	}
}
