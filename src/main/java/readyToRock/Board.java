package readyToRock;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board   {

    public Button[][] buttons;
    public GridPane gridPane;
    
    public Board() {
    	
    	FileInputStream input;
    	
  		
		buttons = new Button[7][7];
		gridPane = new GridPane();
		
		
		for (int i = 0; i < buttons.length; ++i) {
			
			
	         for(int j = 0; j < buttons[i].length; ++j) {
	            
	      
	         		buttons[i][j] = new Button("");
	         		buttons[i][j].setPrefSize(500, 100); 	
	         		
	         		gridPane.add(buttons[i][j],i,j);
	         		int[] num = {i,j};
	         		
	         		buttons[i][j].setOnMouseDragOver(e->buttons[num[0]][num[1]].setStyle("-fx-background-color: #228B22;"));
	         		
	         		buttons[i][j].setOnAction(e -> {
	        		
	        			System.out.print(GridPane.getRowIndex(buttons[num[0]][num[1]])+"  "+GridPane.getColumnIndex(buttons[num[0]][num[1]]));
	        			
	        		}	);
	         }
	     }
		
		try {
			input = new FileInputStream("C:/Users/mario/Desktop/ready/plettro4.png");
			Image image = new Image(input, 80, 80, false, false);
			ImageView imageView = new ImageView(image);

			FileInputStream input2 = new FileInputStream("C:/Users/mario/Desktop/ready/plettro2.png");
			Image image2 = new Image(input2, 80, 80, false, false);
			ImageView imageView2 = new ImageView(image2);

			FileInputStream input3 = new FileInputStream("C:/Users/mario/Desktop/ready/plettro3.png");
			Image image3 = new Image(input3, 80, 80, false, false);
			ImageView imageView3 = new ImageView(image3);

			FileInputStream input4 = new FileInputStream("C:/Users/mario/Desktop/ready/stage.jpg");
			Image image4 = new Image(input4, 200, 100, false, true);
			ImageView imageView4 = new ImageView(image4);
			
			this.buttons[3][0].setGraphic(imageView4);
		
	        this.buttons[1][4].setId("plettro");
			this.buttons[1][4].setGraphic(imageView);
			this.buttons[5][4].setGraphic(imageView2);
			this.buttons[3][6].setGraphic(imageView3);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		   
		

		gridPane.setAlignment(Pos.CENTER_LEFT);
		
		int column = 0, row = 3;
		
		while (column != 7 && row != 2) {

			this.buttons[column][row].setStyle("-fx-background-color: #CD5C5C; ");

			column++;

			if (column <= 3) {

				row++;

			} else
				row--;

		}
		
		

    }




    
}