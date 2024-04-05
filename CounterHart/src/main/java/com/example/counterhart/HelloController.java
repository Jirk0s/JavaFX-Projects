package com.example.counterhart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    int count;
    @FXML
    private Button minusBtn;

    @FXML
    private Button plusBtn;

    @FXML
    private Text resultText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plusBtn.setOnAction(this::CountPlus);
        minusBtn.setOnAction(this::CountMinus);
    }
    void CountPlus(ActionEvent event){
        count++;
        resultText.setText(String.valueOf(Integer.valueOf(count)));
    }
    void CountMinus(ActionEvent event){
        count--;
        resultText.setText(String.valueOf(Integer.valueOf(count)));
    }
}
