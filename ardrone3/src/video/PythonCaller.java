package video;
import java.io.*;
 
public class PythonCaller {
	/**
	* @param args
	* @throws IOException
	*/
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
		System.out.println("Getting the video ...");
		while((line = bfr.readLine()) != null) {
			//Display each output line form python script
			System.out.println(line);
		}
	}
}