package video;

import drone.Commands;
import drone.Controller;

public class KeepAlive extends Thread {

	private Controller _controller;

	
	public KeepAlive(Controller controller) {
		_controller = controller;
	}
	
	
	public void init(){
		String message;
		message = Commands.configIDS(_controller.getSeq());
		_controller.sendMessage(message);
		message = Commands.configCodec(_controller.getSeq());
		_controller.sendMessage(message);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
			
		message = Commands.configIDS(_controller.getSeq());
		_controller.sendMessage(message);
		
		message = Commands.configCameraHorizontal(_controller.getSeq());
		_controller.sendMessage(message);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void run() {
		String message;
		while(true){
			message = Commands.configIDS(_controller.getSeq());
			_controller.sendMessage(message);
			
			message = Commands.configCameraHorizontal(_controller.getSeq());
			_controller.sendMessage(message);
			System.out.println(_controller.getAddr());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
