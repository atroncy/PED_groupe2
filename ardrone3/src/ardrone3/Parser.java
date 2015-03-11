package ardrone3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import model.NavData;


public class Parser {

		//type
	private final static int DATA = (0x02); 
	private final static int DATA_WITH_ACK = (0x04); 
	
	//project
	private final static int PROJECT_BEBOP = (0x01);
	
	//class
	private final static int CLASS_PILOTING_STATE = (0x04);
	private final static int CLASS_PILOTING_SETTING_STATE = (0x06);
	private final static int CLASS_MEDIA_RECORD_STATE = (0x08);
	private final static int CLASS_SPEED_SETTING_STATE = (0x0c);
	
	//cmd 
	private final static int CMD_FLYING_STATE_CHANGED = (0x01);
	private final static int CMD_SPEED_CHANGED = (0x05);
	private final static int CMD_ALTITUDE_CHANGED = (0x08);
	private final static int CMD_MAX_ALTITUDE_CHANGED = (0x00);
	private final static int CMD_VIDEO_STATE_CHANGED_V2 = (0x03);
	private final static int CMD_MAX_VERTICAL_SPEED_CHANGED = (0x00);
	
	private NavData _navData;
	
	public Parser(NavData navData){
		_navData = navData;
	}
	
	public void parse(byte [] packetAD3){
		
		int packetRemain = packetAD3.length;
		int size = 0, offset = 0, type = 0, project = 0,classs = 0, cmd = 0;
		ByteBuffer sizeDecoder = null, args = null;

		//Parsed all the ArDrone3 message included in the packet and search for ArCommands needed for NavData 
		do{
			type = packetAD3[offset];
			sizeDecoder = ByteBuffer.wrap(packetAD3, offset+3, 4).order(ByteOrder.LITTLE_ENDIAN);
			size = sizeDecoder.getInt();
			switch(type){
			case DATA_WITH_ACK:
				project = packetAD3[offset+7]; 
				switch(project){
				case PROJECT_BEBOP:
					classs = packetAD3[offset+8];
					cmd = packetAD3[offset+9];
					switch(classs){
					case CLASS_PILOTING_STATE:
						switch(cmd){
						case CMD_FLYING_STATE_CHANGED:
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setDroneState(args);
							break;
						case CMD_SPEED_CHANGED:
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setCurrentSpeed(args);							
							break;
						case CMD_ALTITUDE_CHANGED:
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setCurrentAltitude(args);
							break;
						default:
							break;
						}
						break;
					case CLASS_PILOTING_SETTING_STATE:
						if(cmd == CMD_MAX_ALTITUDE_CHANGED){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setAltitudeMax(args);
						}
						break;
					case CLASS_MEDIA_RECORD_STATE:
						if(cmd == CMD_VIDEO_STATE_CHANGED_V2){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setMediaRecordState(args);
						}
						break;
					case CLASS_SPEED_SETTING_STATE:
						if(cmd == CMD_MAX_VERTICAL_SPEED_CHANGED){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setSpeedMax(args);
						}
						break;
					default:
						break;
					}				
				default:
					break;
				}
				break;
			case DATA:
				project = packetAD3[offset+7];
				switch(project){
				case PROJECT_BEBOP:
					classs = packetAD3[offset+8];
					cmd = packetAD3[offset+9];
					switch(classs){
					case CLASS_PILOTING_STATE:
						switch(cmd){
						case CMD_FLYING_STATE_CHANGED:
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setDroneState(args);
							break;
						case CMD_SPEED_CHANGED:
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setCurrentSpeed(args);							
							break;
						case CMD_ALTITUDE_CHANGED:
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setCurrentAltitude(args);
							break;
						default:
							break;
						}
						break;
					case CLASS_PILOTING_SETTING_STATE:
						if(cmd == CMD_MAX_ALTITUDE_CHANGED){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setAltitudeMax(args);
						}
						break;
					case CLASS_MEDIA_RECORD_STATE:
						if(cmd == CMD_VIDEO_STATE_CHANGED_V2){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setMediaRecordState(args);
						}
						break;
					case CLASS_SPEED_SETTING_STATE:
						if(cmd == CMD_MAX_VERTICAL_SPEED_CHANGED){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setSpeedMax(args);
						}
						break;
					default:
						break;
					}
				default:
					break;
				}
				break;
			default:
				System.out.println("I ain't got no type. I got it!");
				break;
			}
			offset = offset + size;
			packetRemain = packetRemain - size;
		}while(packetRemain != 0);
	}
}