/**
 * @author Wyatt Templeton (wtemplet)
 * partner Ben Newman (newmanba)
 */


package edu.indiana.cs.c212.players;

import java.awt.AWTEvent;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.util.List;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.OverwriteMove;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class PointAndClickPlayer extends AbstractPlayer implements
		AWTEventListener {
	private Point movePoint;

	public PointAndClickPlayer(PlayerColor c) {
		super(c);
	}

	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		Toolkit.getDefaultToolkit().addAWTEventListener(this, 0);
		
		while(movePoint==null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Move move = null;
		if(board.getTileAt(movePoint.x, movePoint.y).getColor().equals(PlayerColor.BLANK))
			move = new Move((int) movePoint.getX(),(int) movePoint.getY());
		else
			move = new OverwriteMove((int) movePoint.getX(),(int) movePoint.getY());
		
		Toolkit.getDefaultToolkit().removeAWTEventListener(this); 
		
		movePoint=null;
		
		return move;
	}

	@Override
	public void eventDispatched(AWTEvent arg0) {
		movePoint = (Point) arg0.getSource();
	}

	@Override
	public String getName() {
		return "PointAndClickPlayer";
	}

}
