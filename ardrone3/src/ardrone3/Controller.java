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

	//Keep track of the different sequence number (one sequence num per type)
	private byte _seqData 		= 0;
	private byte _seqDataAck	= 0;
	private byte _seqAck		= 0;

	private DatagramSocket _socketUDP;
	private DatagramPacket _packet;


	public Controller(String address, int port) throws UnknownHostException{
		this._ip = InetAddress.getByName(address);
		this._port = port;
	}

	/**
	 * Initiate the TCP connection to negotiate the ports in use, and initialize the UDP socket.
	 * @param controlerName
	 * @return
	 */
	public int init(String controlerName){
		try {
			//Establishing TCP connection
			Socket socketTCP = new Socket (this._ip, 44444);

			PrintWriter out = new PrintWriter (socketTCP.getOutputStream(), true);
			out.print ("{'controller_name':'"+controlerName+"','controller_type':'"+
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

	
	/**
	 * Convert a Command object into a byte array and send it as a UDP datagram.
	 * @param cmd The command to send.
	 */
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

	
	//Getters & Setters to manage the sequence numbers.
	public synchronized byte getSeqData() {
		return _seqData;
	}

	public synchronized void incSeqData() {
		this._seqData++;
	}

	public synchronized byte getSeqDataAck() {
		return _seqDataAck;
	}

	public synchronized void incSeqDataAck() {
		this._seqDataAck++;
	}

	public synchronized byte getSeqAck() {
		return _seqAck;
	}

	public synchronized void incSeqAck() {
		this._seqAck++;
	}



}