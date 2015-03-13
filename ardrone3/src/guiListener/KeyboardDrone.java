package guiListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ardrone3.Controller;
import ardrone3.MessageHandler;


public class KeyboardDrone implements KeyListener{

 	private Controller _c;
	
	public KeyboardDrone(Controller c){
		_c = c;	
	}

	
	private void execute(int key) {
		switch(key){
		case KeyEvent.VK_UP:
			System.out.println("UP key pressed");
			_c.sendMessage(MessageHandler.takeoff(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("DOWN key pressed");
			_c.sendMessage(MessageHandler.landing(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			break;
		}
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		execute(key);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
