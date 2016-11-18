/**
 * @author Ben Newman (newmanba)
 * @author Wyatt Templeton (wtemplet)
 */

package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.players.Player;

public class OverwriteRules extends StandardRules {
	
	public OverwriteRules(Board board, Player red, Player blue) {
		super(board, red, blue);
	}
	
	public boolean isLegalMove(Move m){
		int x = m.getX();
		int y = m.getY();
		int size = super.board.getSize();
		
		if(!m.isOverwriteMove && super.isLegalMove(m))
			return true;
		if(m.isOverwriteMove && x<size && y<size && x>=-1 && y>=-1 && 
				super.board.getTileAt(x, y).getColor().equals(this.getNextPlayer().getColor()))
			return true;
		return false;
	}
	
	public ArrayList<Move> getLegalMoves(Player player){
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		for (int i = 0; i < super.board.getSize(); i++) {
			for (int j = 0; j < super.board.getSize(); j++) {
				if (board.getTileAt(i, j).getColor().equals(getNextPlayer().getColor()))
					legalMoves.add(new OverwriteMove(i, j));
				if (board.getTileAt(i, j).getColor().equals(PlayerColor.BLANK))
					legalMoves.add(new Move(i,j));
			}
		}
		return legalMoves;
	}
}
