/**
 * @author Ben Newman (newmanba)
 * @author Wyatt Templeton (wtemplet)
 */

package edu.indiana.cs.c212.gameMechanics;

public class OverwriteMove extends Move {

	public OverwriteMove(int x, int y) {
		super(x, y);
		isOverwriteMove = true;
	}

}
