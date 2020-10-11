package helpers;
import javax.swing.JOptionPane;

import mcv.controller.Controller;
import mcv.model.Color;
import mcv.model.Pawn;
import mcv.model.Player;
import mcv.model.Turn;
import mcv.model.Cards.Card;
import mcv.model.Cards.NumberTwoCard;

/**
 * This class is used to manage the moves a player could do by using a card.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class MoveManager {
	/**
	 * Manager of moves given a player and the card he pulled.
	 * 
	 * @param p The player that pulled the card.
	 * @param c The card that was pulled by the player.
	 */
	public static void move(Player p, Card c) {
		String color = p.getColor() == Color.RED ? "Red" : "Yellow";
		Controller.state.setInfoBox(new String[] { p.getName() + "'s turn (" + color + ")", "Play your card" });
		Controller.updateGraphics();
		c.move(p);
		Player winner = Util.won();
		boolean ai = Controller.state.AIturn();
		if (!(c instanceof NumberTwoCard))
			Controller.state.setTurn((Controller.state.getTurn() == Turn.Player1) ? Turn.Player2 : Turn.Player1);
		if (winner == null) {
			updateSlides();
			if (!Controller.state.canFold()) {
				Controller.state.setHasPulledCard(false);
				Controller.setBasicInfo();
				Controller.updateGraphics();
				if (Controller.state.AIturn())
					Controller.draw();
			}else if(ai) Controller.fold();
		} else {
			Controller.state.gameEnded(winner.getName());
			Controller.updateGraphics();
		}
	}
	
	/**
	 * Swaps 2 pawns' possitions.
	 * 
	 * @param p1 Pawn1
	 * @param p2 Pawn2
	 */
	public static void swap(Pawn p1, Pawn p2) {
		int p = p1.getPosition();
		p1.setPosition(p2.getPosition());
		p2.setPosition(p);
	}
	
	/**
	 * Moves the pawn of a player at it's start
	 * 
	 * @param pl The player that owns the pawn.
	 * @param p The pawn that should be moved to start.
	 */
	public static void moveToStart(Player pl, Pawn p) {
		Pawn p2 = pl.getPawn1().equals(p) ? pl.getPawn2() : pl.getPawn1();
		if (p.getColor() == Color.RED) {
			if (p2 == null || p2.getPosition() == 72)
				p.setPosition(71);
			else
				p.setPosition(72);
		} else {
			if (p2 == null || p2.getPosition() == 74)
				p.setPosition(73);
			else
				p.setPosition(74);
		}
	}

	/**
	 * Moves the pawn of a player at it's home
	 * 
	 * @param pl The player that owns the pawn.
	 * @param p The pawn that should be moved to home.
	 */
	public static void moveToHome(Player pl, Pawn p) {
		Pawn p2 = pl.getPawn1().equals(p) ? pl.getPawn2() : pl.getPawn1();
		if (p.getColor() == Color.RED) {
			if (p2 == null || p2.getPosition() == 76)
				p.setPosition(75);
			else
				p.setPosition(76);
		} else {
			if (p2 == null || p2.getPosition() == 78)
				p.setPosition(77);
			else
				p.setPosition(78);
		}
	}

	/**
	 * This function calls an the updateSlidePawn 4 times one for each pawn.
	 */
	private static void updateSlides() {
		updateSlidePawn(Controller.state.getPlayer1().getPawn1());
		updateSlidePawn(Controller.state.getPlayer1().getPawn2());
		updateSlidePawn(Controller.state.getPlayer2().getPawn1());
		updateSlidePawn(Controller.state.getPlayer2().getPawn2());
	}

	/**
	 * This function checks if a pawn is on a ladder and updates 
	 * the board accordingly.
	 * 
	 * @param p The pawn that should be checked.
	 */
	private static void updateSlidePawn(Pawn p) {
		int ls = Util.getLadderSize(p.getPosition());
		Color lc = Util.getLadderColor(p.getPosition());
		if (ls != -1 && lc != p.getColor()) {
			for (int i = p.getPosition() + 1; i < p.getPosition()+1+ls; i++) Util.cleanSpot(i);
			p.setPosition(p.getPosition() + ls);
		}
	}
	
	/**
	 * Force the current player to fold
	 */
	public static void forceFold(Player p) {
		Controller.state.setCanFold(true);
		if (!Controller.state.AIturn()) {
			String color = p.getColor() == Color.RED ? "Red" : "Yellow";
			Controller.state.setInfoBox(new String[] { p.getName() + "'s turn (" + color + ")", "Your only availiable move is fold!" });
			Controller.updateGraphics();
		}
	}
}
