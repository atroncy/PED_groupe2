package ardrone3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import model.NavData;


public class Parser {

	//type
	private final static int ACK = 1;
	private final static int DATA = 2;
	private final static int LOW_DELAY = 3;
	private final static int DATA_WITH_ACK = 4;
	
	
	//project
	private final static int PROJECT_BEBOP = 1;
	private final static int PROJECT_COMMON = 0;
	
	//class
	private final static int CLASS_PILOTING_STATE = 4;
	private final static int CLASS_PILOTING_SETTING_STATE = 6;
	private final static int CLASS_MEDIA_RECORD_STATE = 8;
	private final static int CLASS_SPEED_SETTING_STATE = 12;
	private final static int CLASS_CAMERA_STATE = 25;
	
	//cmd 
	private final static int CMD_FLYING_STATE_CHANGED = 1;
	private final static int CMD_SPEED_CHANGED = 5;
	private final static int CMD_ALTITUDE_CHANGED = 8;
	private final static int CMD_MAX_ALTITUDE_CHANGED = 0;
	private final static int CMD_VIDEO_STATE_CHANGED_V2 = 3;
	private final static int CMD_MAX_VERTICAL_SPEED_CHANGED = 0;
	private final static int CMD_ORIENTATION = 0;
	
	private NavData _navData;
	private Controller _ctrl;
	
	public Parser(NavData navData, Controller ctrl){
		_navData = navData;
		_ctrl = ctrl;
	}
	
	public void parse(byte [] packetAD3){
		
		int packetRemain = packetAD3.length;
		int size = 0, offset = 0, type = 0, project = 0,classs = 0, cmd = 0, seq = 0;
		ByteBuffer sizeDecoder = null, args = null;

		//Parse all the ArDrone3 message included in the packet and search for ArCommands needed for NavData 
		do{
			type = packetAD3[offset];
			sizeDecoder = ByteBuffer.wrap(packetAD3, offset+3, 4).order(ByteOrder.LITTLE_ENDIAN);
			size = sizeDecoder.getInt();
			switch(type){
			case ACK:
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
					case CLASS_CAMERA_STATE:
						if(cmd == CMD_ORIENTATION){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setPan(args);
						}
						break;
					default:
						break;
					}
					break;
				case PROJECT_COMMON:
					break;
				default:
					break;
				}
				break;
			case LOW_DELAY:
				break;
			case DATA_WITH_ACK:
				System.out.println("je ack");
				project = packetAD3[offset+7];
				switch(project){
				case PROJECT_BEBOP:
					classs = packetAD3[offset+8];
					cmd = packetAD3[offset+9];
					seq = packetAD3[offset+2];
					switch(classs){
					case CLASS_PILOTING_STATE:
						switch(cmd){
						case CMD_FLYING_STATE_CHANGED:
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setDroneState(args);
							_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
							_ctrl.incSeqAck();
							break;
						case CMD_SPEED_CHANGED:
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setCurrentSpeed(args);
							_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
							_ctrl.incSeqAck();
							break;
						case CMD_ALTITUDE_CHANGED:
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setCurrentAltitude(args);
							_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
							_ctrl.incSeqAck();
							break;
						default:
							_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
							_ctrl.incSeqAck();
							break;
						}
						break;
					case CLASS_PILOTING_SETTING_STATE:
						if(cmd == CMD_MAX_ALTITUDE_CHANGED){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setAltitudeMax(args);
						}
						_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
						_ctrl.incSeqAck();
						break;
					case CLASS_MEDIA_RECORD_STATE:
						if(cmd == CMD_VIDEO_STATE_CHANGED_V2){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setMediaRecordState(args);
						}
						_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
						_ctrl.incSeqAck();
						break;
					case CLASS_SPEED_SETTING_STATE:
						if(cmd == CMD_MAX_VERTICAL_SPEED_CHANGED){
							args = ByteBuffer.wrap(packetAD3,offset+11,size-11);
							_navData.setSpeedMax(args);
						}
						_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
						_ctrl.incSeqAck();
						break;
					default:
						_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
						_ctrl.incSeqAck();
						break;
					}
					break;
				case PROJECT_COMMON:
					seq = packetAD3[offset+2];
					_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
					_ctrl.incSeqAck();
					break;
				default:
					seq = packetAD3[offset+2];
					_ctrl.sendMessage(MessageHandler.ack((byte)seq, _ctrl.getSeqAck()));
					_ctrl.incSeqAck();
					break;
				}
				break;
			default:
				return; //Only way to get out of the loop
			}
			offset = offset + size;
			packetRemain = packetRemain - size;
		}while(packetRemain != 0); //Useful for test
		System.out.println("sortie");
	}
}