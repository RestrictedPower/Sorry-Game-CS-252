package helpers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import mcv.controller.Controller;
import mcv.model.Color;
import mcv.model.Deck;
import mcv.model.Player;
import mcv.model.Turn;
/**
 * This class is used to perform actions on the user's clicks.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class MouseClickListener implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof JButton) {
			if(Controller.state.hasGameEnded()) return;
			String but = ((JButton) e.getSource()).getText();
			if (but.equalsIgnoreCase("Fold"))Controller.fold();
			else Controller.draw();
		}else {
			String but = ((JMenu) e.getSource()).getText();
			if(but.equalsIgnoreCase("New Game")) {
				Controller.state = new GameState(new Deck(), new Player("Player1", Color.RED), new Player("Player2", Color.YELLOW), null,
						false, Turn.Player1, new String[] { "Welcome!" }, false,false);
				Controller.startGame();
			}else if(but.equalsIgnoreCase("Save Game")) {
				if(GameSaver.saveGame()) JOptionPane.showMessageDialog(null,"Game saved!");
				else JOptionPane.showMessageDialog(null, "Could not save game", "Error",JOptionPane.ERROR_MESSAGE);
			}else if(but.equalsIgnoreCase("Continue Saved Game")) {
				GameState state = GameSaver.recoverGame();
				if(state == null) JOptionPane.showMessageDialog(null, "There is no saved game!", "Error",JOptionPane.ERROR_MESSAGE);
				else {
					JOptionPane.showMessageDialog(null,"Game loaded!");
					Controller.state = state;
					Controller.updateGraphics();
				}
			}else {
				System.exit(0);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
