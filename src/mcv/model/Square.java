package mcv.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import mcv.controller.Controller;
import mcv.view.JLayeredPaneExtension;

/**
 * This class represents a board Square.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class Square {
	private int x;
	private int y;
	private String image;
	private String defaultImage;
	private boolean safe;
	public Square(int x, int y, boolean safe) {
		this.x = x;
		this.y = y;
		this.safe = safe;
	}
	public Square(int x, int y, boolean safe, String defaultImage) {
		this.x = x;
		this.y = y;
		this.safe = safe;
		this.defaultImage = defaultImage;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	boolean isWhite() {
		return image == null;
	}
	public boolean isSafe() {
		return safe;
	}
	public void setSafe(boolean safe) {
		this.safe = safe;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public JComponent toImage() {
		if(isWhite()) {
			if(defaultImage!=null) {
				URL imageURL = Controller.cldr.getResource(defaultImage);
		        Image image = new ImageIcon(imageURL).getImage();
		        JLayeredPaneExtension ex = new JLayeredPaneExtension(image);
		        ex.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		        ex.setSize(new Dimension(Controller.sqrSz,Controller.sqrSz));
		        ex.setBounds(getX(), getY(), Controller.sqrSz, Controller.sqrSz);
		        return ex;
			}
	        JPanel sqr = new JPanel(); 
	        sqr.setBounds(Controller.sqrSz, Controller.sqrSz, Controller.sqrSz, Controller.sqrSz);
	        sqr.setBackground(Color.white);
	        sqr.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	        sqr.setBounds(getX(), getY(), Controller.sqrSz, Controller.sqrSz);
	        return sqr;
		}
		URL imageURL = Controller.cldr.getResource(image);
        Image image = new ImageIcon(imageURL).getImage().getScaledInstance(Controller.sqrSz , Controller.sqrSz, 0);
        JLayeredPaneExtension ex = new JLayeredPaneExtension(image);
        ex.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        ex.setSize(new Dimension(Controller.sqrSz,Controller.sqrSz));
        ex.setBounds(getX(), getY(), Controller.sqrSz, Controller.sqrSz);
        return ex;
	}
	public String getDefaultImage() {
		return defaultImage;
	}
	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}
}
