/**
 * @author Wyatt Templeton (wtemplet)
 * partner Ben Newman (newmanba)
 */


package edu.indiana.cs.c212.view.graphical;

import java.awt.AWTEvent;
import java.awt.Point;

public class MoveEvent extends AWTEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MoveEvent(Point p, int id) {
		super(p, id);
		// TODO Auto-generated constructor stub
	}

}
