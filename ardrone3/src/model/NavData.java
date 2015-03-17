package model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Observable;

public class NavData extends Observable{

	private float  _speedMax, _currentSpeed;
	private double _altitudeMax, _currentAltitude;
	private int _pan; // DUNNNOOOO
	private String _droneState, _mediaRecordState;
	
	
	public NavData(){
		_speedMax = -1;
		_currentSpeed = -1;
		_altitudeMax = -1;
		_pan = -1;
		_currentAltitude = -1;
		_droneState = "unknown";
		_mediaRecordState = "unknown";
	}
	
	/**
	 * Parse the arguments of the seted max vertical speed
	 * @param args
	 */
	public void setSpeedMax(ByteBuffer args){
		_speedMax = this.ieee754ToFloat(args);
		this.triggerChange();
	}
	
	public float getSpeedMax(){
		return _speedMax;
	}
	/**
	 * Parse the arguments of the current speed of the drone and 
	 * search for the vertical one
	 * @param args
	 */
	public void setCurrentSpeed(ByteBuffer args){
		args.position(11+8);
		_currentSpeed = this.ieee754ToFloat(args);
		this.triggerChange();
	}
	
	public float getCurrentSpeed(){
		return _currentSpeed;
	}
	
	/**
	 * Parse the arguments of the current altitude
	 * @param args
	 */
	public void setCurrentAltitude(ByteBuffer args){
		_currentAltitude = this.ieee754ToDouble(args);
		this.triggerChange();
	}
	
	public double getCurrentAltitude(){
		return _currentAltitude;
	}
	
	/**
	 * Parse the arguments of the setted max altitude
	 * @param args
	 */
	public void setAltitudeMax(ByteBuffer args){
		_altitudeMax = this.ieee754ToDouble(args);
		this.triggerChange();
	}
	
	public double getAltitudeMax(){
		return _altitudeMax;
	}
	
	/**
	 * Parse the arguments of the drone states
	 * @param args
	 */
	public void setDroneState(ByteBuffer args){
		int flag = this.enumToInt(args);
		switch(flag){
		case 0:
			_droneState = "landed";
			break;
		case 1:
			_droneState = "takingoff";
			break;
		case 2:
			_droneState = "hovering";
			break;
		case 3:
			_droneState = "flying";
			break;
		case 4:
			_droneState = "landing";
			break;
		case 5:
			_droneState = "emergency";
			break;
		default:
			_droneState = "unknown";
			break;
		}
		this.triggerChange();
	}
	
	public String getDroneState(){
		return _droneState;
	}
	
	/**
	 * Parse the arguments of the media recording states
	 * @param args
	 */
	public void setMediaRecordState(ByteBuffer args){
		int flag = this.enumToInt(args);
		switch(flag){
		case 0:
			_mediaRecordState = "stopped";
			break;
		case 1:
			_mediaRecordState = "started";
			break;
		case 2:
			_mediaRecordState = "Not Available";
			break;	
		default:
			_mediaRecordState = "unknown";
			break;
		}
		this.triggerChange();
	}
	
	public String getMediaRecordState(){
		return _mediaRecordState;
	}
	
	/**
	 * Parse the arguments of the current pan angle
	 * @param args
	 */
	public void setPan(ByteBuffer args){
		args.position(11+1);
		_pan = this.byteToi8(args);
		this.triggerChange();
	}
	
	public int getPan(){
		return _pan;
	}
	
	/**
	 * This function take a ByteBuffer set his order to little_endian
	 * and cast the ieee754 arguments to a double
	 * @param args
	 * @return 
	 */
	private double ieee754ToDouble(ByteBuffer args){
		long holder = 0;
		args.order(ByteOrder.LITTLE_ENDIAN);
		holder = args.getLong();
		return Double.longBitsToDouble(holder);
	}
	
	/**
	 * This function take a ByteBuffer set his order to little_endian
	 * and cast the ieee754 arguments to a float		
	 * @param args
	 * @return
	 */
	private float ieee754ToFloat(ByteBuffer args){
		int holder = 0;
		args.order(ByteOrder.LITTLE_ENDIAN);
		holder = args.getInt();
		return Float.intBitsToFloat(holder);
	}
	
	
	/**
	 * This function take a ByteBuffer set his order to little_endian
	 * @param args
	 * @return
	 */
	private int enumToInt(ByteBuffer args){
		args.order(ByteOrder.LITTLE_ENDIAN);
		return args.getInt();
	}
	
	/**
	 * This functions take a ByteBuffer set his order to little_endian
	 * and cast the byte to a int
	 * @param args
	 * @return
	 */
	private int byteToi8(ByteBuffer args){
		int holder = 0;
		args.order(ByteOrder.LITTLE_ENDIAN);
		holder = args.get();
		return holder;
	}
	
	//TODO private later or not ?
	public void triggerChange(){
		this.setChanged();
		this.notifyObservers();
	}
}
