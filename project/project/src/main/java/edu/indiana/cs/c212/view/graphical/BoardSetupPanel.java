/**
 * @author Ben Newman (newmanba)
 * @author Wyatt Templeton (wtemplet)
 */

package edu.indiana.cs.c212.view.graphical;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class BoardSetupPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_BOARD_SIZE = 5; 
	public static final int MAX_SUPPORTED_BOARD_SIZE = 50;
	public static final int MIN_SUPPORTED_BOARD_SIZE = 3;
	private JSpinner spinner;
	
	public BoardSetupPanel(){
		Integer def = new Integer(DEFAULT_BOARD_SIZE);
		Integer max = new Integer(MAX_SUPPORTED_BOARD_SIZE);
		Integer min = new Integer(MIN_SUPPORTED_BOARD_SIZE);
		Integer step = new Integer(1);
		SpinnerNumberModel sizeSpinner = new SpinnerNumberModel(def,min,max,step);
		spinner = new JSpinner(sizeSpinner);
		this.add(spinner);
		
	}
	
	public int getBoardSize(){
		return (Integer) spinner.getValue();
	}
}
