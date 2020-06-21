package readyToRock;


import java.io.FileInputStream;

import org.kie.api.KieServices;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

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

public class rockTest extends Application {

	static KieServices ks = KieServices.Factory.get();
	static KieContainer kc = ks.getKieClasspathContainer();
	static KieSession wm = kc.newKieSession("ksession-rules");
	
 
	public static void main(String[] args) {
		
	
		Cards mazzo = new Cards();
		
		
		//System.exit(1);
		wm.addEventListener(new RuleRuntimeEventListener() {
			
			@Override
			public void objectUpdated(ObjectUpdatedEvent arg0) {

				System.out.println("Object updated!"+ arg0.getObject().toString());
				
			}
			
			@Override
			public void objectInserted(ObjectInsertedEvent arg0) {
				
				System.out.println("Object inserted!"+ arg0.getObject().toString());
				
			}
			
			@Override
			public void objectDeleted(ObjectDeletedEvent arg0) {
				
				System.out.println("Object deleted!"+ arg0.getOldObject().toString());
				
			}
		} );
		
		
		launch(args);
		
		
	
		/*
		 * while()
		 * 
		 * */
		
		
		
	/*	FactHandle handleOfPlayer1 = wm.insert(player1);
		FactHandle handleOfPlayer2 = wm.insert(player2);
		player1.setActions(0);
		wm.update(handleOfPlayer1, player1);*/
		wm.fireAllRules();
		
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Stage window;
		Scene scene1,scene2;
		window = primaryStage;

		
		FileInputStream input = new FileInputStream("C:/Users/mario/Desktop/ready/plettro4.png");
        Image image = new Image(input,80,80,false,false);
        ImageView imageView = new ImageView(image);

        FileInputStream input2 = new FileInputStream("C:/Users/mario/Desktop/ready/plettro2.png");
        Image image2 = new Image(input2,80,80,false,false);
        ImageView imageView2 = new ImageView(image2);
        
        FileInputStream input3 = new FileInputStream("C:/Users/mario/Desktop/ready/plettro3.png");
        Image image3 = new Image(input3,80,80,false,false);
        ImageView imageView3 = new ImageView(image3);
		
		
		 GridPane gridPane = new GridPane();
		Button[][] buttons = new Button[7][7];
		
		for (int i = 0; i < buttons.length; ++i) {
			
			
           for(int j = 0; j < buttons[i].length; ++j) {
              
        
           		buttons[i][j] = new Button("");
           		buttons[i][j].setPrefSize(100, 100); 	
           		
           	
           
           	gridPane.add(buttons[i][j],i,j);
           }
       }
		
		gridPane.setAlignment(Pos.CENTER);
       scene2 = new Scene(gridPane,500,500);
		
	
		buttons[0][3].setStyle("-fx-background-color: #c6a664; ");
		buttons[1][4].setStyle("-fx-background-color: #c6a664; ");
		buttons[2][5].setStyle("-fx-background-color: #c6a664; ");
		buttons[3][6].setStyle("-fx-background-color: #c6a664; ");
		buttons[4][5].setStyle("-fx-background-color: #c6a664; ");
		buttons[5][4].setStyle("-fx-background-color: #c6a664; ");
		buttons[6][3].setStyle("-fx-background-color: #c6a664; ");
		buttons[3][0].setStyle("-fx-background-color: #c6a664; ");
		
		buttons[1][4].setGraphic(imageView);
		buttons[5][4].setGraphic(imageView2);
		buttons[3][6].setGraphic(imageView3);
		
	
		
		Label  label1 = new Label("Welcome! Choose the color of the plectrum!");
		Button button1 = new Button("");
		Button button2 = new Button("");
		Button button3 = new Button("");
		
		button1.setPrefSize(200, 200); 
		button2.setPrefSize(200, 200); 
		button3.setPrefSize(200, 200);
		
		button1.setGraphic(imageView); 
		button2.setGraphic(imageView2);
		button3.setGraphic(imageView3);
		
		
		button1.setOnAction(e -> window.setScene(scene2));
		button1.setOnAction(e -> window.setScene(scene2));
		button1.setOnAction(e -> window.setScene(scene2));
		
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1,button1,button2,button3);
		scene1 = new Scene(layout1,500,500);
		
	
        
        
    	window.setScene(scene1);
		window.setTitle("Thi is the only stage!");
		window.show();
		
	}
	

}
