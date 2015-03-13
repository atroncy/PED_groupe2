package ardrone3;

public class MessageHandler {
	

	//***************************************************************************
	//Constants to use to create commands (see the appropriate functions below)
	
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
	
	
	//droneClass MEDIARECORD
	private final static byte MEDIA_RECORD = 7;
	//command MEDIARECORD
	private final static byte VIDEO = 3;
	//arg MEDIARECORD
	private final static int REC_STOP	= 0;
	private final static int REC_START	= 1;
	
	
	//***************************************************************************

	//Message management in the ConsoleModel
	//ConsoleModel cm;
	
	
	//*************************************
	//*				 Commands 			  *
	//*************************************
	
	/**
	 * Create a "take-off" command.
	 * @return
	 */
	public static Command takeoff(byte seq){
		Command cmd_takeoff = new Command(TYPE_DATA_WITH_ACK, 11, seq, PROJECT, PILOTING, TAKE_OFF);
		cmd_takeoff._size = 11;
		
		//cm.addText("take off");
		return cmd_takeoff;
	}
	
	/**
	 * Create a "landing" command.
	 * @return
	 */
	public static Command landing(byte seq){
		Command cmd_landing = new Command(TYPE_DATA_WITH_ACK, 11, seq, PROJECT, PILOTING, LANDING);
		cmd_landing._size = 11;
		//cm.addText("landing");
		return cmd_landing;
	}
	
	/**
	 * Create an "emergency" command.
	 * @return
	 */
	public static Command emergency(byte seq){
		Command cmd_emergency = new Command(TYPE_DATA_WITH_ACK, 12, seq, PROJECT, PILOTING, EMERGENCY);
		cmd_emergency._size = 11;
		//cm.addText("emergency");
		return cmd_emergency;
	}
	
	/**
	 * Create a "flat trim" command.
	 * @return
	 */
	public static Command flattrim(byte seq){
		Command cmd_flattrim = new Command(TYPE_DATA_WITH_ACK, 11, seq, PROJECT, PILOTING, FLAT_TRIM);
		cmd_flattrim._size = 11;
		//cm.addText("flat trim");
		return cmd_flattrim;
	}
		
	
	/**
	 * Gaz consign for the drone [-100;100].
	 * Type="i8"
	 * @return
	 * @throws InvalidArgumentException 
	 */
	public static Command gaz(byte value, byte seq) throws InvalidArgumentException{
		Command cmd_gaz = new Command(TYPE_DATA, 10, seq, PROJECT, PILOTING, PCMD);
		cmd_gaz._size = 20;
		//cm.addText("gaz");
		cmd_gaz._arg = new byte[cmd_gaz._size - 11];
		if (value<-100 || value>100)
			throw new InvalidArgumentException("Value has to be [-100;100]");
		else
			cmd_gaz._arg[4] = value;
		return cmd_gaz;
	}
	
	/**
	 * Change the camera's orientation. Value is in degree.
	 * @param value
	 * @param seq
	 * @return
	 * @throws InvalidArgumentException 
	 */
	public static Command cameraPan(byte value, byte seq) throws InvalidArgumentException{
		Command cmd_camera = new Command(TYPE_DATA_WITH_ACK, 11, seq, PROJECT, CAMERA, ORIENTATION);
		cmd_camera._size = 13;
		//cm.addText("camera orientation");
		cmd_camera._arg = new byte[cmd_camera._size - 11];
		if (value<-100 || value>100)
			throw new InvalidArgumentException("Value has to be [-100;100] in degree");
		else
			cmd_camera._arg[1] = value;
		return cmd_camera;
	}
	
	/**
	 * 
	 * @param seq
	 * @return
	 */
	public static Command recordStart(byte seq){
		Command cmd_rec = new Command(TYPE_DATA_WITH_ACK, 11, seq, PROJECT, MEDIA_RECORD, VIDEO);
		cmd_rec._size = 15;
		//cm.addtext("start recording");
		cmd_rec._arg = Command.intToByteArray(REC_START);
		return cmd_rec;
	
	}
	
	/**
	 * 
	 * @param seq
	 * @return
	 */
	public static Command recordStop(byte seq){
		Command cmd_rec = new Command(TYPE_DATA_WITH_ACK, 11, seq, PROJECT, MEDIA_RECORD, VIDEO);
		cmd_rec._size = 15;
		//cm.addtext("stop recording");
		cmd_rec._arg = Command.intToByteArray(REC_STOP);
		return cmd_rec;
			
	}
	
	
	
	//TODO set vitesse + altitude
	
	
}
