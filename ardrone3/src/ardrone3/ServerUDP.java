package ardrone3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerUDP extends Thread{

	private Parser _parser;
	private int _UDPport;
	
	public ServerUDP(Parser parser,int UDPport){
		_parser = parser;
		_UDPport = UDPport;
	}

	@Override
	public void run() {
		try {
			DatagramSocket serverSocket = new DatagramSocket(_UDPport);
		
			while(true){
				byte[] receiveData = new byte[1512]; //never forget
				DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
				serverSocket.receive(receivePacket);
				
				byte packetAD3[] = receivePacket.getData();
				System.out.println(receiveData[24]);
				_parser.parse(packetAD3);
			
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	
	
}
