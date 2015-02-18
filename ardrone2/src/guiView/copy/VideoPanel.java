package guiView.copy;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import video.KeepAlive;
import video.FrameGrabber;
import video.FrameRecorder;
import drone.Controller;






 /*For test image
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
 */


public class VideoPanel extends JPanel {
	private JLabel _camLabel;
	private BufferedImage _camImgNew;
	
	
	public VideoPanel(Controller controller){
		
		Controller _controller = controller;
		
		this.setLayout(new BorderLayout());
		this.setSize(640, 360);
		
	
		///* Gestion Vidéo
		
		_camLabel = new JLabel();
		this.add(_camLabel,BorderLayout.NORTH);
		
		KeepAlive keepAlive = new KeepAlive(_controller);
		FrameGrabber fg = new FrameGrabber(_controller,this);
	//	FrameRecorder fr = new FrameRecorder(fg,_controller);
		keepAlive.init();
		fg.start();
		keepAlive.start();
	//	fr.start();

	
		//*/
		
	 	/*Test IHM image
		
		BufferedImage img=null;
		try {
			img = ImageIO.read(new File("res/yes.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(img);
		_camLabel = new JLabel(icon);
		this.add(_camLabel, BorderLayout.NORTH);
		*/

		
	}

	public void setCam (BufferedImage camImgNew){
		_camImgNew = camImgNew;
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(_camImgNew,0,0,_camImgNew.getWidth(),_camImgNew.getHeight(),_camLabel);
	}
}