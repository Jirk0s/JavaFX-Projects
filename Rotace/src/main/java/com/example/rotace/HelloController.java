package com.example.rotace;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider degSlider;

    @FXML
    private Rectangle rectangle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        degSlider.setOnMouseDragged(this::rotateRectangle);
        rectangle.setRotate(degSlider.getValue());
        colorPicker.setOnAction(this::changeColor);
    }

    public void rotateRectangle(MouseEvent event){
        double value = degSlider.getValue();
        rectangle.setRotate(value);
    }
    public void changeColor(ActionEvent event){
        Color color = colorPicker.getValue();
        rectangle.setFill(Paint.valueOf(String.valueOf(color)));
    }
}
