package ardrone3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {

	private InetAddress _ip;
	private int _port;

	private byte _seqData 		= 0;
	private byte _seqDataAck		= 0;

	private DatagramSocket _socketUDP;
	private DatagramPacket _packet;


	public Controller(String address, int port) throws UnknownHostException{
		this._ip = InetAddress.getByName(address);
		this._port = port;
	}

	public int init(String controlerName){
		try {
			//Establishing TCP connection
			Socket socketTCP = new Socket (this._ip, 44444);

			PrintWriter out = new PrintWriter (socketTCP.getOutputStream(), true);
			out.println ("{'controller_name':'"+controlerName+"','controller_type':'"+
					controlerName+"','d2c_port':"+this._port+"}");

			Thread.sleep(1000);
			out.close ();
			socketTCP.close ();
			
			//UDP connection
			_socketUDP = new DatagramSocket();
			
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void sendMessage(Command cmd){
		byte[] cmdToSend = cmd.commandToByteArray();
		_packet = new DatagramPacket(cmdToSend, cmdToSend.length, this._ip, 54321);
		try {
			_socketUDP.send(_packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte getSeqData() {
		return _seqData;
	}

	public void incSeqData() {
		this._seqData++;
	}

	public byte getSeqDataAck() {
		return _seqDataAck;
	}

	public void incSeqDataAck() {
		this._seqDataAck++;
	}




}