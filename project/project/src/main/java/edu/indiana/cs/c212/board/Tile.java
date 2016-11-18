/**
 * @author Ben Newman (newmanba)
 * @author Wyatt Templeton (wtemplet)
 */

package edu.indiana.cs.c212.board;

import java.awt.Point;

import edu.indiana.cs.c212.gameMechanics.PlayerColor;


public class Tile {
	private PlayerColor color;
	private Point p;

	public Tile (PlayerColor color, Point p){
		this.color = color;
		this.p = p;
	}
	
	public Tile (Tile tile){
		PlayerColor color2 = tile.getColor();
		color = color2;
		
		Point p2 = new Point((int)tile.getPoint().getX(), (int)tile.getPoint().getY());
		p = p2;
	}
	
	public PlayerColor getColor(){
		return color;
	}
	
	public void setColor(PlayerColor value){
		this.color = value;
	}
	
	public Point getPoint(){
		return p;
	}
	
	public boolean equals (Object obj){
		if(this.getClass().equals(obj.getClass())){
			Tile tileObj = (Tile) obj;
			if(this.color.equals(tileObj.getColor()) && this.p.equals(tileObj.getPoint()))
				return true;
		}
		return false;
	}
	
	public String toString(){
		return p.toString() + " " + color.toString();
		}
}
