package ardrone3;

import javax.xml.bind.DatatypeConverter;

public class Command {
	
	
	//type
	private final static byte TYPE_ACK 					= 1;
	private final static byte TYPE_DATA 				= 2;
	private final static byte TYPE_DATA_LOW_LATTENCY 	= 3;
	private final static byte TYPE_DATA_WITH_ACK		= 4;
	
	//project
	private final static byte PROJECT 		= 1;
	
	//droneClass PILOTING
	private final static byte PILOTING		= 0;
	//command PILOTING
	private final static short FLAT_TRIM	= 0;
	private final static short TAKE_OFF		= 1;
	private final static short PCMD			= 2;
	private final static short LANDING		= 3;
	private final static short EMERGENCY	= 4;

	
	//droneClass CAMERA
	private final static byte CAMERA 		= 1;
	//command CAMERA
	private final static short ORIENTATION 	= 0;
	//arg CAMERA
	private final static byte PAN 			= 1;
	/*Pan camera consign for the drone (in degree).The value is saturated by the drone.
	Saturation value is sent by the drone through CameraSettingsChanged command. */
	
	
	//Attributes of a command to be sent to the drone:
	//header
	byte _type;
	byte _id;
	byte _seq;
	int _size;
	//body
	byte _project;
	byte _class;	//droneClass
	short _cmd;
	//TODO args
	
	//Sequence number of the command, initiate at 0 and increase by 1 at each new command, modulo 255.
	static byte seqNum = 0;
	

	/**
	 * Create a new command to be sent. The different arguments depends on the type of command
	 * one does want to send. Please use the appropriate commands below to create the correct command.
	 * @param type
	 * @param id
	 * @param project
	 * @param droneClass
	 * @param command
	 */
	public Command (int type, int id, byte project, byte droneClass, short command){
		this._type		= (byte) type;
		this._id		= (byte) id;
		this._seq		= (byte) ((seqNum++)%255);	//Increment the sequence number for each new packet
		this._project	= project;
		this._class		= droneClass;
		this._cmd 		= command;
		
	}
	
	
	/**
	 * Return the command in a human readable format.
	 */
	public String toString(){
		byte[] arr = this.commandToByteArray();
		return DatatypeConverter.printHexBinary(arr);
	}
	
	
	/**
	 * Convert an object Command into a byte of array.
	 * @param command
	 * @return
	 */
	private byte[] commandToByteArray(){
		byte[] array = new byte[this._size];
		array[0] = this._type;
		array[1] = this._id;
		array[2] = this._seq;
		byte[] size_array = intToByteArray(this._size);
		for (int i = 0, j = 3 ; i < 4 ; i++, j++) {
			array[j] = size_array[i];
		}
		array[7] = this._project;
		array[8] = this._class;
		byte[] cmd_array = shortToByteArray(this._cmd);
		array[9] =  cmd_array[0];
		array[10] = cmd_array[1];
		//TODO args
				
		return array;
	}
	
	/**
	 * Convert an integer into a byte array in little endian format.
	 * @param value
	 * @return
	 */
	private static byte[] intToByteArray(int value){
	    return new byte[] {
	            (byte)value,
	            (byte)(value >>> 8),
	            (byte)(value >>> 16),
	            (byte)(value >>> 24)};
	}
	
	/**
	 * Convert a short into a byte array in little endian format.
	 * @param value
	 * @return
	 */
	private static byte[] shortToByteArray(short value){
	    return new byte[] {
	    		(byte)value,
	    		(byte)(value >>> 8)};
	}
	
		
	//*************************************
	//*				 Commands 			  *
	//*************************************
	
	/**
	 * Create a "take-off" command.
	 * @return
	 */
	public static Command takeoff(){
		Command cmd_takeoff = new Command(TYPE_DATA_WITH_ACK, 11, PROJECT, PILOTING, TAKE_OFF);
		cmd_takeoff._size = 11;
		return cmd_takeoff;
	}
	
	/**
	 * Create a "landing" command.
	 * @return
	 */
	public static Command landing(){
		Command cmd_landing = new Command(TYPE_DATA_WITH_ACK, 11, PROJECT, PILOTING, LANDING);
		cmd_landing._size = 11;
		return cmd_landing;
	}
	
	/**
	 * Create an "emergency" command.
	 * @return
	 */
	public static Command emergency(){
		Command cmd_emergency = new Command(TYPE_DATA_WITH_ACK, 11, PROJECT, PILOTING, EMERGENCY);
		cmd_emergency._size = 11; // not sure about the size
		return cmd_emergency;
	}
	
	/**
	 * Create a "flat trim" command.
	 * @return
	 */
	public static Command flattrim(){
		Command cmd_flattrim = new Command(TYPE_DATA_WITH_ACK, 11, PROJECT, PILOTING, FLAT_TRIM);
		cmd_flattrim._size = 11;
		return cmd_flattrim;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	/*TODO
	public static Command up(){
		Command cmd_up = new Command(TYPE_DATA_WITH_ACK, 11, PROJECT, PILOTING, PCMD);
		cmd_up._size = 28;
		return cmd_up;
	}
	*/

	/**
	 * 
	 * @return
	 */
	/*TODO
	public static Command down(){
		Command cmd_down = new Command(TYPE_DATA_WITH_ACK, 11, PROJECT, PILOTING, PCMD);
		cmd_down._size = 28;
		return cmd_down;
	}
	*/
	
	
	//********************** TEST ******************************
	public static void main (String[] args){
		
		/*
		System.out.println(DatatypeConverter.printHexBinary(intToByteArray(cmd._size)));
		byte tmp[] = {(byte)cmd._type,(byte)cmd._id,(byte)cmd._seq,(byte)cmd._project, (byte)cmd._class,
				(byte)cmd._cmd};
		System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(tmp));
		System.out.println(cmd.toString());
		*/
		Command cmd = takeoff();
		System.out.println(cmd);
		Command cmdland = landing();
		System.out.println(cmdland);
	
	}
	
}

	

