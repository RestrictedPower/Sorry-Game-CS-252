package mcv.model;

import java.io.Serializable;
/**
 * This class represents a pawn.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class Pawn implements Serializable{
	private int position;
	private Color color;
	private String image;
	public Pawn(int position, Color color,String image) {
		this.position = position;
		this.color = color;
		this.image = image;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isSafe() {
		for(int i = 61; i<=78; i++) if(getPosition() == i) return true;
		return false;
	}
	
	public boolean isInStart() {
		for(int i = 71; i<=74; i++) if(getPosition() == i) return true;
		return false;
	}
	
	public boolean isInHome() {
		for(int i = 75; i<=78; i++) if(getPosition() == i) return true;
		return false;
	}
}
