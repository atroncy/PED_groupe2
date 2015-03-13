package guiListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardDrone implements KeyListener{

// 	private Controller _c;
	
	public KeyboardDrone(/*Controller c*/){
		// _c = c;	
	}

	
	private void execute(int key) {
		switch(key){
		case KeyEvent.VK_UP:
			System.out.println("We can send this bitch up, it can't go down");
			// Something like this _c.send(Commands.gaz(value, _c.getSeq)
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
