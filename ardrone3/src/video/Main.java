package video;

import java.io.*;
import java.util.Scanner;

import org.bytedeco.javacv.FrameGrabber.Exception;


public class Main {
	
	public static void main(String[] args) throws IOException {
		
		//Set up the command and parameter
		String pythonScriptPath = "/net/travail/jerpoirier/workspacePED/PED_FTP/src/FTPscript.py";
				
		String[] cmd = new String[1];
		cmd[0] = pythonScriptPath;
				 
		//Create runtime to execute external command
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);
				 
		//Retrieve output from python script
		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		System.out.println("Lauching the script ...");
		while((line = bfr.readLine()) != null) {
			//Display each output line form python script
			System.out.println(line);
		}
		
		
		ConvertToAVI conversion = new ConvertToAVI();
		//Chemin Video
		String filePath = "/net/cremi/jerpoirier/VideoBebop.mp4";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrer le repertoire de destination (où sera stocké le fichier avi) :");
		String destinationPath = sc.nextLine();
		System.out.println("Vous avez saisi : " + destinationPath);
		
		//String destinationPath = "/net/travail/jerpoirier/VideosBebop/";
		
		System.out.println("Lancement de la conversion du fichier mp4 en avi ...");
		conversion.convert(filePath, destinationPath);

	}

}
