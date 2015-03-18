package ardrone3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.ConsoleModel;

public class MessageHandler {
	

	//***************************************************************************
	//Constants to use to create commands (see the appropriate functions below)
	
	//type
	private final static byte TYPE_ACK 			= 1;
	private final static byte TYPE_DATA 		= 2;
	private final static byte TYPE_DATA_WITH_ACK= 4;
	
	//****** project BEBOP ******
	private final static byte BEBOP 		= 1;
	
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
	
	
	//droneClass MEDIARECORD
	private final static byte MEDIA_RECORD = 7;
	//command MEDIARECORD
	private final static byte VIDEO = 3;
	//arg MEDIARECORD
	private final static int REC_STOP	= 0;
	private final static int REC_START	= 1;
	
	
	//To set max altitude
	//droneClass PILOTING_SETTINGS
	private final static byte PILOTING_SETTINGS	= 2;
	//command PILOTING_SETTINGS
	private final static byte MAX_ALTITUDE		= 0;

	//To set max vertical speed
	//droneClass SPEED_SETTINGS
	private final static byte SPEED_SETTINGS	= 11;
	//command SPEED_SETTINGS
	private final static byte MAX_VERTICAL_SPEED= 0;
	
	
	//****** project COMMON ******
	private final static byte COMMON_PROJECT	= 0;
	private final static byte COMMON_CLASS 		= 4;
	private final static byte SET_CURRENT_DATE	= 1;
	private final static byte SET_CURRENT_TIME	= 2;
	
	//***************************************************************************
	
	private ConsoleModel _cm;
	
	public MessageHandler(ConsoleModel cm){
		_cm = cm;
	}
	
	
	//*************************************
	//*				 Commands 			  *
	//*************************************
	
	/**
	 * Create a "take-off" command.
	 * @return
	 */
	public Command takeoff(byte seq){
		Command cmd_takeoff = new Command(TYPE_DATA_WITH_ACK, 11, seq, BEBOP, PILOTING, TAKE_OFF);
		cmd_takeoff._size = 11;
		
		_cm.addText("take off");
		
		return cmd_takeoff;
	}
	
	/**
	 * Create a "landing" command.
	 * @return
	 */
	public Command landing(byte seq){
		Command cmd_landing = new Command(TYPE_DATA_WITH_ACK, 11, seq, BEBOP, PILOTING, LANDING);
		cmd_landing._size = 11;
		_cm.addText("landing");
		
		return cmd_landing;
	}
	
	/**
	 * Create an "emergency" command.
	 * @return
	 */
	public Command emergency(byte seq){
		Command cmd_emergency = new Command(TYPE_DATA_WITH_ACK, 12, seq, BEBOP, PILOTING, EMERGENCY);
		cmd_emergency._size = 11;
		_cm.addText("emergency");
		
		return cmd_emergency;
	}
	
	/**
	 * Create a "flat trim" command.
	 * @return
	 */
	public Command flattrim(byte seq){
		Command cmd_flattrim = new Command(TYPE_DATA_WITH_ACK, 11, seq, BEBOP, PILOTING, FLAT_TRIM);
		cmd_flattrim._size = 11;
		_cm.addText("flat trim");
		
		return cmd_flattrim;
	}
		
	
	/**
	 * Gaz consign for the drone [-100;100].
	 * Type="i8"
	 * @return
	 * @throws InvalidArgumentException 
	 */
	public Command gaz(byte value, byte seq) throws InvalidArgumentException{
		Command cmd_gaz = new Command(TYPE_DATA, 10, seq, BEBOP, PILOTING, PCMD);
		cmd_gaz._size = 20;
		_cm.addText("gaz");
		cmd_gaz._arg = new byte[cmd_gaz._size - 11];
		if (value<-100 || value>100)
			throw new InvalidArgumentException("Value has to be [-100;100]");
		else
			cmd_gaz._arg[4] = value;
		
		return cmd_gaz;
	}
	
	/**
	 * Change the camera's orientation. Value is in degree.
	 * Negative values to turn the camera on the left, positive to turn on the right.
	 * @param value in degree
	 * @param seq sequence number
	 * @return
	 * @throws InvalidArgumentException 
	 */
	public Command cameraPan(byte value, byte seq) throws InvalidArgumentException{
		Command cmd_camera = new Command(TYPE_DATA_WITH_ACK, 11, seq, BEBOP, CAMERA, ORIENTATION);
		cmd_camera._size = 13;
		_cm.addText("camera orientation");
		cmd_camera._arg = new byte[cmd_camera._size - 11];
		if (value<-100 || value>100)
			throw new InvalidArgumentException("Value has to be [-100;100] in degree");
		else
			cmd_camera._arg[1] = value;
		
		return cmd_camera;
	}
	
	/**
	 * The application FreeFlight3 seems to use the depreciated method, so do we... note the mass storage id = 0.
	 * @param seq sequence number
	 * @return
	 */
	public Command recordStart(byte seq){
		Command cmd_rec = new Command(TYPE_DATA_WITH_ACK, 11, seq, BEBOP, MEDIA_RECORD, VIDEO);
		cmd_rec._size = 16;
		_cm.addText("start recording");
		byte [] tmp = Command.intToByteArray(REC_START);
		cmd_rec._arg = new byte [cmd_rec._size - 11];
		for (int i = 0; i < 4; i++) {
			cmd_rec._arg[i] = tmp[i];
		}
		cmd_rec._arg[4] = 0; // Mass storage id to record, we don't care.
		
		return cmd_rec;
	}
	

	/**
	 * The application FreeFlight3 seems to use the depreciated method, so do we... note the mass storage id = 0.
	 * @param seq sequence number
	 * @return
	 */
	public Command recordStop(byte seq){
		Command cmd_rec = new Command(TYPE_DATA_WITH_ACK, 11, seq, BEBOP, MEDIA_RECORD, VIDEO);
		cmd_rec._size = 16;
		_cm.addText("stop recording");
		byte [] tmp = Command.intToByteArray(REC_STOP);
		cmd_rec._arg = new byte [cmd_rec._size - 11];
		for (int i = 0; i < 4; i++) {
			cmd_rec._arg[i] = tmp[i];
		}
		cmd_rec._arg[4] = 0; // Mass storage id to record, we don't care.
		
		return cmd_rec;
	}
	
	
	/**
	 * Set the current date, apparently needed to record videos.
	 * @param seq sequence number
	 * @return
	 */
	public Command setCurrentDate(byte seq){
		Command cmd_date = new Command(TYPE_DATA_WITH_ACK, 11, seq, COMMON_PROJECT, COMMON_CLASS, SET_CURRENT_DATE);
		cmd_date._size = 22;
		cmd_date._arg = new byte [cmd_date._size - 11];
		SimpleDateFormat simpleFormat = (SimpleDateFormat) DateFormat.getDateInstance();
	    simpleFormat.applyPattern("yyyy-MM-dd");
	    Date date = new Date();
	    byte[] tmp = simpleFormat.format(date).getBytes();
	    for (int i = 0; i < 10; i++) {
			cmd_date._arg[i] = tmp[i];
		}
	    _cm.addText("set date");
	    
		return cmd_date;
	}

	
	/**
	 * Set the current time, apparently needed to record videos.
	 * @param seq sequence number
	 * @return
	 */
	public Command setCurrentTime(byte seq){
		Command cmd_time = new Command(TYPE_DATA_WITH_ACK, 11, seq, COMMON_PROJECT, COMMON_CLASS, SET_CURRENT_TIME);
		cmd_time._size = 24;
		cmd_time._arg = new byte[cmd_time._size - 11];
		SimpleDateFormat simpleFormat = (SimpleDateFormat) DateFormat.getDateInstance();
	    simpleFormat.applyPattern("'T'HHmmssXX");
	    Date date = new Date();
	    byte[] tmp = simpleFormat.format(date).getBytes();
	    for (int i = 0; i < 12; i++) {
			cmd_time._arg[i] = tmp[i];
		}
	    _cm.addText("set time");
	    
		return cmd_time;
	}
	
	/**
	 * Used to create acknowledgment messages.
	 * @param arg sequence number of the packet we want to acknowledge
	 * @param seq our sequence number
	 * @return
	 */
	public Command ack(byte arg, byte seq){
		Command cmd_ack = new Command(TYPE_ACK, 254, seq);
		cmd_ack._size = 8;
		cmd_ack._arg = new byte[1];
		cmd_ack._arg[0] = arg;
		
		return cmd_ack;
	}
	
	/**
	 * Set the maximal altitude, in meters.
	 * @param maxAlt altitude max in m.
	 * @param seq sequence number
	 * @return
	 */
	public Command setMaxAltitude(float maxAlt, byte seq){
		Command cmd_max = new Command(TYPE_DATA_WITH_ACK, 11, seq, BEBOP, PILOTING_SETTINGS, MAX_ALTITUDE);
		cmd_max._size = 15;
		cmd_max._arg = new byte[cmd_max._size - 11];
		cmd_max._arg = Command.floatToIeee754(maxAlt);
		
		return cmd_max;
	}
	
	/**
	 * Set the maximal vertical speed.
	 * @param maxSpeed max vertical speed in m/s.
	 * @param seq sequence number
	 * @return
	 */
	public Command setMaxSpeed(float maxSpeed, byte seq){
		Command cmd_max = new Command(TYPE_DATA_WITH_ACK, 11, seq, BEBOP, SPEED_SETTINGS, MAX_VERTICAL_SPEED);
		cmd_max._size = 15;
		cmd_max._arg = new byte[cmd_max._size - 11];
		cmd_max._arg = Command.floatToIeee754(maxSpeed);
		
		return cmd_max;
	}
	
}
