/**
 * @author Wyatt Templeton (wtemplet)
 * partner Ben Newman (newmanba)
 */


package edu.indiana.cs.c212.view.graphical;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PlayerChoicePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> choices;
	
	public PlayerChoicePanel(String playerName){
		DefaultComboBoxModel<String> choicesModel = new DefaultComboBoxModel<String>();
		choicesModel.addElement("SimpleRandom");
		choicesModel.addElement("CommandLinePlayer");
		choicesModel.addElement("PointAndClickPlayer");
		choicesModel.addElement("Wtemplet Basic Trails Player");
		choicesModel.addElement("Improved AI");
		choicesModel.addElement("Smart AI Player");
		choices = new JComboBox<String>(choicesModel);
		this.add(choices);
		
	}
	
	public String getPlayerType(){
		return (String) choices.getSelectedItem();
	}
}
