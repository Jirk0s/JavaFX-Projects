package com.example.numbertranslator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button CopyBtn;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea outputArea;

    @FXML
    private Button translateBtn;

    @FXML
    private ComboBox<String> typeCombo;
    String[] items = new String[]{
            "BIN",
            "OCT",
            "HEX"
    };
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeCombo.getItems().addAll(items);
        typeCombo.setValue("BIN");
        translateBtn.setOnAction(this::getResult);
        CopyBtn.setOnAction(this::copyResult);
    }
    public void errorAlert(String title, String content){
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Chyba");
        alertError.setHeaderText(title);
        alertError.setContentText(content);
        alertError.show();
    }
    public void copyResult(ActionEvent event){
        if(!outputArea.getText().equals("")){
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(outputArea.getText());
            clipboard.setContent(content);
            Alert alertError = new Alert(Alert.AlertType.INFORMATION);
            alertError.setTitle("Kopírování");
            alertError.setHeaderText("Zkopírováno do schránky");
            alertError.show();
        }else{
            errorAlert("Výsledek je prázdný", "Nebyla přeložena žádná číslice");
        }
    }
    private void getResult(ActionEvent event){
        String type = typeCombo.getValue();
        try{
            int input = Integer.valueOf(inputField.getText());

            switch (type){
                case "BIN":{
                    outputArea.setText(String.valueOf(Integer.toBinaryString(input)));
                }break;
                case "OCT":{
                    outputArea.setText(String.valueOf(Integer.toOctalString(input)));
                }break;
                case "HEX":{
                    outputArea.setText(String.valueOf(Integer.toHexString(input)));
                }break;
            }
        }catch (Exception e){
            errorAlert("Zadej pouze čísla", "Chybná syntaxe, Zadejte pouze číslice");
        }
    }
}
