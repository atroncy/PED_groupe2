 
import java.io.*;
import java.awt.Robot;
import java.net.UnknownHostException;
import java.util.*;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
 
public class FTPClientMain
{
	public static void main(String[] args)
	{
		FTPClient clientFtp = new FTPClient(true,1);
		try {
			clientFtp.connect("192.168.42.1", 21);
			//clientFtp.login("toto", "");
			
			//Runtime.getRuntime().exec("python FTPscript");
			
			clientFtp.changeDirectory("internal_000/Bebop_Drone/media/");
			//clientFtp.uploadFile("~/test.txt");
			clientFtp.downloadFile("Bebop_Drone_2015-03-09T174648+0000_706319.mp4");
			clientFtp.logout();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clientFtp.disconnect();
	}
	
	/*public static void main(String[] args)
	{
		
		// Mettez le 1er argument à true si vous désirez afficher les messages de 
					// communication entre vous et le serveur.
					// Le deuxième argument règle la méthode de connexion
					// 0: Passif (PASV)
					// 1: Actif (PORT)
		FTPClient clientFTP = new FTPClient(true,1);
		try {
			clientFTP.connect("192.168.42.1", 21);
			//clientFTP.login("", "");
			

			//clientFTP.getSystemType();
			//clientFTP.getCurrentDirectory();
			clientFTP.changeDirectory("internal_000/Bebop_Drone/media/");
			//clientFTP.executeCommand("ls");
			//clientFTP.getFileSize();
			clientFTP.downloadFile("Bebop_Drone_2015-03-09T174648+0000_706319.mp4");
			
			
			//clientFtp.uploadFile("test.txt");
			clientFTP.logout();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clientFTP.disconnect();
	}*/
	
	
	/*public static void main (String[] args)
	{
		String serverName;
		FTPClient ftp = null;
 
		try
		{
			if (args.length == 0)
			{
				serverName = getStringFromUser("Entrer le nom du serveur auquel vous voulez vous connecter: ");
				if (serverName.length() == 0)  {  return;  }
			}  else  {
				serverName = args[0];
			}
 
			// Mettez le 1er argument à true si vous désirez afficher les messages de 
			// communication entre vous et le serveur.
			// Le deuxième argument règle la méthode de connexion
			// 0: Passif (PASV)
			// 1: Actif (PORT)
			ftp = new FTPClient(true,1);
			System.out.println("Connexion anonyme à " + serverName);
			ftp.connect(serverName);
 
			//if (ftp.login("", ""))
			//{
				System.out.println("Identification réussie !");
				System.out.println("Le type de system est: " + ftp.getSystemType());
				System.out.println("Le dossier courant est: " + ftp.getCurrentDirectory());
				String files = ftp.listFiles();
				String subDirs = ftp.listSubdirectories();
				System.out.println("Fichiers:\n" + files);
				System.out.println("Sous dosssiers:\n" + subDirs);
 
				// On essaye de passer dans le 1er sous-dossier
				StringTokenizer st = new StringTokenizer(subDirs, ftp.lineTerm);
				String sdName = "";
				if (st.hasMoreTokens())  { sdName = st.nextToken(); }
 
				if (sdName.length() > 0)
				{
					System.out.println("Changement de dossier vers: " + sdName);
					if (ftp.changeDirectory(sdName))
					{
						// Juste pour le test, on va essayé de télécharger les 3 premiers fichiers.
						files = ftp.listFiles();
						st = new StringTokenizer(files, ftp.lineTerm);
 
						String fileName;
						int count = 1;
						while ((st.hasMoreTokens()) && (count < 3))
						{
							fileName = st.nextToken();
							System.out.println("Téléchargement de " + fileName + " vers C:\\");
							try
							{
								if (ftp.downloadFile(fileName, "C:\\" + fileName))
								{
									System.out.println("Téléchargement réussi!");
								}  else  {
									System.out.println("Erreur lors du Téléchargement " + fileName);
								}
							}  catch(Exception de)  {
								System.out.println("ERROR: " + de.getMessage());
							}
 
							count++;
						}
					}
 
				}  
				
				else  {
					System.out.println("Il n'y a pas de sous dossier!");
				}
 
				ftp.logout();
				ftp.disconnect();
				System.out.println("Disconnected");
			//}  
			
			//else  {
			//	System.out.println("Connexion interrompue.");
			//}
		}  catch(Exception e)  {
			e.printStackTrace();
			try { ftp.disconnect(); }  catch(Exception e2)  {}
		}
	}*/
 
	// Fonction privée pour récupérer le nom de l'host
	/*private static String getStringFromUser(String prompt) throws IOException
	{
		System.out.print(prompt);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}*/
 
}