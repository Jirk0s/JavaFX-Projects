package com.example.htmlbodybuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button browseBtn;

    @FXML
    private Button createBtn;

    @FXML
    private TextField filenameField;

    @FXML
    private TextField pathField;
    DirectoryChooser chooser = new DirectoryChooser();
    File file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        browseBtn.setOnAction(this::browseOnClick);
        createBtn.setOnAction(this::createOnClick);
    }
    public void browseOnClick(ActionEvent event){
        file = chooser.showDialog(new Stage());
        pathField.setText(file.getAbsolutePath());


    }
    public void createOnClick(ActionEvent event){
        if(filenameField.getText() == ""){
            filenameField.setText("index");
        }
        if(file == null){
            pathField.setText(System.getProperty("user.home"));
        }
        file = new File(pathField.getText());
            System.out.println(file.getAbsolutePath() + File.separator + filenameField.getText() + ".html");
        File html = new File(pathField.getText() + File.separator + filenameField.getText() + ".html");
            try {
                html.createNewFile();
                System.out.println("Vytvořen html soubor");
                FileWriter fw = new FileWriter(file.getAbsolutePath() + File.separator + filenameField.getText() + ".html");
                fw.write("<!DOCTYPE html>\n");
                fw.write("<html>\n");
                fw.write("<head>\n");
                fw.write("  <meta charset=\"UTF-8\">\n");
                fw.write("  <title> title </title>\n");
                fw.write("</head>\n");
                fw.write("<body>\n");
                fw.write("</body>\n");
                fw.write("</html>\n");
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
