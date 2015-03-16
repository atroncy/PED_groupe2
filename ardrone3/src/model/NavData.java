package model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Observable;

public class NavData extends Observable{

	private float  _speedMax, _currentSpeed;
	private double _altitudeMax, _currentAltitude;
	private int _pan; 
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
			
	public void setSpeedMax(ByteBuffer args){
		_speedMax = this.ieee754ToFloat(args);
		this.triggerChange();
	}
	
	public float getSpeedMax(){
		return _speedMax;
	}
		
	public void setCurrentSpeed(ByteBuffer args){
		args.position(11+8);
		_currentSpeed = this.ieee754ToFloat(args);
		this.triggerChange();
	}
	
	public float getCurrentSpeed(){
		return _currentSpeed;
	}
		
	public void setCurrentAltitude(ByteBuffer args){
		_currentAltitude = this.ieee754ToDouble(args);
		this.triggerChange();
	}
	
	public double getCurrentAltitude(){
		return _currentAltitude;
	}
	
	public void setAltitudeMax(ByteBuffer args){
		_altitudeMax = this.ieee754ToDouble(args);
		this.triggerChange();
	}
	
	public double getAltitudeMax(){
		return _altitudeMax;
	}
		
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
	
	public void setMediaRecordState(ByteBuffer args){
		int flag = this.enumToInt(args);
		switch(flag){
		case 0:
			_mediaRecordState = "stopped";
			break;
		case 1:
			_mediaRecordState = "stopped";
			break;
		case 2:
			_mediaRecordState = "stopped";
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
	
	public void setPan(ByteBuffer args){
		args.position(11+1);
		_pan = this.byteToi8(args);
		this.triggerChange();
	}
	
	public int getPan(){
		return _pan;
	}
	
	private double ieee754ToDouble(ByteBuffer args){
		long holder = 0;
		args.order(ByteOrder.LITTLE_ENDIAN);
		holder = args.getLong();
		return Double.longBitsToDouble(holder);
	}
	
	private float ieee754ToFloat(ByteBuffer args){
		int holder = 0;
		args.order(ByteOrder.LITTLE_ENDIAN);
		holder = args.getInt();
		return Float.intBitsToFloat(holder);
	}
	
	private int enumToInt(ByteBuffer args){
		args.order(ByteOrder.LITTLE_ENDIAN);
		return args.getInt();
	}
	
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
