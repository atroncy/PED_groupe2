package guiView;

import guiListener.KeyboardDrone;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.ConsoleModel;

public class DroneSendPanel extends JPanel implements Observer{

	private static final int NBROWS = 15; 
	private static final int NBCOLS = 15;
	private JTextArea _consoleText;
	private ConsoleModel _cm;
	
	public DroneSendPanel(KeyboardDrone k, ConsoleModel cm){
		
		_cm = cm;
		_consoleText = new JTextArea(_cm.getText());
		_consoleText.addKeyListener(k);
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
		_consoleText.setText(_cm.getText());
	}
		
	
}
