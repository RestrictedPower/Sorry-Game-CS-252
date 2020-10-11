package helpers;

import java.io.Serializable;


import mcv.model.Deck;
import mcv.model.Player;
import mcv.model.Turn;
import mcv.model.Cards.Card;

/**
 * This class represents the current GameState of the game
 * 
 * It contains the current model of the game and 
 * implements Serializeable in order to be saved
 * and loaded by the GameSaver class.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class GameState implements Serializable{
	private boolean againstAI;
	private boolean gameEnded;
	private Deck deck;
	private Player player1;
	private Player player2;
	private Card currentCard;
	private boolean hasPulledCard;
	private boolean canFold;
	private Turn turn;
	private String[] infoBox;
	public GameState(Deck d, Player p1, Player p2, Card c,boolean pulled, Turn t, String[] infoBox, boolean canFold, boolean againstAI) {
		this.deck = d;
		this.player1 = p1;
		this.player2 = p2;
		this.currentCard = c;
		this.hasPulledCard = pulled;
		this.turn = t;
		this.infoBox = infoBox;
		this.canFold = canFold;
		this.againstAI = againstAI;
		gameEnded = false;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	public Turn getTurn() {
		return turn;
	}
	public void setTurn(Turn turn) {
		this.turn = turn;
	}
	public Card getCurrentCard() {
		return currentCard;
	}
	public void setCurrentCard(Card currentCard) {
		this.currentCard = currentCard;
	}

	public boolean hasPulledCard() {
		return hasPulledCard;
	}

	public void setHasPulledCard(boolean hasPulledCard) {
		this.hasPulledCard = hasPulledCard;
	}

	public String[] getInfoBox() {
		return infoBox;
	}

	public void setInfoBox(String[] infoBox) {
		this.infoBox = infoBox;
	}

	public boolean canFold() {
		return canFold;
	}

	public void setCanFold(boolean canFold) {
		this.canFold = canFold;
	}

	public boolean isAgainstAI() {
		return againstAI;
	}

	public void setAgainstAI(boolean againstAI) {
		this.againstAI = againstAI;
	}
	public boolean AIturn() {
		return isAgainstAI()&&turn==Turn.Player2;
	}

	public boolean hasGameEnded() {
		return gameEnded;
	}

	public void gameEnded(String winner) {
		this.gameEnded = true;
		this.setInfoBox(new String[] {"Game Ended!",winner+" won!"});
	}
}
