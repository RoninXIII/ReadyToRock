/**
 * 
 */
package readyToRock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.kie.api.runtime.rule.FactHandle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author mario
 *
 */
public class Cards {

	@Override
	public String toString() {
		return "Cards [totalCards=" + totalCards + ", type=" + Arrays.toString(type) + ", pathCards=" + pathCards
				+ ", flashCards=" + flashCards + ", wallCards=" + wallCards + "]";
	}

	private int totalCards;

	private String[] type = { "Path", "Flash", "Wall" };

	public Map<String, Integer> pathCards;

	public Map<String, Integer> flashCards;

	public Map<String, Integer> wallCards;
	private Map<String, ImageView> images;

	public FactHandle handleOfCards;

	public Cards() {
		super();

		images = new HashMap<String, ImageView>();

		try {
			FileInputStream input = new FileInputStream("C:/Users/mario/Desktop/ready/straight.jpg");
			Image image = new Image(input, 200, 100, false, true);
			ImageView imageView = new ImageView(image);
			FileInputStream input2 = new FileInputStream("C:/Users/mario/Desktop/ready/cross.jpg");
			Image image2 = new Image(input2, 200, 100, false, true);
			ImageView imageView2 = new ImageView(image2);
			FileInputStream input3 = new FileInputStream("C:/Users/mario/Desktop/ready/turnRight.jpg");
			Image image3 = new Image(input3, 200, 100, false, true);
			ImageView imageView3 = new ImageView(image3);
			FileInputStream input4 = new FileInputStream("C:/Users/mario/Desktop/ready/leftRight.jpg");
			Image image4 = new Image(input4, 200, 100, false, true);
			ImageView imageView4 = new ImageView(image4);
			FileInputStream input5 = new FileInputStream("C:/Users/mario/Desktop/ready/turnLeft.jpg");
			Image image5 = new Image(input5, 200, 100, false, true);
			ImageView imageView5 = new ImageView(image5);
			FileInputStream input6 = new FileInputStream("C:/Users/mario/Desktop/ready/straightLR.jpg");
			Image image6 = new Image(input6, 200, 100, false, true);
			ImageView imageView6 = new ImageView(image6);
			FileInputStream input7 = new FileInputStream("C:/Users/mario/Desktop/ready/wall.jpg");
			Image image7 = new Image(input7, 200, 100, false, true);
			ImageView imageView7 = new ImageView(image7);

			images.put("Straight", imageView);
			images.put("Cross", imageView2);
			images.put("Turn-right", imageView3);
			images.put("Left/Right", imageView4);
			images.put("Turn-left", imageView5);
			images.put("StraightLR", imageView6);
			images.put("Wall", imageView7);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		pathCards = new HashMap<String, Integer>();
		this.pathCards.put("Straight", 5);
		this.pathCards.put("Cross", 5);
		this.pathCards.put("StraightLR", 5);
		this.pathCards.put("Turn-left", 5);
		this.pathCards.put("Turn-right", 5);
		this.pathCards.put("Left/Right", 5);

		flashCards = new HashMap<String, Integer>();
		this.flashCards.put("Ear Plugs", 3); // Skip one turn
		this.flashCards.put("Water", 4); // Draw two cards
		this.flashCards.put("Lot of water", 2); // Draw three cards
		// this.flashCards.put("Shots on fire", 2);
		this.flashCards.put("Smashed bottle", 3); // The chosen player discards two cards
		// this.flashCards.put("Body surfing", 4); // Climb over the wall
		// this.flashCards.put("Ballad", 5);

		wallCards = new HashMap<String, Integer>();
		this.wallCards.put("Wall", 3); // Destroyed discarding one card
		// this.wallCards.put("Hell's Angels", 3);
		// this.wallCards.put("Mosh Pit", 2);

		this.totalCards = sumCards();

	}

	public Map<String, ImageView> getImages() {
		return images;
	}

	public int sumCards() {

		int sum = 0;

		List<Integer> mergedValues = new ArrayList<Integer>();
		mergedValues.addAll(this.pathCards.values());
		mergedValues.addAll(this.flashCards.values());
		mergedValues.addAll(this.wallCards.values());

		for (int values : mergedValues) {

			sum += values;
		}

		return sum;
	}

	public Map<String, Integer> getPathCards() {
		return pathCards;
	}

	public void setPathCards(Map<String, Integer> pathCards) {
		this.pathCards = pathCards;
	}

	public Map<String, Integer> getFlashCards() {
		return flashCards;
	}

	public void setFlashCards(Map<String, Integer> flashCards) {
		this.flashCards = flashCards;
	}

	public Map<String, Integer> getWallCards() {
		return wallCards;
	}

	public void setWallCards(Map<String, Integer> wallCards) {
		this.wallCards = wallCards;
	}

	public int getTotalCards() {
		return totalCards;
	}

	public void setTotalCards(int totalCards) {
		this.totalCards = totalCards;
	}

	public String[] getType() {
		return type;
	}

	public String getRandomType() {

		int rnd = new Random().nextInt(this.type.length);

		switch (type[rnd]) {
		case "Path":
			if (pathCards.size() == 0) {
				return getRandomType();
			}
			break;

		case "Flash":
			if (flashCards.size() == 0) {
				return getRandomType();
			}
			break;

		case "Wall":
			if (wallCards.size() == 0) {
				return getRandomType();
			}
			break;

		}
		return this.type[rnd];
	}

	public String getRandomCard(Map<String, Integer> cardType) {

		Object[] crunchifyKeys = cardType.keySet().toArray();
		String randomCard = (String) crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];

		return randomCard;
	}

	public void removeCardFromDeck(Map<String, Integer> cardType, String card) {

		cardType.replace(card, cardType.get(card) - 1);

		if (cardType.get(card) == 0) {

			cardType.remove(card);

		}

		this.totalCards--;
	}

}
