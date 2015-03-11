package guiView;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.NavData;

public class DroneGUI extends JPanel{

	public DroneGUI(String drone,NavData nav){
		
		this.setLayout(new BorderLayout());
		
		DroneSendPanel droneSend = new DroneSendPanel();
		DroneReceivePanel droneReceive = new DroneReceivePanel(nav);
		JLabel labelDrone = new JLabel(drone);
		labelDrone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		
		this.add(labelDrone,BorderLayout.NORTH);
		this.add(droneSend,BorderLayout.WEST);
		this.add(droneReceive,BorderLayout.CENTER);
		
	}
	
}
