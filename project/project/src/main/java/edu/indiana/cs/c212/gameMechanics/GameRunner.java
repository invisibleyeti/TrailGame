/**
 * @author Wyatt Templeton (wtemplet)
 * partner Ben Newman (newmanba)
 */

package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.SimpleGameBoard;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.Player;
import edu.indiana.cs.c212.view.graphical.GraphicalBoardView;
import edu.indiana.cs.c212.view.textual.CommandLineView;

public class GameRunner extends Observable implements Runnable {
	private boolean running = true;
	private String redPlayerType;
	private String bluePlayerType;
	private String gameType;
	private Board board;
	private Queue<Player> playing;
	
	public GameRunner(int boardSize, String red, String blue, String ruleSet){
		this.redPlayerType = red;
		this.bluePlayerType = blue;
		this.gameType = ruleSet;
		board = new SimpleGameBoard(boardSize);
		playing = new LinkedList<Player>();
	}
	
	public Player createPlayer(String playerType, PlayerColor color){
		Player player = PlayerFactory.createPlayer(PlayerType.valueOf(PlayerType.class, playerType.replace(" ", "")), color);
		playing.add(player);
		return player;
	}
	
	public Player getCurrentPlayer(){
		Player current = playing.poll();
		playing.add(current);
		return current;
	}
	
	public static List<String> getPlayersList(){
		ArrayList<String> playerTypes = new ArrayList<String>();
		playerTypes.add("Simple Random");
		playerTypes.add("Command Line Player"); 
		playerTypes.add("PointAndClickPlayer");
		playerTypes.add("Wtemplet Basic Trails Player");
		playerTypes.add("Improved AI");
		playerTypes.add("Smart AI Player");
		return playerTypes;
	}
	
	public static List<String> getRuleSets(){
		ArrayList<String> rules = new ArrayList<String>();
		rules.add("Standard Rules");
		rules.add("Lose by Connecting");
		rules.add("Overwrite Rules");
		rules.add("Random Player Move");
		return rules;
	}
	
	public Board getBoard(){
		return board;
	}
	
	@Override
	public void run(){
		Rules game = null;
		Player red = createPlayer(redPlayerType, PlayerColor.RED);
		Player blue = createPlayer(bluePlayerType, PlayerColor.BLUE);
		System.out.println(gameType);
		if (gameType.equals("StandardRules")||gameType.equals("0")){
			game = new StandardRules(board, red, blue);
		} else if (gameType.equals("LoseByConnectingRules")||gameType.equals("1")){
			game = new LoseByConnectingRules(board, red, blue);
		}else if (gameType.equals("RandomPlayerMove")||gameType.equals("2")){
				game = new RandomPlayerRules(board, red, blue);
		} else{
			game = new OverwriteRules(board, red, blue);
		}
		while(running){
			SimpleGameBoard board = (SimpleGameBoard) this.board;
			Board copy = new SimpleGameBoard(board);
			playing.remove(null);
			Player current = getCurrentPlayer();
			System.out.println(current);
			Move move = null;
			Random rand = new Random();
			
			int  n = rand.nextInt(2);
			System.out.println(n);
			move = current.getMove(copy, game.getLegalMoves(current));
			if(gameType.equals("RandomPlayerMove") || gameType.equals("2")){
			if(n == 1){
				game.nextTurn();
			this.setChanged();
			this.notifyObservers();
			current = getCurrentPlayer();
			move = current.getMove(copy, game.getLegalMoves(current));
			}
			}
			
			try {
				game.makeMove(move);
				this.setChanged();
				this.notifyObservers(move);
			} catch (InvalidMoveException e) {
				System.out.printf("%s played an invalid move! %s loses!", current.getColor(), current.getColor());
				stopGame();
			}
			if(game.checkForWins() == PlayerColor.RED || game.checkForWins() == PlayerColor.BLUE){
				System.out.printf("%s Player Wins!", game.checkForWins());
				stopGame();
			}
			
			game.nextTurn();
		}
	}
		
		
	
	
	public void stopGame(){
		System.out.println("\nGAME OVER");
		running = false;
	}
	
	public static void main(String[] args) {
	    if (args.length == 1 && args[0].equals("text")) {
	        // use commandlineview
	        Scanner scanner = new Scanner(System.in);
	        CommandLineView.setup(scanner);
	    } else {
	        // use graphical view
	        SwingUtilities.invokeLater(GraphicalBoardView.setup());
	    }
	}

}

/**
 * public Move getMove(Board board, List<Move> legalmoves){
 * 
 * }
 * 
 * public int heuristic(Move m, Board b){
 *      b.makeMove(m, this.getColor())
 *      StandardRules.areTilesConnected(b, b.getTileAt
 * }
 * 
 */ 
