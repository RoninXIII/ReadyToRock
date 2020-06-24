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

	int[] pos = new int[2];
	int[] pos2 = new int[2];
	int[] pos3 = new int[2];

	static Player player1;
	static Player player2;
	static Player player3;
	static Cards deck;
	Board board = new Board();

	public static void main(String[] args) {

		deck = new Cards();

		player1 = new Player(deck);
		player2 = new Player(deck);
		player3 = new Player(deck);

		wm.addEventListener(new RuleRuntimeEventListener() {

			@Override
			public void objectUpdated(ObjectUpdatedEvent arg0) {

				System.out.println("Object updated!" + arg0.getObject().toString());

			}

			@Override
			public void objectInserted(ObjectInsertedEvent arg0) {

				System.out.println("Object inserted!" + arg0.getObject().toString());

			}

			@Override
			public void objectDeleted(ObjectDeletedEvent arg0) {

				System.out.println("Object deleted!" + arg0.getOldObject().toString());

			}
		});

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Stage window;
		Scene scene1, scene2;
		window = primaryStage;

		FileInputStream input = new FileInputStream("C:/Users/mario/Desktop/ready/plettro4.png");
		Image image = new Image(input, 80, 80, false, false);
		ImageView imageView = new ImageView(image);

		FileInputStream input2 = new FileInputStream("C:/Users/mario/Desktop/ready/plettro2.png");
		Image image2 = new Image(input2, 80, 80, false, false);
		ImageView imageView2 = new ImageView(image2);

		FileInputStream input3 = new FileInputStream("C:/Users/mario/Desktop/ready/plettro3.png");
		Image image3 = new Image(input3, 80, 80, false, false);
		ImageView imageView3 = new ImageView(image3);

		FileInputStream input4 = new FileInputStream("C:/Users/mario/Desktop/ready/stage.jpg");
		Image image4 = new Image(input4, 200, 100, false, false);
		ImageView imageView4 = new ImageView(image4);
		
		FileInputStream input5 = new FileInputStream("C:/Users/mario/Desktop/ready/straight.jpg");
		Image image5 = new Image(input5, 180, 100, false, false);
		ImageView straight = new ImageView(image5);

		FileInputStream input6 = new FileInputStream("C:/Users/mario/Desktop/ready/leftRight.jpg");
		Image image6 = new Image(input6, 180, 100, false, false);
		ImageView leftRight = new ImageView(image6);

		FileInputStream input7 = new FileInputStream("C:/Users/mario/Desktop/ready/turnRight.jpg");
		Image image7 = new Image(input7, 180, 100, false, false);
		ImageView turnRight = new ImageView(image7);

		FileInputStream input8 = new FileInputStream("C:/Users/mario/Desktop/ready/cross.jpg");
		Image image8 = new Image(input8, 180, 100, false, false);
		ImageView cross = new ImageView(image8);

		/*
		 * GridPane gridPane = new GridPane(); Button[][] buttons = new Button[7][7];
		 * 
		 * for (int i = 0; i < buttons.length; ++i) {
		 * 
		 * 
		 * for(int j = 0; j < buttons[i].length; ++j) {
		 * 
		 * 
		 * buttons[i][j] = new Button(""); buttons[i][j].setPrefSize(500, 100); //
		 * buttons[i][j].setStyle("-fx-background-color: #D3D3D3;");
		 * 
		 * gridPane.add(buttons[i][j],i,j); } }
		 * 
		 * 
		 * 
		 * gridPane.setAlignment(Pos.CENTER);
		 */
		scene2 = new Scene(board.gridPane, 500, 500);

		int column = 0, row = 3;
		while (column != 7 && row != 2) {

			board.buttons[column][row].setStyle("-fx-background-color: #CD5C5C; ");

			column++;

			if (column <= 3) {

				row++;

			} else
				row--;

		}

		board.buttons[3][0].setGraphic(imageView4);
        
		board.buttons[1][4].setGraphic(imageView);
		board.buttons[5][4].setGraphic(imageView2);
		board.buttons[3][6].setGraphic(imageView3);
		
		board.buttons[0][2].setGraphic(straight);
		board.buttons[0][1].setGraphic(turnRight);
		board.buttons[1][1].setGraphic(leftRight);

		Label label1 = new Label("Welcome! Choose your plectrum!");
		Button button1 = new Button("");
		Button button2 = new Button("");
		Button button3 = new Button("");

		button1.setPrefSize(200, 200);
		button2.setPrefSize(200, 200);
		button3.setPrefSize(200, 200);

		button1.setGraphic(imageView);
		button2.setGraphic(imageView2);
		button3.setGraphic(imageView3);

		button1.setOnAction(e -> {
			FactHandle handleOfPlayer1 = wm.insert(player1);
			FactHandle handleOfCards = wm.insert(deck);

			

			player1.setPosition(1,4);
			player2.setPosition(5,4);
			player3.setPosition(3,6);

			wm.update(handleOfCards, deck);
			wm.update(handleOfPlayer1, player1);
			wm.fireAllRules();
			window.setScene(scene2);
		});

		button2.setOnAction(e -> {

			window.setScene(scene2);
		});

		button3.setOnAction(e -> {

			window.setScene(scene2);
		});

		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, button1, button2, button3);
		scene1 = new Scene(layout1, 500, 500);

		window.setScene(scene1);
		window.setTitle("Thi is the only stage!");
		window.show();

	}

}
