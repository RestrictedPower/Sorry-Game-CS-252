package mcv.model;

import java.io.Serializable;

/**
 * This class represents a Player.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class Player implements Serializable{
	private Color color;
	private String name;
	private Pawn pawn1;
	private Pawn pawn2;
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Pawn getPawn1() {
		return pawn1;
	}
	public void setPawn1(Pawn pawn1) {
		this.pawn1 = pawn1;
	}
	public Pawn getPawn2() {
		return pawn2;
	}
	public void setPawn2(Pawn pawn2) {
		this.pawn2 = pawn2;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
