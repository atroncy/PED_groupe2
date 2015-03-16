package guiListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ardrone3.Controller;
import ardrone3.InvalidArgumentException;
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
		case KeyEvent.VK_A:
			System.out.println("A pressed : start recording");
			_c.sendMessage(MessageHandler.recordStart(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_Z:
			System.out.println("Z pressed : stop recording");
			_c.sendMessage(MessageHandler.recordStop(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("RIGHT pressed : turn camera to the right");
			try {
				_c.sendMessage(MessageHandler.cameraPan((byte)5,_c.getSeqDataAck()));
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("LEFT pressed : turn camera to the left");
			try {
				_c.sendMessage(MessageHandler.cameraPan((byte)-5,_c.getSeqDataAck()));
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
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
