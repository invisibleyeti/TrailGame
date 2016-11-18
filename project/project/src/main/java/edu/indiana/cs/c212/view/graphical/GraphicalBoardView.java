/**
 * @author Wyatt Templeton (wtemplet)
 * partner Ben Newman (newmanba)
 */


package edu.indiana.cs.c212.view.graphical;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class GraphicalBoardView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JComponent rules = new RulesChoicePanel();
	private static JComponent redPlayerType = new PlayerChoicePanel("Red");
	private static JComponent bluePlayerType = new PlayerChoicePanel("Blue");
	private static JComponent size = new BoardSetupPanel();
	private static GameRunner game;
	private static JComponent start;
	private static JPanel views= new JPanel();;
	private JPanel redTurn;
	private JPanel blueTurn;
	private static JPanel board=new JPanel();
	
	private GraphicalBoardView(){
		this.setMaximizedBounds(new Rectangle(900,1200));
		this.setMaximumSize(new Dimension(900,1200));
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Trails: The Adventure Game!");
		start = new JButton("Start");
		((JButton) start).setActionCommand("start");
		((JButton) start).addActionListener(this);
	}
	
	protected void prepareGame(){
		BoardSetupPanel boardSize = (BoardSetupPanel) size;
		PlayerChoicePanel red = (PlayerChoicePanel) redPlayerType;
		PlayerChoicePanel blue = (PlayerChoicePanel) bluePlayerType;
		RulesChoicePanel ruleSet = (RulesChoicePanel) rules;
		
		game = new GameRunner(boardSize.getBoardSize(), red.getPlayerType(), blue.getPlayerType(), ruleSet.getRulesType());
		
		Thread gameThread = new Thread(game);
		gameThread.start();
		
		JPanel turnViews = new JPanel();
		turnViews.setLayout(new BoxLayout(turnViews, BoxLayout.X_AXIS));
		
		JLabel r = new JLabel("Red Turn: ");
		JLabel b = new JLabel("Blue Turn: ");
		
		redTurn = new TurnViewer(PlayerColor.RED, game);
		
		turnViews.add(r);
		turnViews.add(redTurn);
		
		blueTurn = new TurnViewer(PlayerColor.BLUE, game);
		turnViews.add(b);
		turnViews.add(blueTurn);
		
		turnViews.setVisible(true);
		
		views.add(turnViews);
		
		JPanel gameBoard = new BoardPanel(game.getBoard());
		gameBoard.setVisible(true);
		
		board.add(gameBoard);
		game.addObserver((Observer) gameBoard);
		gameBoard.updateUI();
		
		((TurnViewer) redTurn).update(game, null);
		((TurnViewer) blueTurn).update(game, null);
		this.pack();
	}
	
	protected static void createAndShowGUI(){
		JFrame jframe = new GraphicalBoardView();
		ArrayList<JPanel> panes = new ArrayList<JPanel>();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel redPanel = new JPanel();
		redPanel.setLayout(new BoxLayout(redPanel, BoxLayout.X_AXIS));
		JComponent redLabel = new JLabel("Choose Red Player Type: ");
		redPanel.add(redLabel);
		redPanel.add(redPlayerType);
		panes.add(redPanel);
		
		JPanel bluePanel = new JPanel();
		bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.X_AXIS));
		JComponent blueLabel = new JLabel("Choose Blue Player Type: ");
		bluePanel.add(blueLabel);
		bluePanel.add(bluePlayerType);
		panes.add(bluePanel);
		
		JPanel rulesPanel = new JPanel();
		rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.X_AXIS));
		JComponent rulesLabel = new JLabel("Choose Rules Type: ");
		rulesPanel.add(rulesLabel);
		rulesPanel.add(rules);
		panes.add(rulesPanel);
		
		JPanel sizePanel = new JPanel();
		sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.X_AXIS));
		JComponent sizeLabel = new JLabel("Choose Board Size: ");
		sizePanel.add(sizeLabel);
		sizePanel.add(size);
		panes.add(sizePanel);
		
		JPanel startButton = new JPanel();
		startButton.add(start);
		panes.add(startButton);
	
		panes.add(views);
		panes.add(board);
		
		for(JPanel p : panes){
			p.setVisible(true);
			panel.add(p);
		}
			
		jframe.add(panel);
		jframe.setSize(600,300);
		jframe.setVisible(true);
		jframe.pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton s = (JButton) start;
		
		if (arg0.getActionCommand().equals(s.getActionCommand())){
			views.removeAll();
			board.removeAll();
			prepareGame();
		}
		this.repaint();
		// TODO Auto-generated method stub
		
	}

	public static Runnable setup() {
        return new Runnable(){
            public void run(){
                createAndShowGUI();
            }
        };
    }

}
