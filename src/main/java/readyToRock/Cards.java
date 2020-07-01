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
	private ImageView[] images ;

	public FactHandle handleOfCards;
	 
	
	public Cards() {
		super();
		
		images = new ImageView[4];
		
		try {
			FileInputStream	input = new FileInputStream("C:/Users/mario/Desktop/ready/straight.jpg");
			Image image = new Image(input, 200, 100, false, true);
			ImageView imageView = new ImageView(image);
			FileInputStream	input2 = new FileInputStream("C:/Users/mario/Desktop/ready/cross.jpg");
			Image image2 = new Image(input2, 200, 100, false, true);
			ImageView imageView2 = new ImageView(image2);
			FileInputStream	input3 = new FileInputStream("C:/Users/mario/Desktop/ready/turnRight.jpg");
			Image image3 = new Image(input3, 200, 100, false, true);
			ImageView imageView3 = new ImageView(image3);
			FileInputStream	input4 = new FileInputStream("C:/Users/mario/Desktop/ready/leftRight.jpg");
			Image image4 = new Image(input4, 200, 100, false, true);
			ImageView imageView4 = new ImageView(image4);
			
			imageView.setId("straight");
			imageView2.setId("cross");
			imageView3.setId("turnRight");
			imageView4.setId("leftRight");
			
			images[0] = imageView;
			images[1] = imageView2;
			images[2] = imageView3;
			images[3] = imageView4;
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		
		pathCards = new HashMap<String, Integer>();
		this.pathCards.put("Straight", 5);
		this.pathCards.put("Left/Right", 5);
		this.pathCards.put("Turn-left", 5);
		this.pathCards.put("Turn-right", 5);
		this.pathCards.put("Cross", 5);

		flashCards = new HashMap<String, Integer>();
		this.flashCards.put("Ear Plugs", 3); //Skip two turns
		this.flashCards.put("Water", 4); // Draw two cards
		this.flashCards.put("Lot of water", 2); //Draw three cards
		//this.flashCards.put("Shots on fire", 2);
		this.flashCards.put("Smashed bottle", 3); //The chosen player draw two cards 
		this.flashCards.put("Body surfing", 4); // Climb over the wall
		//this.flashCards.put("Ballad", 5);

		wallCards = new HashMap<String, Integer>();
		this.wallCards.put("Head-bangers", 3);
		this.wallCards.put("Hell's Angels", 3);
		//this.wallCards.put("Mosh Pit", 2);

		this.totalCards = sumCards();

	}
	
	
	public ImageView[] getImages() {
		return images;
	}

	public void setImages(ImageView[] images) {
		this.images = images;
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
