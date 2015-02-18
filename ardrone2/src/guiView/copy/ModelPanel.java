package guiView.copy;

import guiModel.ConsoleModel;
import guiListener.KeyboardDrone;

import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class ModelPanel extends JPanel implements Observer{
	
	private static final int NBROWS = 15; 
	private static final int NBCOLS = 25;
	
	private ConsoleModel _consoleModel;
	private JScrollPane _consolePane;
	private JTextArea _consoleText;
	
	public ModelPanel(KeyboardDrone k,ConsoleModel model){
		
		this.setLayout(new BorderLayout());
		this.setSize(640, 520);
		
		
		// NavData
		JPanel navPanel = new JPanel(new BorderLayout());
		JLabel navLabel = new JLabel("NavData");
		navPanel.add(navLabel, BorderLayout.NORTH);
		
		
		//ConsoleModel
		JPanel consolePanel = new JPanel();
		consolePanel.setLayout(new BorderLayout());
		JLabel consoleLabel = new JLabel("Console");
		_consoleModel = model;
		_consoleModel.addObserver(this);
		_consoleText = new JTextArea(_consoleModel.getText());
		_consoleText.setSize(300,100);
		
		_consoleText.addKeyListener(k);
		_consoleText.setRows(NBROWS);
		_consoleText.setColumns(NBCOLS);
		_consoleText.setEditable(false);
		
		_consolePane = new JScrollPane(_consoleText);
		_consolePane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
		consolePanel.add(consoleLabel, BorderLayout.NORTH);
		consolePanel.add(_consolePane, BorderLayout.SOUTH);
		
		
		this.add(navPanel, BorderLayout.CENTER);
		this.add(consolePanel, BorderLayout.WEST);
	}

	public void update(Observable o, Object arg) {
		_consoleText.setText(_consoleModel.getText());
	}
		
}