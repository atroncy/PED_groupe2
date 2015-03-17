package guiListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import systeme_de_drone_de_facade.Automatic;

public class MouseUser implements MouseListener{
	
	private JButton _bStart,_bStop,_bVideo;
	private Automatic auto;
	private JTextField _userPan, _userAltitude, _userSpeed;
	
	public MouseUser(JButton b1,JButton b2,JButton b3, JTextField j1,JTextField j2,JTextField j3,Automatic auto){
		_bStart = b1;
		_bStop = b2;
		_bVideo = b3;
		_userPan = j1;
		_userAltitude = j2;
		_userSpeed = j3;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		if(source == _bStart){
			System.out.println("Start Automatic Thread");
		// 	never tested :,(
        //	auto.userInit(Float.parseFloat(_userSpeed.getText()),
        //	Double.parseDouble(_userAltitude.getText()),
		//	Integer.parseInt(_userPan.getText()));
		//	auto.start();
		}else if (source == _bStop)
			System.out.println("EMERGENCY STOP AUTOMATIC THREAD");
		//	auto.stop;
		else if (source == _bVideo)
			System.out.println("LA VIDEO ICI");
		
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
