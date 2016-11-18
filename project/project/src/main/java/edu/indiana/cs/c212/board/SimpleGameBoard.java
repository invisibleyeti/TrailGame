/**
 * @author Ben Newman (newmanba)
 * @author Wyatt Templeton (wtemplet)
 */

package edu.indiana.cs.c212.board;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class SimpleGameBoard extends AbstractGameBoard {
	private Tile[][] board;
	private Tile[] goals; //Tile orientation in array is [N goal, S goal, E goal, W goal]
	
	public SimpleGameBoard(int size){
		this.size = size;
		
		//creates a dummy board
		board = new Tile[size][size];
		
		//fills dummy board with blank tiles
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				Tile newTile = new Tile(PlayerColor.BLANK, new Point(i,j));
				board[i][j] = newTile;
			}
		}
		
		//sets up the goal array
		goals = new Tile[4];
		goals[0] = new Tile(PlayerColor.BLUE, new Point(-1,0));
		goals[1] = new Tile(PlayerColor.BLUE, new Point(size,0));
		goals[2] = new Tile(PlayerColor.RED, new Point(0,-1));
		goals[3] = new Tile(PlayerColor.RED, new Point(0,size));
	}
	
	public SimpleGameBoard(SimpleGameBoard other){
		//constructs blank board and goal array
		this(other.getSize());
		
		//turns blank board into replicate of other
		for (int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				Tile newTile = new Tile(other.getTileAt(i, j));
				board[i][j] = newTile;
			}
		}
	}
	
	@Override
	public Tile getTileAt(int x, int y) {
		if(x<-1 || y<-1 || x>size || y>size)
			return null;
		if(x == size)
			return goals[1];
		if(y == size)
			return goals[3];
		if(x == -1)
			return goals[0];
		if(y == -1)
			return goals[2];
		return board[x][y];
	}

	@Override
	public Set<Tile> getNeighbors(Tile t) {
		//initializes a set which will contain all the tile t's neighbors
		Set<Tile> neighbors = new HashSet<Tile>();
		
		//retrieves t's current location as a Point
		Point current = t.getPoint();
		
		int row = (int) current.getX();
		int col = (int) current.getY();
		
		//if t is a goal tile, return appropriate neighbors
		//This will be all of the tiles on the appropriate side of the board
		if(row == -1){
			for(int i = 0; i<size;i++)
				neighbors.add(getTileAt(0,i));
		}
		
		if(col == -1){
			for(int i = 0; i<size;i++)
				neighbors.add(getTileAt(i,0));
		}
		
		if(row == size){
			for(int i = 0; i<size;i++)
				neighbors.add(getTileAt(size-1,i));
		}
		
		if(col == size){
			for(int i = 0; i<size;i++)
				 neighbors.add(getTileAt(i,size-1));
		}
		
		//if t is not a goal node return the six appropriate neighbors
		if(row > -1 && row < size && col > -1 && col < size){
			neighbors.add(getTileAt(row-1,col));
			neighbors.add(getTileAt(row-1,col+1));
			neighbors.add(getTileAt(row,col-1));
			neighbors.add(getTileAt(row,col+1));
			neighbors.add(getTileAt(row+1,col-1));
			neighbors.add(getTileAt(row+1,col));
		}
		
		return neighbors;
	}

	@Override
	public void makeMove(Move m, PlayerColor player) {
		int x = m.getX();
		int y = m.getY();
		board[x][y].setColor(player);
	}

}
