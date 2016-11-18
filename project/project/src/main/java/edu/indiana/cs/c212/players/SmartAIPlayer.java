package edu.indiana.cs.c212.players;

import java.util.List;
import java.util.Random;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.SimpleGameBoard;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;
import edu.indiana.cs.c212.gameMechanics.StandardRules;

public class SmartAIPlayer extends AbstractPlayer {
	private PlayerColor player;
	private int scorecounter = 0;

	public SmartAIPlayer (PlayerColor player){
		super(player);
		this.player = player;
	}
	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		if(player.equals(PlayerColor.BLUE))
			return BlueStrat(board, legalMoves);
		
			return RedStrat(board,legalMoves);
	}
	
	public Move BlueStrat(Board board, List<Move> legalMoves) {
		int length = board.getSize();
		Board board2 = new SimpleGameBoard((SimpleGameBoard)board);
		Random num = new Random();
		int next = num.nextInt(legalMoves.size());
		Move bestMove = legalMoves.get(next);
		int bestscore = 0;
		int currentscore = 0;
		Move currentMove = legalMoves.get(next);
		if (scorecounter == 0){
			
			Move move4 = new Move(0,3);
			scorecounter = scorecounter + 1;
			return move4;
		}
		for(Move move: legalMoves){
			Board board3 = new SimpleGameBoard((SimpleGameBoard)board2);
			board3.getTileAt(move.getX(),move.getY()).setColor(PlayerColor.BLUE);
			
			for(int x = 0; x<length;x++){
			if(StandardRules.areTilesConnected(board3, board3.getTileAt(0,x), 
					board3.getTileAt(move.getX(),move.getY()), PlayerColor.BLUE)){
				if (move.getX() >= currentscore){
					currentMove = move;
					currentscore = move.getX() + 1;
				}
			}
			
			
		}
			
		}
		if(currentscore >= bestscore){
			bestscore = currentscore;
			bestMove = currentMove;
		}
			
			return bestMove;
		}
		
		
	
public Move RedStrat(Board board, List<Move> legalMoves) {
	int length = board.getSize();
	Board board2 = new SimpleGameBoard((SimpleGameBoard)board);
	Random num = new Random();
	int next = num.nextInt(legalMoves.size());
	Move bestMove = legalMoves.get(next);
	int bestscore = 0;
	int currentscore = 0;
	Move currentMove = legalMoves.get(next);
	if (scorecounter == 0){
		
		Move move4 = new Move(3,0);
		scorecounter = scorecounter + 1;
		return move4;
	}
	for(Move move: legalMoves){
		Board board3 = new SimpleGameBoard((SimpleGameBoard)board2);
		board3.getTileAt(move.getX(),move.getY()).setColor(PlayerColor.RED);
		
		for(int x = 0; x<length;x++){
		if(StandardRules.areTilesConnected(board3, board3.getTileAt(x,0), 
				board3.getTileAt(move.getX(),move.getY()), PlayerColor.RED)){
			if (move.getY() >= currentscore){
				currentMove = move;
				System.out.println(move.getY()+1);
				currentscore = move.getY() + 1;
			}
		}
		
		
	}
		
	}
	if(currentscore >= bestscore){
		bestscore = currentscore;
		bestMove = currentMove;
	}
		
		return bestMove;
	}
	
	

	@Override
	public String getName() {
		
		return "Improved AI";
	}

}
