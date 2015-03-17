package guiListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ardrone3.Controller;
import ardrone3.InvalidArgumentException;
import ardrone3.MessageHandler;


public class KeyboardDrone implements KeyListener{

 	private Controller _c;
	private MessageHandler _mh;
	
	public KeyboardDrone(Controller c, MessageHandler mh){
		_c = c;	
		_mh = mh;
	}

	
	private void execute(int key) {
		switch(key){
		case KeyEvent.VK_UP:
			System.out.println("UP key pressed");
			_c.sendMessage(_mh.takeoff(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("DOWN key pressed");
			_c.sendMessage(_mh.landing(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_A:
			System.out.println("A pressed : set time and date");
			_c.sendMessage(_mh.setCurrentDate(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			_c.sendMessage(_mh.setCurrentTime(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_Z:
			System.out.println("Z pressed : start recording");
			_c.sendMessage(_mh.recordStart(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_E:
			System.out.println("E pressed : stop recording");
			_c.sendMessage(_mh.recordStop(_c.getSeqDataAck()));
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("RIGHT pressed : turn camera to the right");
			try {
				_c.sendMessage(_mh.cameraPan((byte)5,_c.getSeqDataAck()));
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			_c.incSeqDataAck();
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("LEFT pressed : turn camera to the left");
			try {
				_c.sendMessage(_mh.cameraPan((byte)-5,_c.getSeqDataAck()));
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
