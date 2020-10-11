package mcv.model.Cards;

import java.util.ArrayList;

import helpers.MoveManager;
import helpers.Util;
import mcv.controller.Controller;
import mcv.model.Player;
/**
 * This class represents the Number 11 Card.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class NumberElevenCard extends NumberCard{

	public NumberElevenCard(String img) {
		super(img, 11);
	}
	
	@Override
	public  void move(Player p) {
		ArrayList<String> options = new ArrayList<String>();
		boolean addFoldOption = false;
		int pos1 = Util.getForwardPossition(p.getPawn1(), 11, false);
		int pos2 = Util.getForwardPossition(p.getPawn2(), 11, false);
		if (pos1 != -1 && (pos1 == 0 || Util.getSameColorPawn(p, pos1) == null)) options.add("Move Pawn1 11 spots forward");
		else addFoldOption = true;
		if (pos2 != -1 && (pos2 == 0 || Util.getSameColorPawn(p, pos2) == null)) {
			options.add("Move Pawn2 11 spots forward");
			addFoldOption = false;
		}
		Player enemy = Util.getEnemy(p);
		if (!p.getPawn1().isSafe()) {
			if (!enemy.getPawn1().isSafe()) options.add("Swap your own Pawn1 with the enemy Pawn1");
			if (!enemy.getPawn2().isSafe()) options.add("Swap your own Pawn1 with the enemy Pawn2");
		}
		if (!p.getPawn2().isSafe()) {
			if (!enemy.getPawn1().isSafe()) options.add("Swap your own Pawn2 with the enemy Pawn1");
			if (!enemy.getPawn2().isSafe()) options.add("Swap your own Pawn2 with the enemy Pawn2");
		}
		if (addFoldOption) options.add("Fold");
		if (options.isEmpty() || (options.size() == 1 && options.get(0).equals("Fold"))) {
			MoveManager.forceFold(p);
			return;
		}
		String[] pos = new String[options.size()];
		int idx = 0;
		for (String s : options) pos[idx++] = s;
		String v = Controller.state.AIturn() ? pos[Util.getRandom(0, options.size() - 1)] : Util.getChoice("Choose a move to make:", pos);
		if (v.equals("Move Pawn1 11 spots forward")) {
			if (pos1 != 0) {
				Util.cleanSpot(pos1);
				p.getPawn1().setPosition(pos1);
			} else MoveManager.moveToHome(p, p.getPawn1());
		} else if (v.equals("Move Pawn2 11 spots forward")) {
			if (pos2 != 0) {
				Util.cleanSpot(pos2);
				p.getPawn2().setPosition(pos2);
			} else MoveManager.moveToHome(p, p.getPawn2());
		} else if (v.equals("Swap your own Pawn1 with the enemy Pawn1")) {
			MoveManager.swap(p.getPawn1(), enemy.getPawn1());
		} else if (v.equals("Swap your own Pawn1 with the enemy Pawn2")) {
			MoveManager.swap(p.getPawn1(), enemy.getPawn2());
		} else if (v.equals("Swap your own Pawn2 with the enemy Pawn1")) {
			MoveManager.swap(p.getPawn2(), enemy.getPawn1());
		} else if (v.equals("Swap your own Pawn2 with the enemy Pawn2")) {
			MoveManager.swap(p.getPawn2(), enemy.getPawn2());
		} else if (v.equals("Fold")) {
			Controller.state.setCanFold(true);
		}
	}
}
