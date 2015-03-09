package ardrone3;

public class Command {
	
	
	//project
	private final static byte PROJECT = 1;
	
	//droneClass PILOTING
	private final static byte PILOTING	= 0;
	//command PILOTING
	private final static short FLAT_TRIM	= 0;
	private final static short TAKE_OFF		= 1;
	private final static short PCMD			= 2;
	private final static short LANDING		= 3;
	private final static short EMERGENCY	= 4;

	
	//droneClass CAMERA
	private final static byte CAMERA 	= 1;
	//command CAMERA
	private final static short ORIENTATION = 0;
	//arg CAMERA
	private final static byte PAN = 1; 	/*Pan camera consign for the drone (in degree)
										The value is saturated by the drone.
										Saturation value is sent by the drone
										through CameraSettingsChanged command. */
	//header
	byte _type;
	byte _id;
	byte _seq;
	int _size;
	//body
	byte _project;
	byte _class;	//droneClass
	short _cmd;
	
	static byte seqNum = 0;
	

	/**
	 * Create message
	 * 
	 * 
	 */
	public Command (int type, int id, byte project, byte droneClass, short command){
	
		this._type		= (byte) type;
		this._id		= (byte) id;
		this._seq		= (byte) ((seqNum++)%255);	//Increment the sequence number for each new packet
		this._project	= PROJECT;
		this._class		= droneClass;
		this._cmd 		= command;
		
		//TODO this._size = 
	}
	
	
	/**
	 * Convert a message to a string of bytes
	 * 
	 */
	public byte[] convert_command_to_byte(Command cmd){
		
		byte[] message = new byte[cmd._size];
			
		return message;
	}
	
	public String toString(){
		 return Byte.toString(this._type) + Byte.toString(this._id) + Byte.toString(this._seq) +
				 Integer.toString(this._size) + Byte.toString(this._project) + Byte.toString(this._class) +
				  Short.toString(this._cmd);
	}
	
//	public byte[] commandToByteArray(){
//		//return
//		
//		
//	}
	
	public static byte[] intToByteArray(int value){
	    return new byte[] {
	            (byte)value,
	            (byte)(value >>> 8),
	            (byte)(value >>> 16),
	            (byte)(value >>> 24)};
	}
	
	public static byte[] shortToByteArray(short value){
	    return new byte[] {
	    		(byte)value,
	    		(byte)(value >>> 8)};
	}
	
	public static void main (String[] args){
		Command cmd = new Command(3, 2, PROJECT, PILOTING, TAKE_OFF);
		cmd._size=1012;
		System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(intToByteArray(cmd._size)));
		byte tmp[] = {(byte)cmd._type,(byte)cmd._id,(byte)cmd._seq,(byte)cmd._project, (byte)cmd._class,
				(byte)cmd._cmd};
		System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(tmp));
		System.out.println(cmd.toString());
		
	
	}
	
	

}

	

