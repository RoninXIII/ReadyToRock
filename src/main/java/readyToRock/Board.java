package readyToRock;



import javafx.event.ActionEvent;

import java.util.Arrays;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Board {

    private int[][] board_matrix;
    private int board_size;

    public Board(int board_size) {
        this.board_matrix = new int[board_size][board_size];
        this.board_size = board_size;
      

        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                this.board_matrix[i][j] = 0;
            }
        }
    }

    public void make_move(int player, int x_pos, int y_pos) {
        if (player == 1) board_matrix[x_pos][y_pos] = 1;
        else board_matrix[x_pos][y_pos] = 2;
    }

	@Override
	public String toString() {
		return "Board [board_matrix=" + Arrays.toString(board_matrix) + ", board_size=" + board_size + ", win_length=" + "]";
	}

    
}