/**
 * @author Ben Newman (newmanba)
 * @author Wyatt Templeton (wtemplet)
 */

package edu.indiana.cs.c212.gameMechanics;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.players.Player;

public class LoseByConnectingRules extends StandardRules implements Rules{
	
	public LoseByConnectingRules(Board board, Player red, Player blue){
		super(board, red, blue);
	}
	
	@Override
	public PlayerColor checkForWins(){
		Tile redStart = board.getTileAt(0,-1);
		Tile redTarget = board.getTileAt(0, board.getSize());
		Tile blueStart = board.getTileAt(-1,0);
		Tile blueTarget = board.getTileAt(board.getSize(),0);
		if(areTilesConnected(board, redStart, redTarget, PlayerColor.RED))
			return PlayerColor.BLUE;
		else if(areTilesConnected(board, blueStart, blueTarget, PlayerColor.BLUE)){
			return PlayerColor.RED;
		}
		return null;
	}
}
