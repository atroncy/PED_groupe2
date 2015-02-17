package guiView;

import guiListener.KeyboardDrone;
import guiModel.ConsoleModel;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import drone.Controller;


public class MainWindowGUI extends JFrame{
	private static final int WIDTH 	= 750;
	private static final int HEIGHT	= 600;
	private static final String TITLE = "Oui"; 
	
	
	public MainWindowGUI(KeyboardDrone k,ConsoleModel model, Controller controller){
		this.setTitle(TITLE);
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setSize(1280,800);

		this.setLayout(new BorderLayout());
		
		
		RightPanelGUI rightPanel = new RightPanelGUI(k,model);
		LeftPanelGUI leftPanel = new LeftPanelGUI(controller);
		
		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.EAST);
		
		this.setVisible(true);
		
		
	}
	
	public MainWindowGUI(KeyboardDrone k1,KeyboardDrone k2, ConsoleModel model1, ConsoleModel model2, Controller controller1, Controller controller2){
		//TODO
		/*
		this.setTitle(TITLE);
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setSize(1280,800);

		this.setLayout(new BorderLayout());
		
		
		RightPanelGUI rightPanel = new RightPanelGUI(k,model);
		LeftPanelGUI leftPanel = new LeftPanelGUI(controller);
		
		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.EAST);
		
		this.setVisible(true);
		*/
	}
}
