package com.example.morzeovka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextArea resultArea;
    @FXML
    private Button traslateBtn;

    @FXML
    private TextField userField;
    //Pole znaků podle morzeova písma
    String[] Morzeovka = new String[]{
            "●-",
            "-●●●",
            "-●-●",
            "-●●",
            "●",
            "●●-●",
            "--●",
            "●●●●",
            "●●",
            "●---",
            "-●-",
            "●-●●",
            "--",
            "-●",
            "---",
            "●--●",
            "--●-",
            "●-●",
            "●●●",
            "-",
            "●●-",
            "●●●-",
            "●--",
            "-●●-",
            "-●--",
            "--●●",
            "-----",
            "●----",
            "●●---",
            "●●●--",
            "●●●●-",
            "●●●●●",
            "-●●●●",
            "--●●●",
            "---●●",
            "----●",
            "    "
    };
    //Pole znaků
    char[] Letter = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Přiřazení getResult na tlačítko
        traslateBtn.setOnAction(this::getResult);
    }

    //Porovnání znaků
    void getResult(ActionEvent event){
        String text = userField.getText();
        String resultText = "";
        char[] poleZnaku = text.toCharArray();

        for (char item : poleZnaku) {
            int i = 0;
            for (char itemZnak : Letter) {
                if (item == itemZnak) { // zde získáme první znak z řetězce
                    resultText += Morzeovka[i] + " | ";
                    break; // Zastaví procházení, protože jsme našli odpovídající znak
                }
                i++;
                if(i>36){
                    resultText += "Znak nebyl nalezen" + " | ";
                }
            }
        }
        resultArea.setText(resultText);
    }
}