/**
 * 
 */
package readyToRock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	private String path;
	private int actions = 2;
	private Button cell = new Button();
	private int distance = 5;
	public boolean isTurn = true;
	public String cardToPlay = "";
	private ArrayList<String> oldCards = new ArrayList<String>();
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

		} else {
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
		}

	}

	public ImageView getPlectrum() {
		return plectrum;
	}

	public void playCard(String playedCard, KieSession wm) {

		this.cardToPlay = playedCard;
		wm.fireAllRules();

	}

	public void hasWon() {

		System.out.println("Congratulations, you reached the stage!");
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Congratulations, you reached the stage!");
		Optional<ButtonType> result = alert.showAndWait();
		

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
		// this.cardToPlay = "";
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
		return "Player [color=" + color + ", cards=" + cards + ", path=" + path + ", actions=" + actions + ", distance="
				+ distance + ", isTurn=" + isTurn + ", cardToPlay=" + cardToPlay + ", oldCards=" + oldCards
				+ ", position=" + GridPane.getRowIndex(cell) + " " + GridPane.getColumnIndex(cell) + "]";
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

	public String getPath() {

		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPlayerAlert(String playedCard, Player player2) {

		Cards deck = new Cards();

		this.getCell().setOnAction(event -> {

			ButtonType[] cards = new ButtonType[this.getCards().size() + 3];

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Alert");
			alert.setHeaderText("Choose the card to play!");
			alert.setContentText(actions + " actions left");
			if (playedCard != null && playedCard != "Wall") {
				this.path = playedCard;
				ImageView image = deck.getImages().get(playedCard);
				alert.setGraphic(image);
			}
			int i = 0;
			for (String card : this.getCards()) {
				cards[i] = new ButtonType(card);

				alert.getButtonTypes().add(cards[i]);
				i++;
			}

			cards[getCards().size()] = new ButtonType("Finish your turn");
			cards[getCards().size() + 1] = new ButtonType("Discard one card");
			cards[getCards().size() + 2] = new ButtonType("Draw one card");

			alert.getButtonTypes().addAll(cards[getCards().size()], cards[getCards().size() + 1],
					cards[getCards().size() + 2]);

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() != ButtonType.OK && result.get() != ButtonType.CANCEL) {

				this.cardToPlay = result.get().getText();
				this.workingMemory.update(this.handleOfPlayer, this);
				if ( actions > 0) {
					// check if possible to play the card
					// if possible remove the played card from player's hand
		
					this.workingMemory.fireAllRules();
					this.workingMemory.update(this.handleOfPlayer, this);
					this.workingMemory.update(player2.handleOfPlayer, player2);

				} else if (cardToPlay == "Finish your turn") {

				
					this.workingMemory.fireAllRules();
					this.workingMemory.update(this.handleOfPlayer, this);
					this.workingMemory.update(player2.handleOfPlayer, player2);
					this.workingMemory.fireAllRules();
				}

			}

		});

	}

	public boolean hasWall(Button cell) {

		if (cell.getId() != null) {
			return true;
		} else
			return false;
	}

	public void setCell(Button cell, Player player2) {
		Board board = new Board();

		if (!this.checkLimit(cell, board)) {
			this.cell.setOnAction(null);
			this.cell.setId(null);
			this.cell = cell;

			this.cell.setId(getColor());
			this.cell.setGraphic(this.getPlectrum());
			this.setPlayerAlert(cardToPlay, player2);
			// this.cardToPlay = "";
			this.workingMemory.update(handleOfPlayer, this);
		} else {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("You can't move there!");
			alert.setContentText(null);

			alert.showAndWait();
		}

	}

	public boolean checkLimit(Button cell, Board board) {

		if (GridPane.getRowIndex(cell) == 0) {
			return true;
		} else {

			return false;
		}

	}

	public boolean isValid() {

		switch (this.cardToPlay) {
		case "Straight":

			if (path == "Left/Right" || path == "Turn-right" || path == "Turn-left")
				return false;
			else
				return true;

		case "StraightLR":

			if (path == "Straight")
				return false;
			else
				return true;
		case "Turn-right":

			if (path == "Turn-right" || path == "Left/Right" || path == "Turn-left")
				return false;
			else
				return true;

		case "Left/Right":

			if (path == "Straight")
				return false;
			else
				return true;

		case "Turn-left":

			if (path == "Turn-left" || path == "Left/Right" || path == "Turn-right")
				return false;
			else
				return true;

		default:
			return true;
		}

	}

	public String findCardToPlay() {
		Cards deck = new Cards();
		Object[] arr = deck.pathCards.keySet().toArray();
		Object[] arr2 = deck.flashCards.keySet().toArray();
		Object[] arr3 = deck.wallCards.keySet().toArray();

		for (int i = 0; i < arr.length; i++) {

			if (cards.contains(arr[i]) && oldCards.contains(arr[i]) == false)
				return (String) arr[i];
		}

		for (int i = 0; i < arr2.length; i++) {

			if (cards.contains(arr2[i]) && oldCards.contains(arr2[i]) == false)
				return (String) arr2[i];
		}

		for (int i = 0; i < arr3.length; i++) {

			if (cards.contains(arr3[i]) && oldCards.contains(arr3[i]) == false)
				return (String) arr3[i];
		}

		return "";

	}

	public ArrayList<String> getOldCards() {
		return oldCards;
	}

	public void setOldCards(ArrayList<String> oldCards) {
		this.oldCards = oldCards;
	}

	public void cpuTurn() {

		this.workingMemory.update(handleOfPlayer, this);
		this.workingMemory.fireAllRules();
		this.workingMemory.update(handleOfPlayer, this);
	}

	public Button getTopCell(Board board) {

		int row = GridPane.getRowIndex(this.cell);
		int column = GridPane.getColumnIndex(this.cell);
		Button nextCell = board.buttons[column][row - 1];

		return nextCell;

	}

	public Button getRightCell(Board board) {

		int row = GridPane.getRowIndex(this.cell);
		int column = GridPane.getColumnIndex(this.cell);
		Button rightCell = board.buttons[column + 1][row];

		return rightCell;

	}

	public Button getLeftCell(Board board) {

		int row = GridPane.getRowIndex(this.cell);
		int column = GridPane.getColumnIndex(this.cell);
		Button leftCell = board.buttons[column - 1][row];

		return leftCell;

	}

	public Button getTopRightCell(Board board) {

		int row = GridPane.getRowIndex(this.cell);
		int column = GridPane.getColumnIndex(this.cell);
		Button topRightCell = board.buttons[column + 1][row - 1];

		return topRightCell;

	}

	public Button getTopLeftCell(Board board) {

		int row = GridPane.getRowIndex(this.cell);
		int column = GridPane.getColumnIndex(this.cell);
		Button leftCell = board.buttons[column - 1][row - 1];

		return leftCell;

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
