package mcv.model.Cards;

import java.util.ArrayList;

import helpers.MoveManager;
import helpers.Util;
import mcv.controller.Controller;
import mcv.model.Player;
/**
 * This class represents a Sorry Card.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class SorryCard extends Card{
	
	public SorryCard(String img) {
		super(img);
	}
	
	public void move(Player p) {
		ArrayList<String> options = new ArrayList<String>();
		Player enemy = Util.getEnemy(p);
		if (p.getPawn1().isInStart()) {
			if (!enemy.getPawn1().isSafe())
				options.add("Move your Pawn1 to the enemy Pawn1 position and send them back to start");
			if (!enemy.getPawn2().isSafe())
				options.add("Move your Pawn1 to the enemy Pawn2 position and send them back to start");
		}
		if (p.getPawn2().isInStart()) {
			if (!enemy.getPawn1().isSafe())
				options.add("Move your Pawn2 to the enemy Pawn1 position and send them back to start");
			if (!enemy.getPawn2().isSafe())
				options.add("Move your Pawn2 to the enemy Pawn2 position and send them back to start");
		}

		if (options.isEmpty()) {
			MoveManager.forceFold(p);
			return;
		}

		String[] pos = new String[options.size()];
		int idx = 0;
		for (String s : options)
			pos[idx++] = s;
		String v = Controller.state.AIturn() ? pos[Util.getRandom(0, options.size() - 1)]
				: Util.getChoice("Choose a move to make:", pos);
		if (v.equals("Move your Pawn1 to the enemy Pawn1 position and send them back to start")) {
			p.getPawn1().setPosition(enemy.getPawn1().getPosition());
			MoveManager.moveToStart(enemy, enemy.getPawn1());
		} else if (v.equals("Move your Pawn1 to the enemy Pawn2 position and send them back to start")) {
			p.getPawn1().setPosition(enemy.getPawn2().getPosition());
			MoveManager.moveToStart(enemy, enemy.getPawn2());
		} else if (v.equals("Move your Pawn2 to the enemy Pawn1 position and send them back to start")) {
			p.getPawn2().setPosition(enemy.getPawn1().getPosition());
			MoveManager.moveToStart(enemy, enemy.getPawn1());
		} else if (v.equals("Move your Pawn2 to the enemy Pawn2 position and send them back to start")) {
			p.getPawn2().setPosition(enemy.getPawn2().getPosition());
			MoveManager.moveToStart(enemy, enemy.getPawn2());
		}
	}

}
