package com.example.hrubamzda2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Text bonuszaloha;

    @FXML
    private TextField hodnoceni;

    @FXML
    private Button minus;

    @FXML
    private Button plus;

    @FXML
    private Text socialni;

    @FXML
    private Button vypocetBtn;

    @FXML
    private TextField vyseField;

    @FXML
    private Text vysledek;

    @FXML
    private Text zaloha;

    @FXML
    private Text zdravotni;
    @FXML
    private Text pocet;
    int pocetDeti = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        minus.setOnAction(this::Minus);
        plus.setOnAction(this::Plus);
        vypocetBtn.setOnAction(this::getResult);
    }
    public void getResult(ActionEvent event){
        if(!vyseField.getText().equals("")){
            try {
                double vyse = Integer.parseInt(vyseField.getText());
                double osobniohodnoceni = 0;
                if(!hodnoceni.getText().equals("")){
                    osobniohodnoceni = Integer.parseInt(hodnoceni.getText());
                }
                double hrubamzda = vyse+osobniohodnoceni;
                double zdravotni_ = (0.045*hrubamzda);
                double socialni_ = (0.065*hrubamzda);
                double zaloha_ = (Math.ceil(hrubamzda / 100.0f)*100)*0.15;
                if(pocetDeti == 0){
                    zaloha_ -= 2320;
                } else if (pocetDeti == 1) {
                    zaloha_ -= 1267;
                } else if (pocetDeti == 2) {
                    zaloha_ -= (1267 + 1617);
                } else if (pocetDeti == 3) {
                    zaloha_ -= (1267 + 1617 + 2017);
                }else{
                    zaloha_ -= ((1267 + 1617 + 2017) + (2017 * (pocetDeti-3)));
                }
                DecimalFormat df = new DecimalFormat("#.###");
                socialni.setText(String.valueOf(df.format(socialni_)) + " Kč");
                zdravotni.setText(String.valueOf(df.format(zdravotni_)) + " Kč");
                if(zaloha_>0){
                    bonuszaloha.setText("Záloha na daň");
                    zaloha.setText(String.valueOf(df.format(zaloha_)) + " Kč");
                }else{
                    bonuszaloha.setText("Daňový bonus");
                    zaloha.setText(String.valueOf(df.format(Math.abs(zaloha_))) + " Kč");
                }
                vysledek.setText(String.valueOf(df.format(hrubamzda - socialni_ - zaloha_ -zdravotni_)) + " Kč");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void Plus(ActionEvent event){
        pocetDeti++;
        pocet.setText(String.valueOf(pocetDeti));
    }
    public void Minus(ActionEvent event){
        if(pocetDeti != 0){
            pocetDeti--;
        }
        pocet.setText(String.valueOf(pocetDeti));
    }
}