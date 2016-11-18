/**
 * @author Wyatt Templeton (wtemplet)
 * partner Ben Newman (newmanba)
 */

package edu.indiana.cs.c212.view.graphical;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class RulesChoicePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JComboBox<String> choices;
	
	public RulesChoicePanel(){
		DefaultComboBoxModel<String> choicesModel = new DefaultComboBoxModel<String>();
		choicesModel.addElement("OverwriteRules");
		choicesModel.addElement("StandardRules");
		choicesModel.addElement("RandomPlayerMove");
		choicesModel.addElement("LoseByConnectingRules");
		choices = new JComboBox<String>(choicesModel);
		this.add(choices);
		choices.setVisible(true);
	}
	
	public String getRulesType(){
		return (String) choices.getSelectedItem();
	}
}

