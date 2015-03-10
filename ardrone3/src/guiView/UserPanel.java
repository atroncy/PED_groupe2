package guiView;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserPanel extends JPanel{

	private JTextField _tfAltitude, _tfPanAngle, _tfSpeed;
	private JButton _launch, _stop, _video;

	
	public UserPanel(){
		
		this.setLayout(new BorderLayout());
		
		JLabel labelAltitude = new JLabel("Altitude Max");
		JLabel labelPanAngle = new JLabel("Panning");
		JLabel labelSpeed = new JLabel("Vertical Speed");
		_tfAltitude = new JTextField(3);
		_tfPanAngle = new JTextField();
		_tfSpeed = new JTextField();
		_launch = new JButton("Launch");
		_stop = new JButton("STOPEU");
		_video = new JButton("Video");
		
		
		Box left = Box.createVerticalBox();
		Box right = Box.createVerticalBox();
		Box filler = Box.createVerticalBox();
		Box filler2 = Box.createVerticalBox();
		Box filler3 = Box.createVerticalBox();
		Box oui = Box.createHorizontalBox();
		Box bot = Box.createHorizontalBox();
		
		left.add(labelAltitude);
		left.add(Box.createVerticalStrut(50));
		left.add(labelPanAngle);
		left.add(Box.createVerticalStrut(50));
		left.add(labelSpeed);
		
		filler.add(Box.createHorizontalStrut(50));
		filler2.add(Box.createHorizontalStrut(50));
		filler3.add(Box.createHorizontalStrut(50));
		
		right.add(Box.createVerticalStrut(40));
		right.add(_tfAltitude);
		right.add(Box.createVerticalStrut(30));
		right.add(_tfPanAngle);
		right.add(Box.createVerticalStrut(30));
		right.add(_tfSpeed);
		right.add(Box.createVerticalStrut(40));
		
		oui.add(filler);
		oui.add(left);
		oui.add(filler2);
		oui.add(right);
		oui.add(filler3);
		bot.add(_launch);
		bot.add(_stop);
		bot.add(_video);
		
		//this.addMouseListener(_mouseListener(_lauch,_stop,_video));
		
		this.add(oui,BorderLayout.CENTER);
		this.add(bot,BorderLayout.SOUTH);
		
	}
	
}
