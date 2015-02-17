package guiView;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameGrabber.Exception;

import video.KeepAlive;
import drone.Commands;
import drone.Controller;






// /*For test
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
// */


public class LeftPanelGUI extends JPanel {
	private JLabel _camLabel;
	private BufferedImage _camImgNew;
	private Controller _controller;
	private FFmpegFrameGrabber _ffg;
	private FFmpegFrameRecorder _ffr;
	private ShowImage _showImage;
	private IplImage _iplImage;
	
	public LeftPanelGUI(Controller controller){
		
		_controller = controller;
		
		
		this.setLayout(new BorderLayout());
		this.setSize(640, 360);
		
	
		///* Gestion Vidéo
		// pour initialiser le codec

		
		Loader.load(swresample.class);
		_ffg = new FFmpegFrameGrabber("tcp://192.168.1.1:5555"); 
		
		_ffr = new FFmpegFrameRecorder("banane.h264",640,360);

		
		try {
			_ffg.start();
			_ffr.start();
			try {
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {				
				e.printStackTrace();
			}
			System.out.println("3");
			_showImage = new ShowImage(this);
			_showImage.start();
			System.out.println("4");
		} catch (Exception e1) {
			e1.printStackTrace();
		} catch (org.bytedeco.javacv.FrameRecorder.Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		_camLabel = new JLabel();
		this.add(_camLabel,BorderLayout.NORTH);
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
		KeepAlive test = new KeepAlive(_controller);
		test.init();
		test.start();
		
	}

	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(_camImgNew,0,0,_camImgNew.getWidth(),_camImgNew.getHeight(),_camLabel);
	}
	
	
	//TODO Or NOT TODO, That is the question.
	class ShowImage extends Thread {
		private LeftPanelGUI _panel;
		
		
		public ShowImage(LeftPanelGUI panel){
			_panel = panel;
			_camImgNew = null;
		}
		
		public void run(){
			int i = 0;
			while(true){
				try{
					//oui
					++i;
					//Horizontal : 60fps
					if (i==60){
						_ffg.restart();
					}
					//Vertical : 40 fps
					
					//récupère l'image et la transfo en buffered image
					_iplImage = _ffg.grab();
					_camImgNew = _iplImage.getBufferedImage();
					_ffr.record(_iplImage);
					//resize et repaint l'image
					_panel.setSize(640,360);
					_panel.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}	
}
