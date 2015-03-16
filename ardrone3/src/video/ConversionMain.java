package video;

import java.io.File;

import org.bytedeco.javacv.FrameGrabber.Exception;


public class ConversionMain {
	
	public static void main(String[] args) {
			
		ConvertToAVI conversion = new ConvertToAVI();
		String pathFile = "/net/travail/jerpoirier/VideosBebop/VideoBebop.mp4";
		String destinationPath = "/net/travail/jerpoirier/VideosBebop/";

		System.out.println("Lancement de la conversion du fichier mp4 en avi ...");
		conversion.convert(pathFile, destinationPath);

	}

}