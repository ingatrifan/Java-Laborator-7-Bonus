package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {


    private int size = 4;
    private Tile[][] board = new Tile[size+1][size+1];
    private Player player1 = new SmartPlayer("Ana",1);
    private Player player2 = new RandomPlayer("Maria",2);
    private Game game;
    private BorderPane root = new BorderPane();

    private Parent createContent() {
        root.setPrefSize(600, 600);

        root.setTop(setPlayersNames());

        root.setCenter(setTable());

        return root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    private HBox setPlayersNames(){
        HBox hbox = new HBox();
        hbox.setSpacing(150);
        Text playerName1 = new Text(player1.getName());
        Text playerName2 = new Text(player2.getName());
        playerName1.setFont(Font.font(24));
        playerName2.setFont(Font.font(24));
        playerName1.setTextAlignment(TextAlignment.LEFT);
        playerName2.setTextAlignment(TextAlignment.RIGHT);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(playerName1);
        hbox.getChildren().add(playerName2);
        return hbox;
    }
    private FlowPane setTable(){
        FlowPane flow = new FlowPane();
        flow.setAlignment(Pos.CENTER);
        flow.setHgap(0);
        flow.setMaxWidth(size*80);
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                Tile tile = new Tile();
                flow.getChildren().add(tile);
                board[j][i] = tile;
            }
        }
        game = new Game(player1,player2,size,board);
        return flow;
    }
}