package readyToRock;





import java.util.Arrays;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board   {

    public Button[][] buttons;
    public GridPane gridPane;
    
    public Board() {
      		
		buttons = new Button[7][7];
		gridPane = new GridPane();
		
		for (int i = 0; i < buttons.length; ++i) {
			
			
         for(int j = 0; j < buttons[i].length; ++j) {
            
      
         		buttons[i][j] = new Button("");
         		buttons[i][j].setPrefSize(500, 100); 	
         		
         	
         
         	gridPane.add(buttons[i][j],i,j);
         }
     }
		gridPane.setAlignment(Pos.CENTER);
    }




    
}