package mcv.model.Cards;

import mcv.model.Player;
/**
 * This class represents a Number Card
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public abstract class NumberCard extends Card{
	private int number;
	public NumberCard(String img, int number) {
		super(img);
		this.number = number;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
    public abstract void move(Player p);
}
