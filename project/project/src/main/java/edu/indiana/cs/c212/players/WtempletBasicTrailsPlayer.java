/**
 * @author Wyatt Templeton (wtemplet)
 * partner Ben Newman (newmanba)
 */


package edu.indiana.cs.c212.players;

import java.awt.Point;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.SimpleGameBoard;
import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;
import edu.indiana.cs.c212.gameMechanics.StandardRules;

public class WtempletBasicTrailsPlayer extends AbstractPlayer {
	private PlayerColor player;
	private PlayerColor enemy;
	private int counter = 0;
	private Move lastMove; 
	private boolean second;

	

	public WtempletBasicTrailsPlayer(PlayerColor player){
		super(player);
		if(player.equals(PlayerColor.RED))
			enemy = PlayerColor.BLUE;
		if(player.equals(PlayerColor.BLUE))
			enemy = PlayerColor.RED;
		this.player = player;
	}
	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		if(player.equals(PlayerColor.BLUE))
			return BlueStrat(board, legalMoves);
		
			return RedStrat(board,legalMoves);
	}

	public Move RedStrat(Board board, List<Move> legalMoves) {
		Random num = new Random();
		int next = num.nextInt(legalMoves.size());
		Move bestMove = legalMoves.get(next);
		int bestscore = 0;
		if (counter == 0){
			
			Move move4 = new Move(3,0);
			counter = counter + 1;
			lastMove = move4;
			return move4;
		}
		
		for(Move currentMove:legalMoves){
			int currentscore = moveValue(board, currentMove,legalMoves);
			if (currentscore > bestscore){
				bestscore = currentscore;
				bestMove = currentMove;
			
		}
		
		}
		counter = bestMove.getY() + 1;
		lastMove = bestMove;
		return bestMove;
	}
	
	public Move BlueStrat(Board board, List<Move> legalMoves) {
		Random num = new Random();
		int next = num.nextInt(legalMoves.size());
		Move bestMove = legalMoves.get(next);
		int bestscore = 0;
		if (counter == 0){
			for (int i = 0; i < board.getSize(); i++) {
				for (int j = 0; j < board.getSize(); j++){
					if (board.getTileAt(i, j).getColor().equals(enemy)){
						if(j - 3 >= 0){
							Move move2 = new Move(i,j - 3);
							if(legalMoves.contains(move2)){
								second = true;
							counter = counter + 1;
							lastMove = move2;
								return move2;
							}
						}
				if(j + 3 < board.getSize()){
					Move move3 = new Move(i,j + 3);
					if(legalMoves.contains(move3)){
						second = true;
					counter = counter + 1;
					lastMove = move3;
					return move3;
				}
				}
		}
	}
			}
			Move move4 = new Move(0,2);
			counter = counter + 1;
			lastMove = move4;
			return move4;
		}
		
		
		if(second){
			int x = lastMove.getX();
			int y = lastMove.getY();
			Move secondmove = new Move(x - 1,y);
			Move secondmove2 = new Move(x + 1,y);
			if(legalMoves.contains(secondmove)){
				lastMove = secondmove;
			if(lastMove.getX() == 0)
				second = false;
			counter = counter + 1;
			return secondmove;
							}
			if(legalMoves.contains(secondmove2)){
				lastMove = secondmove2;
				if(lastMove.getX() == 0)
					second = false;
			counter = counter + 1;
			return secondmove2;
			
	}
			second = false;
			lastMove = bestMove;
			return bestMove;
	}
		for(Move currentMove:legalMoves){
			int currentscore = moveValue(board, currentMove,legalMoves);
			if (currentscore > bestscore){
				bestscore = currentscore;
				bestMove = currentMove;
			
		}
		
		}
		counter = bestMove.getX() + 1;
		lastMove = bestMove;
		return bestMove;
	}
	
	public int moveValue(Board board, Move move,List<Move> legalMoves){
		int moveValue = 0;
		Board board2 = new SimpleGameBoard((SimpleGameBoard)board);
		board2.makeMove(move,player);
		Set<Tile> neighbors = board.getNeighbors(board.getTileAt(move.getX(), move.getY()));
			if(youWin(board,move))
				moveValue = moveValue + 100;
			if(theyWin(board,move))
				moveValue = moveValue + 75;
			
			if(player.equals(PlayerColor.BLUE)){
				Point point = new Point (lastMove.getX(), lastMove.getY());
				Tile bluetile = new Tile(PlayerColor.BLUE, point);
				Point point2 = new Point (move.getX(), move.getY());
				Tile t = new Tile(PlayerColor.BLUE, point2);
				if(move.getX() == counter && StandardRules.areTilesConnected(board2,bluetile , t, PlayerColor.BLUE)){
				moveValue = moveValue + 35;
			}
			}
			if(player.equals(PlayerColor.RED)){
				Point point = new Point (lastMove.getX(), lastMove.getY());
				Tile redtile = new Tile(PlayerColor.RED, point);
				Point point2 = new Point (move.getX(), move.getY());
				Tile t = new Tile(PlayerColor.RED, point2);
				if(move.getY() == counter && StandardRules.areTilesConnected(board2,redtile , t, PlayerColor.RED)){
				moveValue = moveValue + 35;
			}
			}
			if(player.equals(PlayerColor.BLUE)){
				if(move.getX() == lastMove.getX())
				moveValue = moveValue + 20;
									}
			if(player.equals(PlayerColor.RED)){
				if(move.getY() == lastMove.getY())
				moveValue = moveValue + 20;
									}
				for(Tile t:neighbors){
					if(!t.equals(board2.getTileAt(0,-1))){
						if(!t.equals((board2.getTileAt(0, board.getSize())))){
							if(!t.equals(board2.getTileAt(-1,0))){
								if(!t.equals(board2.getTileAt(board.getSize(),0))){
										if(t.getColor().equals(player))
											moveValue = moveValue + 4;
												if(t.getColor().equals(enemy))
														moveValue = moveValue + 1;
												
																			}
																				}
																					}
																						}
												
																			
																							}
				
			
				return moveValue;
		
	}
	
	
		
	private boolean youWin(Board board, Move move){
		Board board2 = new SimpleGameBoard((SimpleGameBoard)board);
		board2.makeMove(move, player);
		Tile redStart = board2.getTileAt(0,-1);
		Tile redTarget = board2.getTileAt(0, board.getSize());
		Tile blueStart = board2.getTileAt(-1,0);
		Tile blueTarget = board2.getTileAt(board.getSize(),0);
		if(StandardRules.areTilesConnected(board2, redStart, redTarget, PlayerColor.RED))
			return true;
		else if(StandardRules.areTilesConnected(board2, blueStart, blueTarget, PlayerColor.BLUE)){
			return true;
		}
		return false;
	}
	private boolean theyWin(Board board,Move move){
		Board board2 = new SimpleGameBoard((SimpleGameBoard)board);
			board2.makeMove(move, enemy);
		Tile redStart = board2.getTileAt(0,-1);
		Tile redTarget = board2.getTileAt(0, board.getSize());
		Tile blueStart = board2.getTileAt(-1,0);
		Tile blueTarget = board2.getTileAt(board.getSize(),0);
		if(StandardRules.areTilesConnected(board2, redStart, redTarget, PlayerColor.RED))
			return true;
		else if(StandardRules.areTilesConnected(board2, blueStart, blueTarget, PlayerColor.BLUE)){
			return true;
		}
		return false;
		
	}
		

	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Smart AI";
	}

}
