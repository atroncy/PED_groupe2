package guiView;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.NavData;

public class DroneReceivePanel extends JPanel implements Observer{
	
	private JTextField _tfSettedAltitude, _tfSettedPanAngle, _tfSettedSpeed, _tfCurrentAltitude, _tfCurrentPanAngle, _tfCurrentSpeed, _tfCurrentState, _tfCurrentMedia;
	private NavData _nav;
	
	public DroneReceivePanel(NavData nav){
		
		_nav = nav;
		_nav.addObserver(this);
		
		this.setLayout(new BorderLayout());
		
		JLabel labelSetted = new JLabel("Setted");
		JLabel labelSettedAltitude = new JLabel("Altitude Max");
		JLabel labelSettedPanAngle = new JLabel("Panning");
		JLabel labelSettedSpeed = new JLabel("Vertical Speed");
		_tfSettedAltitude = new JTextField();
		_tfSettedAltitude.setEditable(false);
		_tfSettedPanAngle = new JTextField();
		_tfSettedPanAngle.setEditable(false);
		_tfSettedSpeed = new JTextField();
		_tfSettedSpeed.setEditable(false);
		_tfCurrentMedia = new JTextField();
		_tfCurrentMedia.setEditable(false);
		JLabel labelCurrent = new JLabel("Current");
		JLabel labelCurentAltitude = new JLabel("Altitude Max");
		JLabel labelCurrentPanAngle = new JLabel("Panning");
		JLabel labelCurrentSpeed = new JLabel("Vertical Speed");
		JLabel labelCurrentState = new JLabel("Drone State");
		JLabel labelCurrentMedia = new JLabel("Media Recording");
		_tfCurrentAltitude = new JTextField();
		_tfCurrentAltitude.setEditable(false);
		_tfCurrentPanAngle = new JTextField();
		_tfCurrentPanAngle.setEditable(false);
		_tfCurrentSpeed = new JTextField();
		_tfCurrentSpeed.setEditable(false);
		_tfCurrentState = new JTextField();
		_tfCurrentState.setEditable(false);

		
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
		left.add(Box.createVerticalStrut(5));
		left.add(labelCurrentMedia);
		
		right.add(Box.createVerticalStrut(30));
		right.add(_tfSettedAltitude);
		right.add(_tfSettedPanAngle);
		right.add(_tfSettedSpeed);
		right.add(Box.createVerticalStrut(62));
		right.add(_tfCurrentAltitude);
		right.add(_tfCurrentPanAngle);
		right.add(_tfCurrentSpeed);
		right.add(_tfCurrentState);
		right.add(_tfCurrentMedia);
		right.add(Box.createVerticalStrut(20));
		
		oui.add(left);
		oui.add(right);
		
		this.add(oui,BorderLayout.CENTER);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		_tfSettedAltitude.setText(String.valueOf(_nav.getAltitudeMax()));
		_tfSettedPanAngle.setText(String.valueOf(_nav.getPan()));
		_tfSettedSpeed.setText(String.valueOf(_nav.getSpeedMax()));
		_tfCurrentAltitude.setText(String.valueOf(_nav.getCurrentAltitude()));
		_tfCurrentPanAngle.setText(String.valueOf(_nav.getPan()));
		_tfCurrentSpeed.setText(String.valueOf(_nav.getCurrentSpeed()));
		_tfCurrentState.setText(_nav.getDroneState());
		_tfCurrentMedia.setText(_nav.getMediaRecordState());
	}
}
