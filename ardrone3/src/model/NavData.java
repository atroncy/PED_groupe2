package model;

import java.util.Observable;

public class NavData extends Observable{

	private float  _Speed, _currentSpeed;
	private double _AltitudeMax, _currentAltitude;
	private int _Pan; // DUNNNOOOO
	private String _droneState;
	
	public NavData(){
		
	}
			
	public void setSpeed(float ms){
		_Speed = ms;
	}
	
	public float getSpeed(){
		return _Speed;
	}
		
	public void setCurrentSpeed(float ms){
		_currentSpeed = ms;
	}
	
	public float getCurrentSpeed(){
		return _currentSpeed;
	}
		
	public void setCurrentAltitude(double meter){
		_currentAltitude = meter;
	}
	
	public double getCurrentAltitude(){
		return _currentAltitude;
	}
	
	public void setAltitudeMax(double meter){
		_AltitudeMax = meter;
	}
	
	public double getAltitudeMax(){
		return _AltitudeMax;
	}
		
	public void setDroneState(int flag){
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
	}
	
	public String getDroneState(){
		return _droneState;
	}
	
	private double ieee754ToDouble(byte holyshit[]){
		double result = 0;
		
		return result;
	}
	
	private float ieee754ToFloat(byte omg[]){
		float result = 0;
		
		return result;
	}
	
}
