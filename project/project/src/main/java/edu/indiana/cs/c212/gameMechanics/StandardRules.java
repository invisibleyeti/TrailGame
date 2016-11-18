/**
 *  @author Ben Newman (newmanba)
 *  @author Wyatt Templeton (wtemplet)
 **/

package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.Player;

public class StandardRules implements Rules {
	protected final Board board;
	private Queue<Player> players;

	public StandardRules(Board board, Player red, Player blue) {
		this.board = board;
		players = new LinkedList<Player>();
		players.add(red);
		players.add(blue);
	}

	public static boolean areTilesConnected(Board board, Tile start,
			Tile target, PlayerColor color) {

		if (start.equals(target)) {
			return true;
		} 

		if(!start.getColor().equals(target.getColor())){
			return false;
		}
		
		Queue<Tile> frontier = new LinkedList<Tile>();
		Set<Tile> visited = new HashSet<Tile>();
		frontier.addAll(board.getNeighbors(start));
		visited.add(start);
		
		while (!frontier.isEmpty()) {
			Tile current = frontier.poll();
			if (current.getColor().equals(color)) {
				if (current.equals(target))
					return true;
				for (Tile neighbor : board.getNeighbors(current)) {
					if (!visited.contains(neighbor))
						frontier.add(neighbor);
				}
			}
			visited.add(current);
		}
		return false;
	}

	@Override
	public Queue<Player> getPlayers() {
		return players;
	}

	@Override
	public PlayerColor checkForWins() {
		Tile startRed = board.getTileAt(0,-1);
		Tile goalRed = board.getTileAt(0, board.getSize());
		Tile startBlue = board.getTileAt(-1,0);;
		Tile goalBlue = board.getTileAt(board.getSize(), 0);;
		if(areTilesConnected(board, startRed, goalRed, PlayerColor.RED))
			return PlayerColor.RED;
		if(areTilesConnected(board, startBlue, goalBlue, PlayerColor.BLUE))
			return PlayerColor.BLUE;
		return null;
	}

	@Override
	public boolean isLegalMove(Move m) {
		int x = m.getX();
		int y = m.getY();
		int size = board.getSize();
		return ((x < size) && (x >= -1) && (y < size) && (y >= -1)	&& board.getTileAt(x, y).getColor().equals(PlayerColor.BLANK));

	}

	@Override
	public Player nextTurn() {
		Player current = players.poll();
		players.add(current);
		return players.peek();
	}

	@Override
	public Player getNextPlayer() {
		Player current = players.poll();
		Player next = players.poll();
		players.add(current);
		players.add(next);
		return next;
	}

	@Override
	public void makeMove(Move m) throws InvalidMoveException {
		if (isLegalMove(m)){
			this.board.getTileAt(m.getX(), m.getY()).setColor(
					players.peek().getColor());
		} else
			throw new InvalidMoveException(null, m, 0);
	}

	@Override
	public ArrayList<Move> getLegalMoves(Player player) {
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				if (board.getTileAt(i, j).getColor().equals(PlayerColor.BLANK))
					legalMoves.add(new Move(i, j));
			}
		}
		return legalMoves;
	}

}
