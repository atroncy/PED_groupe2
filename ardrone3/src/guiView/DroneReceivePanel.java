package guiView;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DroneReceivePanel extends JPanel implements Observer{
	
	private JTextField _tfSettedAltitude, _tfSettedPanAngle, _tfSettedSpeed, _tfCurrentAltitude, _tfCurrentPanAngle, _tfCurrentSpeed, _tfCurrentState, _tfSettedMedia;
		
	public DroneReceivePanel(){
			
		this.setLayout(new BorderLayout());
		
		JLabel labelSetted = new JLabel("Setted");
		JLabel labelSettedAltitude = new JLabel("Altitude Max");
		JLabel labelSettedPanAngle = new JLabel("Panning");
		JLabel labelSettedSpeed = new JLabel("Vertical Speed");
		JLabel labelSettedMedia = new JLabel("Media Recording");
		_tfSettedAltitude = new JTextField();
		_tfSettedAltitude.setEnabled(false);
		_tfSettedPanAngle = new JTextField();
		_tfSettedPanAngle.setEnabled(false);
		_tfSettedSpeed = new JTextField();
		_tfSettedSpeed.setEnabled(false);
		_tfSettedMedia = new JTextField();
		_tfSettedMedia.setEnabled(false);
		JLabel labelCurrent = new JLabel("Current");
		JLabel labelCurentAltitude = new JLabel("Altitude Max");
		JLabel labelCurrentPanAngle = new JLabel("Panning");
		JLabel labelCurrentSpeed = new JLabel("Vertical Speed");
		JLabel labelCurrentState = new JLabel("Drone State");
		_tfCurrentAltitude = new JTextField();
		_tfCurrentAltitude.setEnabled(false);
		_tfCurrentPanAngle = new JTextField();
		_tfCurrentPanAngle.setEnabled(false);
		_tfCurrentSpeed = new JTextField();
		_tfCurrentSpeed.setEnabled(false);
		_tfCurrentState = new JTextField();
		_tfCurrentState.setEnabled(false);

		
		Box left = Box.createVerticalBox();
		Box right = Box.createVerticalBox();
		Box oui = Box.createHorizontalBox();
		
		left.add(labelSetted);
		left.add(Box.createVerticalStrut(5));
		left.add(labelSettedAltitude);
		left.add(Box.createVerticalStrut(5));
		left.add(labelSettedPanAngle);
		left.add(Box.createVerticalStrut(5));
		left.add(labelSettedSpeed);
		left.add(Box.createVerticalStrut(5));
		left.add(labelSettedMedia);
		left.add(Box.createVerticalStrut(40));
		left.add(labelCurrent);
		left.add(Box.createVerticalStrut(5));
		left.add(labelCurentAltitude);
		left.add(Box.createVerticalStrut(5));
		left.add(labelCurrentPanAngle);
		left.add(Box.createVerticalStrut(5));
		left.add(labelCurrentSpeed);
		left.add(Box.createVerticalStrut(5));
		left.add(labelCurrentState);
		
		right.add(Box.createVerticalStrut(33));
		right.add(_tfSettedAltitude);
		right.add(_tfSettedPanAngle);
		right.add(_tfSettedSpeed);
		right.add(_tfSettedMedia);
		right.add(Box.createVerticalStrut(56));
		right.add(_tfCurrentAltitude);
		right.add(_tfCurrentPanAngle);
		right.add(_tfCurrentSpeed);
		right.add(_tfCurrentState);
		right.add(Box.createVerticalStrut(20));
		
		oui.add(left);
		oui.add(right);
		
		this.add(oui,BorderLayout.CENTER);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		/*
		 * _tfSettedAltitude.setText(_parsed.Truc)
		 * _tfSettedPanAngle
		 * _tfSettedSpeed
		 * _tfCurrentAltitude
		 * _tfCurrentPanAngle
		 * _tfCurrentSpeed
		 * _tfCurrentState
		 * _tfSettedMedia
		 * 
		 */
		
	}
}
