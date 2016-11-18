/**
 * @author Ben Newman (newmanba)
 * @author Wyatt Templeton (wtemplet)
 */

package edu.indiana.cs.c212.gameMechanics;

public class Move {
	private int x;
	private int y;
	protected boolean isOverwriteMove = false;
	
	public Move(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		//get the x-coordinate of the move
		return x;
	}
	
	public int getY(){
		//get the y coordinate of the move
		return y;
	}
	
	public boolean equals(Object other){
		//is other of type move?
		if (!other.getClass().equals(this.getClass()))
			//other is not of type move, so return false
			return false;
		
		//other is a move object, so cast it as such
		Move otherMove = (Move) other;
		
		//Are the x and y coordinates of the move and otherMove the same?
		if((otherMove.getY() == this.y) && (otherMove.getX() == this.x))
			//Yes, return true
			return true;
		//No, return false
		return false; 
	}

	@Override
	public String toString(){
		//Returns string representation of the move
		return this.getClass().getName() + '@' + Integer.toHexString(hashCode());
	}
}
