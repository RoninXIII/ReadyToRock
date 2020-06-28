/**
 * 
 */
package readyToRock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.apache.tools.ant.taskdefs.WaitFor;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import readyToRock.Cards;

/**
 * @author mario
 *
 */
public class Player {

	private String color;

	private ArrayList<String> cards;

	private int actions ;
	private Button cell = new Button();
	private int distance = 5;
	public boolean isTurn = false;
	

	
	public Player(Cards deck,String color) {
		super();
		
		this.color = color;
		this.actions = 0;
		this.cards = new ArrayList<String>();
		
		
		for (int i = 0; i < 4; i++) {
			
			this.drawCard(deck);
		}
		
		
		
	}
	
	
	

	
	
	
	public void playCard(String playedCard){
		
		
		
	}
	
	
	public boolean hasWon() {
		
		if(this.distance == 0) {
			
			return true;
			
		}else return false;
		
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
		return "Player [color=" + color + ", cards=" + cards + ", actions=" + actions + ", distance=" + distance
				+" position= ["+GridPane.getRowIndex(cell)+" "+GridPane.getColumnIndex(cell)+ "] ]";
	}



	
	
	
	
	public boolean checkPosition(int row,int column) {
		
		if(row == GridPane.getRowIndex(cell) && column == GridPane.getColumnIndex(cell)) {
			
			return true;
			
		}else return false;
		
		
	}
	
	

	
	public Button getCell() {
		return cell;
	}

	public int[] getPosition() {
		int[] pos = {GridPane.getRowIndex(cell), GridPane.getColumnIndex(cell)};
		return pos;
	}

	public void setPosition(Button cell) {
		
		this.cell = cell;
		
	}

	public ArrayList<String> getCards() {
		return cards;
	}

	public void setCards(ArrayList<String> cards) {
		this.cards = cards;
	}

}
