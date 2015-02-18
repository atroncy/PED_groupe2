import guiListener.KeyboardDrone;
import guiModel.ConsoleModel;
import drone.Controller;


import guiView.copy.MainWindowGUI;
//import guiView.MainWindowGUI;


public class Main {
	
	public static void main (String[] args) throws InterruptedException{
	
		Controller controller1; 		
		KeyboardDrone keyboard1;
		ConsoleModel model1;
		
		///*// Drone 2
		Controller controller2; 		
		KeyboardDrone keyboard2;
		ConsoleModel model2;
		//*/
		
		model1 = new ConsoleModel("drone1");
		controller1 = new Controller("192.168.1.1", 5556, "127.0.0.1", 7000,model1);
		keyboard1 = new KeyboardDrone(controller1, model1);
		
		///*// Drone 2
		model2 = new ConsoleModel("drone2");
		controller2 = new Controller("192.168.1.2", 5556, "127.0.0.1", 7001,model2);
		keyboard2 = new KeyboardDrone(controller2, model2);
		//*/
		
		//new MainWindowGUI(keyboard1, model1, controller1);
		
		//Avec 2 drone
		new MainWindowGUI(keyboard1, keyboard2, model1, model2, controller1, controller2);
	}	
}

