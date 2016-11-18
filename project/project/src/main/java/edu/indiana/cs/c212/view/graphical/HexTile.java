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
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class HexTile extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x,y,radius;
	private Tile tile;
	//private int A;
	private int B; 
	private Polygon hexTile;
	
	public HexTile(int x, int y, int radius, Tile tile){
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.B = (int) (radius * Math.cos(Math.PI/6));
		//this.A = (int) (radius * Math.sin(Math.PI/6));
		this.tile = tile;
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(2*B+4,2*radius+4));
		int[] xPoints = new int[6];
		int[] yPoints = new int[6];
		
		xPoints[0] = this.x;
		xPoints[1] = (int) (radius*Math.cos((5*Math.PI)/6))+this.x;
		xPoints[2] = (int) (radius*Math.cos((7*Math.PI)/6))+this.x;
		xPoints[3] = this.x;
		xPoints[4] = (int) (radius*Math.cos((11*Math.PI)/6))+this.x;
		xPoints[5] = (int) (radius*Math.cos((1*Math.PI)/6))+this.x;
		
		yPoints[0] = this.y+radius;
		yPoints[1] = (int) (radius*Math.sin((5*Math.PI)/6))+this.y;
		yPoints[2] = (int) (radius*Math.sin((7*Math.PI)/6))+this.y;
		yPoints[3] = this.y-radius;
		yPoints[4] = (int) (radius*Math.sin((11*Math.PI)/6))+this.y;
		yPoints[5] = (int) (radius*Math.sin(Math.PI/6))+this.y;
		hexTile = new Polygon(xPoints,yPoints,6);
	}
	
	public boolean contains(int x, int y){
		return (hexTile.contains(x, y));
	}
	
	public boolean contains(Point p){
		return contains((int) p.getX(), (int) p.getY());
	}
	
	public int getBoardX(){
		return (int) tile.getPoint().getX();
	}
	
	public int getBoardY(){
		return (int) tile.getPoint().getY();
	}
	
	public void setRadius(int r){
		this.radius = r;
		this.B = (int) (radius * Math.cos(Math.PI/6));
		//this.A = (int) (radius * Math.sin(Math.PI/6));
		this.setPreferredSize(new Dimension(2*B+4,2*radius+4));
		int[] xPoints = new int[6];
		int[] yPoints = new int[6];
		xPoints[0] = this.x;
		xPoints[1] = (int) (radius*Math.cos((5*Math.PI)/6))+this.x;
		xPoints[2] = (int) (radius*Math.cos((7*Math.PI)/6))+this.x;
		xPoints[3] = this.x;
		xPoints[4] = (int) (radius*Math.cos((11*Math.PI)/6))+this.x;
		xPoints[5] = (int) (radius*Math.cos((1*Math.PI)/6))+this.x;
		
		yPoints[0] = this.y+radius;
		yPoints[1] = (int) (radius*Math.sin((5*Math.PI)/6))+this.y;
		yPoints[2] = (int) (radius*Math.sin((7*Math.PI)/6))+this.y;
		yPoints[3] = this.y-radius;
		yPoints[4] = (int) (radius*Math.sin((11*Math.PI)/6))+this.y;
		yPoints[5] = (int) (radius*Math.sin(Math.PI/6))+this.y;
		hexTile = new Polygon(xPoints,yPoints,6);
	}
	
	public void paint(Graphics g){
		Graphics2D tablet = (Graphics2D) g;
	
		tablet.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
		tablet.drawPolygon(hexTile);
		
		Color color = null;
		if(tile.getColor().equals(PlayerColor.RED))
			color= Color.RED;
		else if(tile.getColor().equals(PlayerColor.BLUE))
			color=Color.BLUE;
		else
			color=Color.WHITE;
		
		tablet.setColor(color);
		tablet.fillPolygon(hexTile);
	}
	
	protected void processMouseEvent(MouseEvent e){
		if(this.contains(e.getPoint())){
			super.processMouseEvent(e);
		}
	}
}
