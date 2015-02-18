package video;

import guiView.copy.VideoPanel;

import java.awt.image.BufferedImage;

import drone.Controller;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;

public class FrameGrabber extends Thread{
	
	private Controller _controller;
	private VideoPanel _panel;
	private IplImage _iplImage;
	private BufferedImage _camImg;
	
	public FrameGrabber(Controller controller, VideoPanel panel){
		Loader.load(swresample.class);
		_controller = controller;
		_panel = panel;
	}
	
	
	//FIX voir FrameRecorder FIX
	public IplImage getIplImage(){
		return _iplImage;
	}
	
	public void run(){
		int i = 0;
		FFmpegFrameGrabber _ffg = new FFmpegFrameGrabber("tcp://"+_controller.getAddr()+":5555");
		
		try {
			_ffg.start();
			
			Thread.sleep(1000);
			
			while(true){
				++i;
				
				if(i==60){
					_ffg.restart();
				}
				_iplImage = _ffg.grab();
				_camImg = _iplImage.getBufferedImage();
				_panel.setSize(640,360);
				_panel.setCam(_camImg);
				_panel.repaint();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
