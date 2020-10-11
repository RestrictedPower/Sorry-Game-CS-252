package mcv.model.Cards;

import java.util.ArrayList;

import helpers.MoveManager;
import helpers.Util;
import mcv.controller.Controller;
import mcv.model.Player;
/**
 * This class represents the number 4 Card.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class NumberFourCard extends NumberCard{

	public NumberFourCard(String img) {
		super(img, 4);
	}
	
	public void move(Player p) {
		ArrayList<String> options = new ArrayList<String>();
		int pos1 = Util.getBackwardPossition(p.getPawn1(), 4);
		int pos2 = Util.getBackwardPossition(p.getPawn2(), 4);
		if (pos1 != -1 && (pos1 == 0 || Util.getSameColorPawn(p, pos1) == null)) options.add("Pawn 1");
		if (pos2 != -1 && (pos2 == 0 || Util.getSameColorPawn(p, pos2) == null)) options.add("Pawn 2");
		if (options.isEmpty()) {
			MoveManager.forceFold(p);
			return;
		}
		String[] pos = new String[options.size()];
		int idx = 0;
		for (String s : options) pos[idx++] = s;
		String v = Controller.state.AIturn() ? pos[Util.getRandom(0, options.size() - 1)] : Util.getChoice("Choose a pawn to make that move:", pos);
		if (v.equals("Pawn 1")) {
			Util.cleanSpot(pos1);
			p.getPawn1().setPosition(pos1);
		} else if (v.equals("Pawn 2")) {
			Util.cleanSpot(pos2);
			p.getPawn2().setPosition(pos2);
		}
	}
}
