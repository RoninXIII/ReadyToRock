/**
 * 
 */
package readyToRock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author mario
 *
 */
public class Cards {

	
	private int totalCards ;
	
	
	public Map<String,Integer>  pathCards;
	
	public Map<String,Integer> flashCards;
	
	public Map<String,Integer> wallCards;
	
	
	public Cards() {
		super();
		
		
		
		pathCards = new HashMap<String,Integer>();
		this.pathCards.put("Straight", 5);
		this.pathCards.put("Left/Right", 5);
		this.pathCards.put("Turn-left", 5);
		this.pathCards.put("Turn-right", 5);
		this.pathCards.put("Cross", 5);
		
		flashCards = new HashMap<String,Integer>();
		this.flashCards.put("Ear Plugs", 3);
		this.flashCards.put("Beer", 4);
		this.flashCards.put("Lots of beer", 2);
		this.flashCards.put("Shots on fire", 2);
		this.flashCards.put("Smashed beer", 3);
		this.flashCards.put("Body surfing", 4);
		this.flashCards.put("Ready to rock", 5);
		
		wallCards = new HashMap<String,Integer>();
		this.wallCards.put("Head-bangers", 3);
		this.wallCards.put("Hell's Angels", 3);
		this.wallCards.put("Mosh Pit", 2);
	
		
		this.totalCards = sumCards();
		
	}
	
	
public int sumCards(){
	
	int sum = 0;
	
	List<Integer> mergedValues = new ArrayList<Integer>();
	mergedValues.addAll(this.pathCards.values());
	mergedValues.addAll(this.flashCards.values());
	mergedValues.addAll(this.wallCards.values());
	
	
	for ( int values : mergedValues) {
		
		sum+=values;
	}
	
	
	return sum;
}
	
	


	public boolean allocateCards(Player player){
		
		String[] cards = {};
		
		
		//player.setCards();
		
		return false;
		
		
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
}
