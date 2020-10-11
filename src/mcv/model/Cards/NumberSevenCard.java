package mcv.model.Cards;

import java.util.ArrayList;

import helpers.MoveManager;
import helpers.Util;
import mcv.controller.Controller;
import mcv.model.Player;
/**
 * This class represents the number 7 Card.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class NumberSevenCard extends NumberCard{

	public NumberSevenCard(String img) {
		super(img, 7);
	}
	
	public void move(Player p) {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i <= 7; i++) {
			int m1 = i, m2 = 7 - i;
			if ((m1 == 0) && p.getPawn1().isInStart() && (!p.getPawn2().isInStart())) {
				int pos2 = Util.getForwardPossition(p.getPawn2(), m2, false);
				if (pos2 == -1) continue;
				options.add("Move Pawn1 (" + m1 + ") moves and Pawn2 (" + m2 + ") moves.");
			} else if ((m2 == 0) && (!p.getPawn1().isInStart()) && p.getPawn2().isInStart()) {
				int pos1 = Util.getForwardPossition(p.getPawn1(), m1, false);
				if (pos1 == -1) continue;
				options.add("Move Pawn1 (" + m1 + ") moves and Pawn2 (" + m2 + ") moves.");
			} else {
				int pos1 = Util.getForwardPossition(p.getPawn1(), m1, false);
				int pos2 = Util.getForwardPossition(p.getPawn2(), m2, false);
				if ((pos1 == pos2 && pos1!=0) || pos1 == -1 || pos2 == -1) continue;
				options.add("Move Pawn1 (" + m1 + ") moves and Pawn2 (" + m2 + ") moves.");
			}
		}
		if (options.isEmpty()) {
			MoveManager.forceFold(p);
			return;
		}
		String[] pos = new String[options.size()];
		int idx = 0;
		for (String s : options) pos[idx++] = s;
		String v = Controller.state.AIturn() ? pos[Util.getRandom(0, options.size() - 1)] : Util.getChoice("Choose a move to make:", pos);
		for (int i = 0; i <= 7; i++) {
			int m1 = i, m2 = 7 - i;
			if (v.equalsIgnoreCase("Move Pawn1 (" + m1 + ") moves and Pawn2 (" + m2 + ") moves.")) {
				int pos1 = Util.getForwardPossition(p.getPawn1(), m1, false);
				if (m1 != 0) {
					if (pos1 != 0) {
						Util.cleanSpot(pos1);
						p.getPawn1().setPosition(pos1);
					} else MoveManager.moveToHome(p, p.getPawn1());
				}
				int pos2 = Util.getForwardPossition(p.getPawn2(), m2, false);
				if (m2 != 0) {
					if (pos2 != 0) {
						Util.cleanSpot(pos2);
						p.getPawn2().setPosition(pos2);
					} else MoveManager.moveToHome(p, p.getPawn2());
				}
			}
		}
	}
}
