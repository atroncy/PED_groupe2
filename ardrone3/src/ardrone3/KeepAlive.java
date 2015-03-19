package ardrone3;

public class KeepAlive extends Thread{

	
	private Controller _ctrl;
	private MessageHandler _mh;

	public KeepAlive(Controller ctrl, MessageHandler mh){
		_ctrl = ctrl;
		_mh = mh;
	}
	
	
	/**
	 * This thread intent is to keep alive the communication between the drone
	 * and the controller. We send a PCMD with 0 arguments. 
	 * Note : The application FreeFlight3 use a similar mechanics using PCMD with 0 as arguments  
	 */
	@Override
	public void run() {
		
		while(true){
			try {
				_ctrl.sendMessage(_mh.gaz((byte) 0, _ctrl.getSeqData()));
				_ctrl.incSeqData();
				
				Thread.sleep(500);
				
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		
	}
	
	
	
}
