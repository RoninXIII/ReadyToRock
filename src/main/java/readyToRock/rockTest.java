package readyToRock;

import java.io.FileInputStream;
import java.util.Arrays;

import org.kie.api.KieServices;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class rockTest extends Application {

	static KieServices ks = KieServices.Factory.get();
	static KieContainer kc = ks.getKieClasspathContainer();
	static KieSession wm = kc.newKieSession("ksession-rules");
	
	Cards deck = new Cards();
	Player player1 = new Player(deck, "Blue",wm);
	
	//Player player2 = new Player(deck, "White",wm);
	Player player2 = new Player(deck, "Red",wm);

	Board board = new Board();
	
	public static void main(String[] args) {

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
		
		scene2 = new Scene(board.gridPane, 500, 500);
		
		scene2.getStylesheets().add("readyToRock/stylesheet.css");

		player1.handleOfPlayer = wm.insert(player1);
		player2.handleOfPlayer = wm.insert(player2);
		//player3.handleOfPlayer = wm.insert(player3);
		
		deck.handleOfCards = wm.insert(deck);
		FactHandle handleOfBoard = wm.insert(board);

	//	player1.workingMemory = wm;
		

		// player1.playCard("straight",wm);

		// board.findCell(player1);
		/*
		 * board.buttons[1][4].setOnAction(e -> {
		 * 
		 * Object ob = e.getSource(); System.out.println(ob.toString());
		 * 
		 * } );
		 */
		
		wm.fireAllRules();

	/*	wm.update(handleOfCards, deck);
		wm.update(handleOfBoard, board);
		wm.update(handleOfPlayer1, player1);
		wm.update(handleOfPlayer2, player2);
		wm.update(handleOfPlayer3, player3);*/

		window.setScene(scene2);

		window.setTitle("Welcome!");
		window.show();

	}

}
