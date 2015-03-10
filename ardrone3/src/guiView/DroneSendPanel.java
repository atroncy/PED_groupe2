package guiView;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DroneSendPanel extends JPanel implements Observer{

	private static final int NBROWS = 15; 
	private static final int NBCOLS = 15;
	private JTextArea _consoleText;
	
	public DroneSendPanel(){
		
		
		_consoleText = new JTextArea("Oui");
		
		_consoleText.setRows(NBROWS);
		_consoleText.setColumns(NBCOLS);
		_consoleText.setEditable(false);
		
		JScrollPane consoleScrollPane = new JScrollPane(_consoleText);
		consoleScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
		
		this.add(consoleScrollPane);
	}
	
	public void update(Observable o, Object arg) {
	//	_consoleText.setText(_consoleModel.getText());
	}
		
	
}
