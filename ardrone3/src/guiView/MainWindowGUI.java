package guiView;

import java.awt.GridLayout;

import javax.swing.JFrame;

import ardrone3.Parser;

import model.NavData;

public class MainWindowGUI extends JFrame{
	private static final int WIDTH 	= 1200;
	private static final int HEIGHT	= 300;
	private static final String TITLE = "Oui";
	
	public MainWindowGUI(NavData nav1, NavData nav2){
		
		this.setTitle(TITLE);
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(1,3));
		
		DroneGUI drone1 = new DroneGUI("Drone Uno",nav1);
		DroneGUI drone2 = new DroneGUI("Drone Duo",nav2);
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
		MainWindowGUI mw = new MainWindowGUI(nav1,nav2);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nav1.triggerChange();
		nav2.triggerChange();
		
		byte grostasdeBits[] = new byte[15];
	    grostasdeBits[0]= (byte)(0x04);
	    grostasdeBits[1]= (byte)(0x0b);
    	grostasdeBits[2]= (byte)(0x02);
    	grostasdeBits[3]= (byte)(0x0f);
		grostasdeBits[4]= (byte)(0x00);
		grostasdeBits[5]= (byte)(0x00);
		grostasdeBits[6]= (byte)(0x00);
		grostasdeBits[7]= (byte)(0x01);
		grostasdeBits[8]= (byte)(0x04);
		grostasdeBits[9]= (byte)(0x01);
		grostasdeBits[10]= (byte)(0x00);
		grostasdeBits[11]= (byte)(0x04);
		grostasdeBits[12]= (byte)(0x00);
		grostasdeBits[13]= (byte)(0x00);
		grostasdeBits[14]= (byte)(0x00);

		Parser p1 = new Parser(nav1);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p1.parse(grostasdeBits);
	     
	}
}
