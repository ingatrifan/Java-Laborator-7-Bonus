package sample;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    private Text text = new Text();

    private boolean playable = true;
    private boolean turnX = true;

    public Tile() {
        Rectangle border = new Rectangle(70, 70);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        text.setFont(Font.font(72));
        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);

        setOnMouseClicked(event -> handleEvent(event));

    }
    private  void handleEvent(MouseEvent event){
        if (!playable)
            return;
        if (event.getButton() == MouseButton.PRIMARY) {
            if (!turnX)
                return;

            drawX();
            turnX = false;
        }
    }

    public void drawX () {
        text.setText("X");
    }

    public void drawO () {
        text.setText("O");
    }

    public double getCenterX() {
        return getTranslateX() + 100;
    }

    public double getCenterY() {
        return getTranslateY() + 100;
    }

    public String getValue() {
        return text.getText();
    }

}