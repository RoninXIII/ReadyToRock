/**
 * 
 */
package readyToRock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import readyToRock.Cards;

/**
 * @author mario
 *
 */
public class Player {

	private String color;

	private ArrayList<String> cards;

	private int actions = 2;
	private Button cell = new Button();
	private int distance = 5;
	public boolean isTurn = false;
	public String cardToPlay;
	private ImageView plectrum = new ImageView();
	public FactHandle handleOfPlayer;
	public KieSession workingMemory;
	
	public Player(Cards deck, String color, KieSession wm) {
		super();

		this.color = color;
	
		this.cards = new ArrayList<String>();
		this.workingMemory = wm;
		

		if (color == "Blue") {
			FileInputStream input;
			try {
				input = new FileInputStream("C:/Users/mario/Desktop/ready/plettro4.png");
				Image image = new Image(input, 80, 80, false, false);
				ImageView imageView = new ImageView(image);
				this.plectrum = imageView;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (color == "Red") {
			FileInputStream input;
			try {
				input = new FileInputStream("C:/Users/mario/Desktop/ready/plettro2.png");
				Image image = new Image(input, 80, 80, false, false);
				ImageView imageView = new ImageView(image);
				this.plectrum = imageView;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			FileInputStream input;
			try {
				input = new FileInputStream("C:/Users/mario/Desktop/ready/plettro3.png");
				Image image = new Image(input, 80, 80, false, false);
				ImageView imageView = new ImageView(image);
				this.plectrum = imageView;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public ImageView getPlectrum() {
		return plectrum;
	}

	public void playCard(String playedCard, KieSession wm) {

		this.cardToPlay = playedCard;
		wm.fireAllRules();

	}

	public boolean hasWon() {

		if (this.distance == 0) {

			return true;

		} else
			return false;

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
		this.cardToPlay ="";
		this.workingMemory.update(handleOfPlayer, this);
		this.workingMemory.update(deck.handleOfCards, deck);

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
				+ ", isTurn=" + isTurn + ", cardToPlay=" + cardToPlay + ", position= [" + GridPane.getRowIndex(cell) + " " + GridPane.getColumnIndex(cell) + "] ]";
	}

	public boolean checkPosition(int row, int column) {

		if (row == GridPane.getRowIndex(cell) && column == GridPane.getColumnIndex(cell)) {

			return true;

		} else
			return false;

	}

	public Button getCell() {
		return cell;
	}

	public void setCell(Button cell) {
		this.cell.setOnAction(null);
		this.cell.setId(null);
		this.cell = cell;
		this.cell.setId(getColor());
		this.cell.setGraphic(this.getPlectrum());
		this.workingMemory.update(handleOfPlayer, this);
	}
	
	
	public Button getNextCell(Board board) {

		int row = GridPane.getRowIndex(this.cell);
		int column = GridPane.getColumnIndex(this.cell);
		Button nextCell = board.buttons[column][row - 1];
		this.cardToPlay ="";
		return nextCell;


	}

	public int[] getPosition() {
		int[] pos = { GridPane.getRowIndex(cell), GridPane.getColumnIndex(cell) };
		return pos;
	}

	

	public ArrayList<String> getCards() {
		return cards;
	}

	public void setCards(ArrayList<String> cards) {
		this.cards = cards;
	}

}
