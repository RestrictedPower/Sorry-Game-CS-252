package mcv.model.Cards;

import java.util.ArrayList;

import helpers.MoveManager;
import helpers.Util;
import mcv.controller.Controller;
import mcv.model.Player;
/**
 * This class represents a simple number Card.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class SimpleNumberCard extends NumberCard{

	public SimpleNumberCard(String img, int number) {
		super(img, number);
	}
	
	public void move(Player p) {
		ArrayList<String> options = new ArrayList<String>();
		int pos1 = Util.getForwardPossition(p.getPawn1(), getNumber(), false);
		int pos2 = Util.getForwardPossition(p.getPawn2(), getNumber(), false);
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
			if (pos1 != 0) {
				Util.cleanSpot(pos1);
				p.getPawn1().setPosition(pos1);
			} else MoveManager.moveToHome(p, p.getPawn1());
		} else if (v.equals("Pawn 2")) {
			if (pos2 != 0) {
				Util.cleanSpot(pos2);
				p.getPawn2().setPosition(pos2);
			} else MoveManager.moveToHome(p, p.getPawn2());
		}
	}
}
