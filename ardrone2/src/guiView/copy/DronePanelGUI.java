package guiView.copy;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import guiListener.KeyboardDrone;
import guiModel.ConsoleModel;
import drone.Controller;

public class DronePanelGUI extends JPanel {
	
	public static final int WIDTH = 640;
	private static final int HEIGHT	= 1080;
	
	public DronePanelGUI(KeyboardDrone k, ConsoleModel model,
			Controller controller) {
		
		this.setLayout(new BorderLayout());
		this.setSize(WIDTH,HEIGHT);
		
		VideoPanel videoPanel = new VideoPanel(controller);
		ModelPanel modelPanel = new ModelPanel(k,model);
		
		this.add(videoPanel, BorderLayout.NORTH);
		this.add(modelPanel, BorderLayout.SOUTH);
		
		
	}

}