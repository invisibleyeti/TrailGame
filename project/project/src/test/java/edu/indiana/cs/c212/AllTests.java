package edu.indiana.cs.c212;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.indiana.cs.c212.gameMechanics.GameRunnerTest;
import edu.indiana.cs.c212.gameMechanics.LoseByConnectingRulesTest;
import edu.indiana.cs.c212.gameMechanics.MoveTest;
import edu.indiana.cs.c212.gameMechanics.OverwriteMoveTest;
import edu.indiana.cs.c212.gameMechanics.OverwriteRulesTest;
import edu.indiana.cs.c212.gameMechanics.StandardRulesTest;
import edu.indiana.cs.c212.players.SimpleRandomTest;
import edu.indiana.cs.c212.board.TileTest;
import edu.indiana.cs.c212.board.SimpleGameBoardTest;


@RunWith(Suite.class)
@SuiteClasses({	MoveTest.class, 
				StandardRulesTest.class,
				LoseByConnectingRulesTest.class,
				TileTest.class,
				SimpleGameBoardTest.class,
				SimpleRandomTest.class,
				OverwriteRulesTest.class,
				OverwriteMoveTest.class,
				GameRunnerTest.class
				})
public class AllTests {

}
