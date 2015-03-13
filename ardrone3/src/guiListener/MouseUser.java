package guiListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MouseUser implements MouseListener{
	
	private JButton _b1,_b2,_b3;
	
	public MouseUser(JButton b1,JButton b2,JButton b3){
		_b1 = b1;
		_b2 = b2;
		_b3 = b3;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		if(source == _b1)
			System.out.println("I wish I could buy me a spaceship and fly, past the sky");
			//stuff
		else if (source == _b2)
			System.out.println("We won't stop, everybody move");
			//stuff
		else if (source == _b3)
			System.out.println("I had like to propose a toast, i said toast motherf****");
			//stuff
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
