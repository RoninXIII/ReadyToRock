package readyToRock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board {

	public Button[][] buttons;
	public GridPane gridPane;

	public Board() {

		buttons = new Button[7][7];
		gridPane = new GridPane();

		for (int i = 0; i < buttons.length; ++i) {

			for (int j = 0; j < buttons[i].length; ++j) {

				buttons[i][j] = new Button("");
				buttons[i][j].setPrefSize(500, 100);

				gridPane.add(buttons[i][j], i, j);
				/*
				 * int[] num = { i, j };
				 * 
				 * buttons[i][j].setOnAction(e -> {
				 * 
				 * System.out.print(GridPane.getRowIndex(buttons[num[0]][num[1]]) + "  " +
				 * GridPane.getColumnIndex(buttons[num[0]][num[1]]));
				 * 
				 * });
				 */
			}
		}

		gridPane.setAlignment(Pos.CENTER);

		for (Button initial : this.initialPlatforms()) {

			initial.setStyle("-fx-background-color: #CD5C5C; ");

		}

		FileInputStream input4;
		try {
			input4 = new FileInputStream("C:/Users/mario/Desktop/ready/stage.jpg");
			Image image4 = new Image(input4, 200, 100, false, true);
			ImageView imageView4 = new ImageView(image4);

			this.buttons[3][0].setGraphic(imageView4);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	// Store the initial platform, that is the platform on which the player will
	// start

	public Button[] initialPlatforms() {

		Button[] platforms = new Button[buttons.length];

		int column = 0, row = 3;
		int i = 0;
		while (column != 7 && row != 2) {

			platforms[i] = this.buttons[column][row];
			column++;
			i++;
			if (column <= 3) {

				row++;

			} else
				row--;

		}
		return platforms;
	}

	public boolean isEmpty(Button cell) {

		if (cell.getId() == null) {
			return true;
		} else
			return false;

	}

	public void setPlayerAlert(Player player) {

		player.getCell().setOnAction(event -> {

			ButtonType[] cards = new ButtonType[player.getCards().size()];
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Alert");
			alert.setHeaderText("Choose the card to play!");

			int i = 0;
			for (String card : player.getCards()) {
				cards[i] = new ButtonType(card);

				alert.getButtonTypes().add(cards[i]);
				i++;
			}

			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get().getText() != "Ok" || result.get().getText() != "Annulla") {
			
			player.cardToPlay = result.get().getText();
			//check if possible to play the card
			//if possible remove the played card from player's hand
			player.getCards().remove(player.cardToPlay);
			player.workingMemory.update(player.handleOfPlayer, player);
			player.workingMemory.fireAllRules();
			}

		});

		// alert.setContentText("Choose your option.");

	}

	

	public void setInitialPosition(Player player1) {

		for (Button initial : this.initialPlatforms()) {
			initial.setOnAction(e -> {
				if (player1.getCell().getId() == null) {
					player1.setCell(initial);
					player1.workingMemory.fireAllRules();
					this.setPlayerAlert(player1);

				}
			});
		}

	}

	public void setCpuInitialPosition(Player cpu) {

		Button[] initialCpu2 = this.initialPlatforms();
		int rnd = new Random().nextInt(initialCpu2.length);

		while (this.isEmpty(initialCpu2[rnd]) == false) {

			rnd = new Random().nextInt(initialCpu2.length);
		}

		cpu.setCell(initialCpu2[rnd]);

		cpu.workingMemory.update(cpu.handleOfPlayer, cpu);

	}

}