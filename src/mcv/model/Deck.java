package mcv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import mcv.controller.Controller;
import mcv.model.Cards.Card;
import mcv.model.Cards.NumberElevenCard;
import mcv.model.Cards.NumberFourCard;
import mcv.model.Cards.NumberOneCard;
import mcv.model.Cards.NumberSevenCard;
import mcv.model.Cards.NumberTenCard;
import mcv.model.Cards.NumberTwoCard;
import mcv.model.Cards.SimpleNumberCard;
import mcv.model.Cards.SorryCard;
/**
 * This class represents a Deck.
 * At first it loads all the cards and shuffles them
 * and supports a getCard() function that draws them one 
 * by one till they end. After they end they are shuffled 
 * reinserted to the stack.
 * 
 * @author Kostas Anemozalis (CSD4149)
 */
public class Deck implements Serializable{
    private ArrayList<Card> usedCards;
    private Stack<Card> cards;
    public Deck() {
    	cards = new Stack<Card>();
    	usedCards = new ArrayList<Card>();
    	loadCards();
    }
    
	/**
	 * Function that loads the cards and their image URLs. 
	 */
    public void loadCards() {
    	for(int i = 0; i<4; i++) {
    		usedCards.add(new NumberOneCard("resources/images/cards/card1.png"));
    		usedCards.add(new NumberTwoCard("resources/images/cards/card2.png"));
    		usedCards.add(new NumberFourCard("resources/images/cards/card4.png"));
    		usedCards.add(new NumberSevenCard("resources/images/cards/card7.png"));
    		usedCards.add(new NumberTenCard("resources/images/cards/card10.png"));
    		usedCards.add(new NumberElevenCard("resources/images/cards/card11.png"));
    		for(int j = 3; j<=12; j++) {
    			if(j == 4 || j == 6 || j == 7||j == 9 || j == 10 || j == 11) continue;
    			usedCards.add(new SimpleNumberCard("resources/images/cards/card"+j+".png",j));
    		}
    		usedCards.add(new SorryCard("resources/images/cards/cardSorry.png"));
    	}
    	resetCards();
    }
    
	/**
	 * Pops a card from the stack.
	 * 
	 * @return The top card
	 */
    public Card getCard() {
    	Card c = cards.pop();
    	usedCards.add(c);
    	if(getCardsLeft()==0) resetCards();
        return c;
    }
    
	/**
	 * Resets the cards.
	 */
    public void resetCards() {
    	if(usedCards.isEmpty()&&cards.isEmpty())loadCards();
    	while(!cards.isEmpty()) usedCards.add(cards.pop());
    	Collections.shuffle(usedCards);
    	for(Card c : usedCards) cards.add(c);
    	usedCards.clear();
    	if(Controller.state!=null) Controller.state.setCurrentCard(null);
    }
    
	/**
	 * Returns the amount of cards left in the stack.
	 * 
	 * @return The amount of cards.
	 */
    public int getCardsLeft() {
    	return cards.size();
    }
}
