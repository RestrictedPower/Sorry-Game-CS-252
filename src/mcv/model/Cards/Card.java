package mcv.model.Cards;

import java.io.Serializable;
import java.util.ArrayList;

import mcv.model.Player;
/**
 * This class represents a basic Card.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public abstract class Card implements Serializable{
    private String img;
    public Card(String img) {
    	this.img = img;
    }
    public String getImage() {
        return this.img;
    }

    public void setImage(String img) {
        this.img = img;
    }
    public abstract void move(Player p);
}
