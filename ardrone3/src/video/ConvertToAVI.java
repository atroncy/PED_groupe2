package video;

import java.io.File;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.swresample;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;


public class ConvertToAVI {

	public void convert(String filePath, String destinationPath) {	
		
		Loader.load(swresample.class); //Necessary call
		
		FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(filePath);

	    IplImage captured_frame = null;

	    FFmpegFrameRecorder recorder = null;
	    //recorder = new FFmpegFrameRecorder("/net/travail/jerpoirier/VideosBebop/ConversionAVIDone.avi", 300, 300);
	    recorder = new FFmpegFrameRecorder(destinationPath + "ConversionAVIDone.avi", 300, 300);
	    recorder.setVideoCodec(13);
	    recorder.setFrameRate(30);
	    recorder.setFormat("avi");
	    try {
	        recorder.start();
	        frameGrabber.start();
	        while (true) {
	            try {
	                captured_frame = frameGrabber.grab();

	                if (captured_frame == null) {
	                    System.out.println("cvQueryFrame ending");
	                    break;
	                }
	                recorder.record(captured_frame);
	            } catch (Exception e) {
	            }
	        }
	        recorder.stop();
	        recorder.release();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}
