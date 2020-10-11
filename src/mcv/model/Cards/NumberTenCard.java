package mcv.model.Cards;

import java.util.ArrayList;

import helpers.MoveManager;
import helpers.Util;
import mcv.controller.Controller;
import mcv.model.Player;
/**
 * This class represents the number 10 Card.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class NumberTenCard extends NumberCard{

	public NumberTenCard(String img) {
		super(img, 10);
	}
	public void move(Player p) {
		ArrayList<String> options = new ArrayList<String>();
		int pos1 = Util.getForwardPossition(p.getPawn1(), 10, false);
		int pos2 = Util.getForwardPossition(p.getPawn2(), 10, false);
		int pos3 = Util.getBackwardPossition(p.getPawn1(), 1);
		int pos4 = Util.getBackwardPossition(p.getPawn2(), 1);
		if (pos1 != -1 && (pos1 == 0 || Util.getSameColorPawn(p, pos1) == null)) options.add("Move Pawn1 10 steps forward");
		if (pos2 != -1 && (pos2 == 0 || Util.getSameColorPawn(p, pos2) == null)) options.add("Move Pawn2 10 steps forward");
		if (pos3 != -1 && (pos3 == 0 || Util.getSameColorPawn(p, pos3) == null)) options.add("Move Pawn1 1 step backwards");
		if (pos4 != -1 && (pos4 == 0 || Util.getSameColorPawn(p, pos4) == null)) options.add("Move Pawn2 1 step backwards");
		if (options.isEmpty()) {
			MoveManager.forceFold(p);
			return;
		}
		String[] pos = new String[options.size()];
		int idx = 0;
		for (String s : options) pos[idx++] = s;
		String v = Controller.state.AIturn() ? pos[Util.getRandom(0, options.size() - 1)] : Util.getChoice("Choose a move to make:", pos);
		if (v.equals("Move Pawn1 10 steps forward")) {
			if (pos1 != 0) {
				Util.cleanSpot(pos1);
				p.getPawn1().setPosition(pos1);
			} else MoveManager.moveToHome(p, p.getPawn1());
		} else if (v.equals("Move Pawn2 10 steps forward")) {
			if (pos2 != 0) {
				Util.cleanSpot(pos2);
				p.getPawn2().setPosition(pos2);
			} else MoveManager.moveToHome(p, p.getPawn2());
		} else if (v.equals("Move Pawn1 1 step backwards")) {
			Util.cleanSpot(pos3);
			p.getPawn1().setPosition(pos3);
		} else if (v.equals("Move Pawn2 1 step backwards")) {
			Util.cleanSpot(pos4);
			p.getPawn2().setPosition(pos4);
		}
	}

}
