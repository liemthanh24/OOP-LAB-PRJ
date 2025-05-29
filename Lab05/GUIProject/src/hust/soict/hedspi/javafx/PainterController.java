package hust.soict.hedspi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadio;

    @FXML
    private RadioButton eraserRadio;

    @FXML
    private ToggleGroup toolToggleGroup;

    private Color currentColor = Color.BLACK;
    private double brushSize = 4;

    @FXML
    public void initialize() {
        penRadio.setSelected(true);
        toolToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (penRadio.isSelected()) {
                currentColor = Color.BLACK;
                brushSize = 4;
            } else if (eraserRadio.isSelected()) {
                currentColor = Color.WHITE;
                brushSize = 10;
            }
        });
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Circle newCircle = new Circle(event.getX(), event.getY(), brushSize, currentColor);
        drawingAreaPane.getChildren().add(newCircle);
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
}
