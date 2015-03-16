package systeme_de_drone_de_facade;

import java.util.Observable;
import java.util.Observer;

import ardrone3.Controller;
import ardrone3.InvalidArgumentException;
import ardrone3.MessageHandler;
import model.NavData;

public class Automatic extends Thread implements Observer{

	
	private NavData _nav1,_nav2;
	private Controller _ctrl1, _ctrl2;
	private float  _speedMax1, _speedMax2, _userSpeed;
	private double _altitudeMax1, _currentAltitude1, _altitudeMax2, _currentAltitude2, _userAltitude;
	private int _pan1, _pan2, _userPan; 
	private String _droneState1, _mediaRecordState1, _droneState2, _mediaRecordState2;
	
	
	
	public Automatic(NavData nav1, NavData nav2, Controller ctrl1, Controller ctrl2){
		_nav1 = nav1;
		_nav2 = nav2;
		_ctrl1 = ctrl1;
		_ctrl2 = ctrl2;
	}
	
	
	public void userInit(float userSpeed, double userAltitude, int userPan){
		_userSpeed = userSpeed;
		_userAltitude = userAltitude;
		_userPan = userPan;
		_nav1.addObserver(this);
		_nav2.addObserver(this);
	}
	
	
	@Override
	public void run() {
		
		try {
			
			this.phaseTakeoff();
			
			this.phaseConfig();
			
			this.phaseMediaStarted();
			
			this.phaseFlying();
			
			this.phaseMediaStoped();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}



	private int phaseTakeoff() throws InterruptedException{
		
		_ctrl1.sendMessage(MessageHandler.takeoff(_ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(MessageHandler.takeoff(_ctrl2.getSeqDataAck()));
		_ctrl2.incSeqDataAck();
		
		Automatic.sleep(1000);
		
		boolean check = false;
		do{
			check = checkTakeOffDone();
		}while(!check);
		
		return 1;
	}
	
	private boolean checkTakeOffDone() throws InterruptedException {
		if(_droneState1.equals("hovering") && _droneState2.equals("hovering"))
			return true;
		else
			this.wait();
		
		return false;
	}

	private int phaseConfig() throws InvalidArgumentException, InterruptedException{
		_ctrl1.sendMessage(MessageHandler.cameraPan((byte) _userPan, _ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(MessageHandler.cameraPan((byte) - _userPan, _ctrl2.getSeqDataAck()));
		_ctrl2.incSeqDataAck();
		
		
//		speed			
//		_ctrl1.sendMessage(MessageHandler.cameraPan((byte) _userPan, _ctrl1.getSeqDataAck()));
//		_ctrl1.incSeqDataAck();
//		_ctrl2.sendMessage(MessageHandler.cameraPan((byte) - _userPan, _ctrl2.getSeqDataAck()));
//		_ctrl2.incSeqDataAck();
//		altitude
//		_ctrl1.sendMessage(MessageHandler.cameraPan((byte) _userPan, _ctrl1.getSeqDataAck()));
//		_ctrl1.incSeqDataAck();
//		_ctrl2.sendMessage(MessageHandler.cameraPan((byte) - _userPan, _ctrl2.getSeqDataAck()));
//		_ctrl2.incSeqDataAck();
		
		
		boolean check = false;
		do{
			check = checkConfigDone();
		}while(!check);
		
		return 1;
	}
	
	private boolean checkConfigDone() throws InterruptedException {
		if(_pan1 == _userPan && _pan2 == - _userPan 
			&& _speedMax1 == _userSpeed && _speedMax2 == _userSpeed
			&& _altitudeMax1 == _userAltitude && _altitudeMax2 == _userAltitude)
			return true;
		else
			this.wait();
		
		return false;
	}


	private int phaseMediaStarted() throws InterruptedException {
		_ctrl1.sendMessage(MessageHandler.recordStart(_ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(MessageHandler.recordStart(_ctrl2.getSeqDataAck()));
		_ctrl2.incSeqDataAck();
		
		boolean check = false;
		do{
			check = checkMediaStarted();
		}while(!check);
		
		return 1;
	}

	
	private boolean checkMediaStarted() throws InterruptedException {
		if(_mediaRecordState1.equals("started") && _mediaRecordState2.equals("started"))
			return true;
		else
			this.wait();
		
		return false;
	}

//	aint that good
	private void phaseFlying() throws InvalidArgumentException{
		do{
			_ctrl1.sendMessage(MessageHandler.gaz((byte) 50, _ctrl1.getSeqData()));
			_ctrl1.incSeqData();
			_ctrl2.sendMessage(MessageHandler.gaz((byte) 50, _ctrl2.getSeqData()));
			_ctrl2.incSeqData();
		}while(_currentAltitude1 < _userAltitude && _currentAltitude2 < _userAltitude);
		
	}


	private int phaseMediaStoped() throws InterruptedException {
		_ctrl1.sendMessage(MessageHandler.recordStop(_ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(MessageHandler.recordStop(_ctrl2.getSeqDataAck()));
		_ctrl2.incSeqDataAck();
		
		boolean check = false;
		do{
			check = checkMediaStoped();
		}while(!check);
		
		return 1;
	}

	
	private boolean checkMediaStoped() throws InterruptedException {
		if(_mediaRecordState1.equals("stoped") && _mediaRecordState2.equals("stoped"))
			return true;
		else
			this.wait();
		
		return false;
	}

	@Override
	public void update(Observable o, Object arg) {
		_altitudeMax1 = _nav1.getAltitudeMax();
		_pan1 =_nav1.getPan();
		_speedMax1 = _nav1.getSpeedMax();
		_currentAltitude1 = _nav1.getCurrentAltitude();
		_droneState1 = _nav1.getDroneState();
		_mediaRecordState1 = _nav1.getMediaRecordState();
		_altitudeMax2 = _nav2.getAltitudeMax();
		_pan2 =_nav2.getPan();
		_speedMax2 = _nav2.getSpeedMax();
		_currentAltitude2 = _nav2.getCurrentAltitude();
		_droneState2 = _nav2.getDroneState();
		_mediaRecordState2 = _nav2.getMediaRecordState();
		this.notify();
	}
	
}
