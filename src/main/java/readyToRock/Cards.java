/**
 * 
 */
package readyToRock;

/**
 * @author mario
 *
 */
public class Cards {

	
	private int total = 63;
	
	private class Path{
		
		private int total = 30;

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
	}
	
	
	private class Flash{
		
		private int total = 8;

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
	}
	
	private class Wall{
		
		private int total = 25;

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
	}
}
