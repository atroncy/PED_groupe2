package video;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameRecorder.Exception;

import drone.Controller;



public class FrameRecorder extends Thread{
	
	private FrameGrabber _fg;
	private Controller _controller;
	private FFmpegFrameRecorder _fr;
	
	public FrameRecorder(FrameGrabber fg, Controller controller){
		_fg = fg;
		_controller = controller;
		_fr = new FFmpegFrameRecorder(_controller.getAddr()+".h264",640,360);
	}
	
	
	// FIX plante .............. For some unknown reason
	public void run(){
		while(true){
			try {
				_fr.start();
				_fr.record(_fg.getIplImage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
