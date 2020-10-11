package mcv.controller;

import javax.swing.JOptionPane;

import helpers.GameState;
import helpers.MoveManager;
import helpers.Util;
import mcv.model.Color;
import mcv.model.Deck;
import mcv.model.Pawn;
import mcv.model.Player;
import mcv.model.Turn;
import mcv.view.View;

/**
 * This is the main class of the game. The controller.
 * It is basically the brain of the game.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class Controller {
	public static View view;
	public static ClassLoader cldr;
	public static final int sqrSz = 45;
	public static GameState state;

	/**
	 * Main initialization of the game.
	 * 
	 * @param c The controller
	 */
	public static void init(Controller c) {
		cldr = c.getClass().getClassLoader();
		view = new View();
		view.setVisible(true);
		state = new GameState(new Deck(), new Player("Player1", Color.RED), new Player("Player2", Color.YELLOW), null, false, Turn.Player1, new String[] { "Welcome!" }, false,false);
		startGame();
	}

	/**
	 * Game starting function.
	 */
	public static void startGame() {
		state.getPlayer1().setPawn1(new Pawn(0, state.getPlayer1().getColor(), "resources/images/pawns/"
				+ (state.getPlayer1().getColor() == Color.RED ? "red" : "yellow") + "Pawn1.png"));
		state.getPlayer1().setPawn2(new Pawn(0, state.getPlayer1().getColor(), "resources/images/pawns/"
				+ (state.getPlayer1().getColor() == Color.RED ? "red" : "yellow") + "Pawn2.png"));
		state.getPlayer2().setPawn1(new Pawn(0, state.getPlayer2().getColor(), "resources/images/pawns/"
				+ (state.getPlayer2().getColor() == Color.RED ? "red" : "yellow") + "Pawn1.png"));
		state.getPlayer2().setPawn2(new Pawn(0, state.getPlayer2().getColor(), "resources/images/pawns/"
				+ (state.getPlayer2().getColor() == Color.RED ? "red" : "yellow") + "Pawn2.png"));
		MoveManager.moveToStart(state.getPlayer1(), state.getPlayer1().getPawn1());
		MoveManager.moveToStart(state.getPlayer1(), state.getPlayer1().getPawn2());
		MoveManager.moveToStart(state.getPlayer2(), state.getPlayer2().getPawn1());
		MoveManager.moveToStart(state.getPlayer2(), state.getPlayer2().getPawn2());
		updateGraphics();
		boolean againstAi = Util.getChoice("Would you like to play against AI?", new String[] {"Yes","No"}).equals("Yes");
		state.setAgainstAI(againstAi);
		state.getPlayer1().setName(Util.getText(againstAi?"Enter your name: ":"Enter player1's name"));
		state.getPlayer2().setName(againstAi?"AI":Util.getText("Enter player2's name"));
		setBasicInfo();
		updateGraphics();
	}

	/**
	 * Draw press event.
	 * Executed when the user presses the draw card button
	 */
	public static void draw() {
		if (state.hasPulledCard()) {
			JOptionPane.showMessageDialog(null, "You have already pulled a card!", "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Player p = state.getTurn() == Turn.Player1 ? state.getPlayer1() : state.getPlayer2();
		state.setCurrentCard(state.getDeck().getCard());
		state.setHasPulledCard(true);
		updateGraphics();
		MoveManager.move(p, state.getCurrentCard());
	}

	/**
	 * Changing the gamestate's info box to a basic state.
	 */
	public static void setBasicInfo() {
		String color = (state.getTurn() == Turn.Player1 ? state.getPlayer1().getColor()
				: state.getPlayer2().getColor()) == Color.RED ? "Red" : "Yellow";
		state.setInfoBox(new String[] { ("Turn: "
				+ (state.getTurn() == Turn.Player1 ? state.getPlayer1().getName() : state.getPlayer2().getName())) + "("
				+ color + ")", "Cards left: " + state.getDeck().getCardsLeft(), "Pull a card" });
	}
	
	
	/**
	 * Fold press event.
	 * Executed when the user presses the Fold button
	 */
	public static void fold() {
		state.setCanFold(false);
		state.setHasPulledCard(false);
		setBasicInfo();
		updateGraphics();
		if (Controller.state.AIturn()) Controller.draw();
	}

	/**
	 * Update the view due to the current state
	 */
	public static void updateGraphics() {
		view.repaint(state);
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		c.init(c);
	}
}
