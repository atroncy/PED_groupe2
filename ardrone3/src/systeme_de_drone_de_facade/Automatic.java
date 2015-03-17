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
	private MessageHandler _mh1, _mh2;
	
	
	
	public Automatic(NavData nav1, NavData nav2, Controller ctrl1, Controller ctrl2, MessageHandler mh1, MessageHandler mh2){
		_nav1 = nav1;
		_nav2 = nav2;
		_ctrl1 = ctrl1;
		_ctrl2 = ctrl2;
		_mh1 = mh1;
		_mh2 = mh2;
	}
	
	
	public void userInit(float userSpeed, double userAltitude, int userPan){
		_userSpeed = userSpeed;
		_userAltitude = userAltitude;
		_userPan = userPan;
		_nav1.addObserver(this);
		_nav2.addObserver(this);
	}
	
	/**
	 * Start the automatic process of the recording of the facade
	 */
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


	/**
	 * Start the takeoff phase and check if the drone is hovering
	 * @return
	 * @throws InterruptedException
	 */
	private int phaseTakeoff() throws InterruptedException{
		
		_ctrl1.sendMessage(_mh1.takeoff(_ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(_mh2.takeoff(_ctrl2.getSeqDataAck()));
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
	
	/**
	 * Start the configuration phase and check if all user parameters are setted
	 * @return
	 * @throws InvalidArgumentException
	 * @throws InterruptedException
	 */
	private int phaseConfig() throws InvalidArgumentException, InterruptedException{
//		pan
		_ctrl1.sendMessage(_mh1.cameraPan((byte) _userPan, _ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(_mh2.cameraPan((byte) - _userPan, _ctrl2.getSeqDataAck()));
		_ctrl2.incSeqDataAck();
		
		
//		altitude
		_ctrl1.sendMessage(_mh1.setMaxAltitude((byte) _userAltitude, _ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(_mh2.cameraPan((byte) _userAltitude, _ctrl2.getSeqDataAck()));
		_ctrl2.incSeqDataAck();
		
//		speed
		_ctrl1.sendMessage(_mh1.cameraPan((byte) _userSpeed, _ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(_mh2.cameraPan((byte) _userSpeed, _ctrl2.getSeqDataAck()));
		_ctrl2.incSeqDataAck();
		
		
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

	/**
	 * Start the media phase and check if the recording is started
	 * @return
	 * @throws InterruptedException
	 */
	private int phaseMediaStarted() throws InterruptedException {
		_ctrl1.sendMessage(_mh1.recordStart(_ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(_mh2.recordStart(_ctrl2.getSeqDataAck()));
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
	
	/**
	 * Start the flying phase sending gaz up until the max altitude is reach
	 * @throws InvalidArgumentException
	 */
	private void phaseFlying() throws InvalidArgumentException{
		do{
			_ctrl1.sendMessage(_mh1.gaz((byte) 50, _ctrl1.getSeqData()));
			_ctrl1.incSeqData();
			_ctrl2.sendMessage(_mh2.gaz((byte) 50, _ctrl2.getSeqData()));
			_ctrl2.incSeqData();
		}while(_currentAltitude1 < _userAltitude && _currentAltitude2 < _userAltitude);
		
	}

	/**
	 * Start the media phase and check if the recording is stopped
	 * @return
	 * @throws InterruptedException
	 */
	private int phaseMediaStoped() throws InterruptedException {
		_ctrl1.sendMessage(_mh1.recordStop(_ctrl1.getSeqDataAck()));
		_ctrl1.incSeqDataAck();
		_ctrl2.sendMessage(_mh2.recordStop(_ctrl2.getSeqDataAck()));
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
