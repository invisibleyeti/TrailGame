/**
 * @author Ben Newman (newmanba)
 * @author Wyatt Templeton (wtemplet)
 */

package edu.indiana.cs.c212.gameMechanics;

import edu.indiana.cs.c212.players.CommandLinePlayer;
import edu.indiana.cs.c212.players.ImprovedAI;
import edu.indiana.cs.c212.players.Player;
import edu.indiana.cs.c212.players.PointAndClickPlayer;
import edu.indiana.cs.c212.players.SimpleRandom;
import edu.indiana.cs.c212.players.SmartAIPlayer;
import edu.indiana.cs.c212.players.WtempletBasicTrailsPlayer;


public class PlayerFactory {
	public static Player createPlayer(PlayerType type, PlayerColor color){
		boolean generated = false;
		
		Player player = null;
		while(!generated){
		switch(type){
		case SimpleRandom:
			player = new SimpleRandom(color);
			generated=true;
			break;
		case CommandLinePlayer: 
			player = new CommandLinePlayer(color);
			generated=true;
			break;
		case WtempletBasicTrailsPlayer: 
			player = new WtempletBasicTrailsPlayer(color);
			generated=true;
			break;
		case SmartAIPlayer: 
			player = new SmartAIPlayer(color);
			generated=true;
			break;
		case ImprovedAI: 
			player = new ImprovedAI(color);
			generated=true;
			break;
		case PointAndClickPlayer:
			player = new PointAndClickPlayer(color);
			generated=true;
			break;
		default:
			System.out.println("Please choose a valid Player Type.");
			break;
		}
		}
		return player;
	}
}
