package com.example.cistamzda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField hrubaField;
    @FXML
    private Text zalohaBonus;

    @FXML
    private Button minusBtn;

    @FXML
    private TextField ohodnoceniField;

    @FXML
    private Button plusBtn;

    @FXML
    private Button resultBtn;

    @FXML
    private Text resultText;

    @FXML
    private Text socialniText;

    @FXML
    private Text zalohaText;

    @FXML
    private Text childText;
    @FXML
    private Text zdravotniText;
    int ChildCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        plusBtn.setOnAction(this::plus);
        minusBtn.setOnAction(this::minus);
        resultBtn.setOnAction(this::getResult);
    }
    public void plus(ActionEvent event){
        ChildCount++;
        childText.setText(String.valueOf(ChildCount));
    }
    public void minus(ActionEvent event){
        if(ChildCount != 0){
            ChildCount--;
            childText.setText(String.valueOf(ChildCount));
        }
    }
    public void errorAlert(String header, String content){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Chyba");
        alert.setHeaderText(header);
        if(content != null){
            alert.setContentText(content);
        }
        alert.show();
    }
    public void getResult(ActionEvent event){
        double hrubaMzda = 0;
        if(hrubaField.getText().equals("")){
            errorAlert("Zadejte výši hrubé mzdy", null);
        } else {
            try{
                int mzda = Integer.valueOf(hrubaField.getText().replace(" ", ""));
                int ohodnoceni = 0;
                if(!ohodnoceniField.getText().equals("")){
                    ohodnoceni = Integer.parseInt(ohodnoceniField.getText().replace(" ", ""));
                }else ohodnoceniField.setText("0");
                hrubaMzda = mzda+ohodnoceni;
                double zdravotni = (0.045*hrubaMzda);
                double socialni = (0.065*hrubaMzda);
                double zaloha =(Math.ceil(hrubaMzda / 100.0f) * 100)*0.15;
                if (ChildCount == 0) {
                    zaloha -= 2320;
                } else {
                    switch (ChildCount) {
                        case 1:
                            zaloha -= 1267;
                            break;
                        case 2:
                            zaloha -= (1267 + 1617);
                            break;
                        case 3:
                            zaloha -= (1267 + 1617 + 2017);
                            break;
                        default:
                            int childminus3 = ChildCount - 3;
                            zaloha -= (1267 + 1617 + 2017) + (2017 * childminus3);
                            break;
                    }
                }
                DecimalFormat df = new DecimalFormat("#.###");
                socialniText.setText(String.valueOf(df.format(socialni)) + " Kč");
                zdravotniText.setText(String.valueOf(df.format(zdravotni)) + " Kč");
                if(zaloha > 0){
                    zalohaBonus.setText("Záloha na daň");
                    zalohaText.setText(String.valueOf(df.format(zaloha)) + " Kč");
                }else{
                    zalohaBonus.setText("Daňový bonus");
                    zalohaText.setText(String.valueOf(df.format(Math.abs(zaloha))) + " Kč");
                }
                System.out.println(mzda + " " + ohodnoceni);
                resultText.setText(String.valueOf(df.format( hrubaMzda - socialni -zdravotni - zaloha)) + " Kč");
            }catch (Exception e){
                errorAlert("Zadej pouze čísla", e.getLocalizedMessage());
            }
        }
    }
}
