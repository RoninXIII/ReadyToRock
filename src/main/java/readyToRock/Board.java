package readyToRock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.kie.api.KieServices;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
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
		/*		int[] num = { i, j };

				buttons[i][j].setOnAction(e -> {

					System.out.print(GridPane.getRowIndex(buttons[num[0]][num[1]]) + "  "
							+ GridPane.getColumnIndex(buttons[num[0]][num[1]]));

				});*/
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

	public void setInitialPosition(Player player1,Player cpu) {

		try {

			FileInputStream input = new FileInputStream("C:/Users/mario/Desktop/ready/plettro4.png");
			Image image = new Image(input, 80, 80, false, false);
			ImageView imageView = new ImageView(image);
			for (Button initial : this.initialPlatforms()) {
				initial.setOnAction(e -> {
					if (player1.getCell().getId() == null) {
						player1.setPosition(initial);
						player1.getCell().setId(player1.getColor());
						initial.setGraphic(imageView);
						this.setCpuInitialPosition(cpu);

					}
				});
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	public void setCpuInitialPosition(Player cpu) {


			
		
		FileInputStream input2;
		try {
			input2 = new FileInputStream("C:/Users/mario/Desktop/ready/plettro2.png");

			Image image2 = new Image(input2, 80, 80, false, false);
			ImageView imageView2 = new ImageView(image2);

			FileInputStream input3 = new FileInputStream("C:/Users/mario/Desktop/ready/plettro3.png");
			Image image3 = new Image(input3, 80, 80, false, false);
			ImageView imageView3 = new ImageView(image3);

			Button[] initialCpu2 = this.initialPlatforms();
			int rnd = new Random().nextInt(initialCpu2.length);

			while (this.isEmpty(initialCpu2[rnd]) == false) {

				rnd = new Random().nextInt(initialCpu2.length);
			}

			cpu.setPosition(initialCpu2[rnd]);
			cpu.getCell().setId(cpu.getColor());
			if (cpu.getColor() != "Red")
				initialCpu2[rnd].setGraphic(imageView2);
			else
				initialCpu2[rnd].setGraphic(imageView3);

			System.out.println(cpu.getCell().getId());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

	

}