/**
 * 
 */
package readyToRock;

import java.util.ArrayList;

import readyToRock.Cards;

/**
 * @author mario
 *
 */
public class Player {

	private String color;
	// private int cardsNumber;
	private ArrayList<String> cards;

	private int actions = 2;

	private int distance = 5;
	private int[][] posBoard;
	// attributo per indicare le mosse valide

	
	public Player(String color,Cards deck) {
		super();
		this.color = color;
		this.cards = new ArrayList<String>();
		
		for (int i = 0; i < 4; i++) {
			
			this.drawCard(deck);
		}
		
		
		
	}
	
	
	public void drawCard(Cards deck) {

		String randomType = deck.getRandomType();
		String randomCard;

		switch (randomType) {
		case "Path":

			randomCard = deck.getRandomCard(deck.pathCards);
			this.cards.add(randomCard);
			deck.removeCardFromDeck(deck.pathCards, randomCard);

			break;

		case "Flash":

			randomCard = deck.getRandomCard(deck.flashCards);
			this.cards.add(randomCard);
			deck.removeCardFromDeck(deck.flashCards, randomCard);

			break;
		case "Wall":

			randomCard = deck.getRandomCard(deck.wallCards);
			this.cards.add(randomCard);
			deck.removeCardFromDeck(deck.wallCards, randomCard);

			break;
		}

	}



	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getActions() {
		return actions;
	}

	public void setActions(int actions) {
		this.actions = actions;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Player [color=" + color + ", cards=" + cards + ", actions=" + actions + ", distance=" + distance + "]";
	}

	public int[][] getPosBoard() {
		return posBoard;
	}

	public void setPosBoard(int[][] posBoard) {
		this.posBoard = posBoard;
	}

	public ArrayList<String> getCards() {
		return cards;
	}

	public void setCards(ArrayList<String> cards) {
		this.cards = cards;
	}

}
