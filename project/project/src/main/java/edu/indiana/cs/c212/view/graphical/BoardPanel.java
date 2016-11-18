
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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import edu.indiana.cs.c212.board.Board;

public class BoardPanel extends JPanel implements Observer, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private HexTile[][] hexes;
	private double A,B;
	private int radius;
	private static final int DEFAULT_RADIUS = 30;
	
	public BoardPanel(Board board){
		this.setLayout(null);
		radius = DEFAULT_RADIUS;
		A = (radius*Math.sin(Math.PI/6));
		B = (radius*Math.cos(Math.PI/6));

		int prefWidth=(int) ((board.getSize()*2*B+board.getSize()*B)+14);
		int prefHeight=(int) ((2*radius+((2*radius)-A)*(board.getSize()-1))+14);
		
		this.setPreferredSize(new Dimension(prefWidth,prefHeight));
		
		this.board = board;
		int boardSize = board.getSize();
		
		hexes = new HexTile[boardSize][boardSize];
		
		for(int i=0;i<boardSize;i++){
			for(int j=0;j<boardSize;j++){
				hexes[i][j]=new HexTile(radius-3,radius+1,radius,board.getTileAt(i, j));
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		HexTile tile = (HexTile) e.getSource();
		MoveEvent move = new MoveEvent(new Point(tile.getBoardX(), tile.getBoardY()),0);
		for (AWTEventListener l : Toolkit.getDefaultToolkit().getAWTEventListeners()){
			l.eventDispatched(move);
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int size = board.getSize();
		int radius = (int) (this.getWidth()/((3*Math.cos(Math.PI/6)*size)));
		
		int AA = (int) A;
		int BB = (int) B;
		
		Graphics2D tablet = (Graphics2D) g;
		
		
		
		Rectangle2D redGoalUp = new Rectangle((int) ((.5*BB)+7),2,(int) (((2*size)-1)*BB),25);
		Rectangle2D redGoalDown = new Rectangle((int) (size*BB),this.getBounds().height-36,(int) (((2*size)-1)*BB),30);
		int[] xPoints = new int[4];
		int[] yPoints = new int[4];
		
		Rectangle2D blueGoalright = new Rectangle((int) redGoalUp.getMaxX(), (int) redGoalUp.getMaxY() + (BB / 2), 26, (size - 1) * (2 * BB));
	    AffineTransform af1 = new AffineTransform();
	    af1.rotate(Math.toRadians(-30), redGoalUp.getMaxX(), redGoalUp.getMaxY() + (BB / 2));
	    Rectangle2D blueGoalleft = new Rectangle(0, (BB * 2), 40, (size - 1) * (2 * BB));
	    AffineTransform af2 = new AffineTransform();
	    af2.rotate(Math.toRadians(-30), 0, (BB * 2));

	    Shape blueright = af1.createTransformedShape(blueGoalright);
	    Shape blueleft = af2.createTransformedShape(blueGoalleft);
	
		tablet.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		tablet.draw(redGoalUp);
		tablet.draw(redGoalDown);
		tablet.draw(blueright);
	    tablet.draw(blueleft);
		tablet.drawPolygon(xPoints, yPoints, 4);
		tablet.setColor(Color.RED);
		tablet.fill(redGoalUp);
		tablet.fill(redGoalDown);
        tablet.setColor(Color.BLUE);
        tablet.fill(blueright);
        tablet.fill(blueleft);
	
		
		for(int i=0;i<board.getSize();i++){
			for(int j=0;j<board.getSize();j++){
				hexes[i][j].setBounds((2*BB*i+j*BB)+7, j*(radius+AA)+7, (int) hexes[i][j].getPreferredSize().getWidth(), (int) hexes[i][j].getPreferredSize().getHeight());
				this.add(hexes[i][j]);
				hexes[i][j].addActionListener(this);
			}
		}	
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}

}
