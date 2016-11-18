/**
 * @author Ben Newman (newmanba)
 * @author Wyatt Templeton (wtemplet)
 */

package edu.indiana.cs.c212.players;

import java.util.List;
import java.util.Random;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class SimpleRandom extends AbstractPlayer {
	
	public SimpleRandom(PlayerColor player){
		super(player);
		
	}

	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		Random num = new Random();
		int next = num.nextInt(legalMoves.size());
		return legalMoves.get(next);
	}
	
	
	
	@Override
	public String getName() {
		return "Simple Random";
	}
}
