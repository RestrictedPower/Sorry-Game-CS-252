package mcv.view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JLayeredPane;
/**
 * This class represents a JLayeredPaneExtension
 * basically a JLayeredPane with an image painted
 * on it.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class JLayeredPaneExtension extends JLayeredPane {
	private Image image;
	public JLayeredPaneExtension(Image img) {
		this.image = img;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}