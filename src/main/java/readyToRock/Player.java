/**
 * 
 */
package readyToRock;

/**
 * @author mario
 *
 */
public class Player {

	private String color;
	private int cards;
	private int actions = 2;
	
	private int distance = 5;
	// attributo per indicare le mosse valide
	// attributo per indicare la posizione all'interno della griglia
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCards() {
		return cards;
	}

	public void setCards(int cards) {
		this.cards = cards;
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
		return "Player [color=" + color + ", cards=" + cards + ", actions=" + actions + 
				 ", distance=" + distance + "]";
	}
	 
	
	
}