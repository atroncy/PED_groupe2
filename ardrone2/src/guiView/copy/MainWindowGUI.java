package guiView.copy;

import guiListener.KeyboardDrone;
import guiModel.ConsoleModel;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import drone.Controller;


public class MainWindowGUI extends JFrame{
	private static final int WIDTH 	= 1480;
	private static final int HEIGHT	= 1080;
	private static final String TITLE = "Oui"; 
		
	public MainWindowGUI(KeyboardDrone k1,KeyboardDrone k2, ConsoleModel model1, ConsoleModel model2, Controller controller1, Controller controller2){
		
		this.setTitle(TITLE);
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		

		this.setLayout(new BorderLayout());
		
		
		RightPanelGUI rightPanel = new RightPanelGUI();
		DronePanelGUI dronePanel1 = new DronePanelGUI(k1, model1, controller1);
		DronePanelGUI dronePanel2 = new DronePanelGUI(k2, model2, controller2);
		
		this.add(dronePanel1, BorderLayout.WEST);
		this.add(dronePanel2, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
		
		this.setVisible(true);
		
	}
}