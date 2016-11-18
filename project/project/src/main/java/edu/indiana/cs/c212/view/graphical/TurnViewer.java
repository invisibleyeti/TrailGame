/**
 * @author Wyatt Templeton (wtemplet)
 * partner Ben Newman (newmanba)
 */

package edu.indiana.cs.c212.view.graphical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class TurnViewer extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerColor current;
	private GameRunner game;
	
	public TurnViewer(PlayerColor player, GameRunner game){
		this.current = player;
		if (game != null){
			game.addObserver(this);
			this.game = game;
		}
		
		this.setPreferredSize(new Dimension(30,30));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		xPoints[0]=3;
		xPoints[1]=3;
		xPoints[2]=23;
		yPoints[0]=3;
		yPoints[1]=23;
		yPoints[2]=13;
		Color curCol;
		g2D.setStroke(new BasicStroke(3.0f,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		g2D.drawPolygon(xPoints,yPoints,3);
		if (game == null || game.getCurrentPlayer()==null){
			g2D.setColor(Color.WHITE);
			g2D.fillPolygon(xPoints, yPoints, 3);
		} else if (game.getCurrentPlayer().getColor().equals(current)){
			if(current.equals(PlayerColor.BLUE))
				curCol = Color.BLUE;
			else
				curCol = Color.RED;
			g2D.setColor(curCol);
			g2D.fillPolygon(xPoints, yPoints, 3);
		}else{
			g2D.drawPolygon(xPoints, yPoints, 3);
			g2D.setColor(Color.WHITE);
			g2D.fillPolygon(xPoints, yPoints, 3);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		/*if(o!=null){
			this.game = (GameRunner) o;
		}*/
		this.repaint();
		/*if (arg != null)
		     current = arg;*/
	}

}
