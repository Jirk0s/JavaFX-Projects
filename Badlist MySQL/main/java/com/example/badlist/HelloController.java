package com.example.badlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.example.badlist.dbconnect; //Připojení souboru dbconnect
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button addBtn;

    @FXML
    private Button delBtn;

    @FXML
    private TextField emailField;

    @FXML
    private Button findBtn;

    @FXML
    private TextField findField;

    @FXML
    private ListView<String> list;

    @FXML
    private TextField nameField;
    /*
    @Změnit na svoje přihlašovací údaje
     */
    dbconnect db = new dbconnect("it-jmenoprijmeni", "heslo", "sql.stredniskola.com", "jmenoprijmeni");
    @FXML
    private TextField prijmeniField;
    public void refreshList(){
        db.connect();
        try {
            Statement st = db.getConnection().createStatement();
            ResultSet q = st.executeQuery("SELECT jmeno, prijmeni FROM uzivatele");
            ObservableList<String> uzivatele = FXCollections.observableArrayList();
            while (q.next()){
                uzivatele.add(q.getString(1) + " " + q.getString(2));
            }
            list.getItems().addAll(uzivatele);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        db.close();
    }
    public String getSelection(){
        return list.getSelectionModel().selectedItemProperty().getValue();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshList();
        list.setOnMouseClicked(this::getValues);
        findBtn.setOnAction(this::search);
        addBtn.setOnAction(this::addToList);
        delBtn.setOnAction(this::deleteRow);
    }
    public void getValues(MouseEvent event){
        if(getSelection() != null){
            try {
                db.connect();
                String[] celejmeno = getSelection().split(" ");
                String jmeno = celejmeno[0];
                String prijmeni = celejmeno[1];
                Statement st = db.getConnection().createStatement();
                String query = "SELECT Jmeno, prijmeni, email FROM uzivatele WHERE Jmeno = ? AND prijmeni = ?";
                PreparedStatement statement = db.getConnection().prepareStatement(query);
                statement.setString(1, jmeno);
                statement.setString(2, prijmeni);
                ResultSet q = statement.executeQuery();
                while (q.next()){
                    nameField.setText(q.getString(1));
                    prijmeniField.setText(q.getString(2));
                    emailField.setText(q.getString(3));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            db.close();
        }
    }
    public void search(ActionEvent event){
        db.connect();
        try {
            if(!findField.getText().equals("")){
            Statement st = db.getConnection().createStatement();
            String query = "SELECT Jmeno, prijmeni, email FROM uzivatele WHERE Jmeno LIKE ? or prijmeni LIKE ? or email LIKE ? ";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, '%' + findField.getText() + '%');
            statement.setString(2, '%' + findField.getText() + '%');
            statement.setString(3, '%' + findField.getText() + '%');
            ResultSet q = statement.executeQuery();
            while (q.next()){
                list.getItems().clear();
                list.getItems().add(q.getString(1) + " " + q.getString(2));
            }
            }else{
                list.getItems().clear();
                refreshList();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        db.close();
    }
    public void addToList(ActionEvent event){
        db.connect();
        if(!emailField.getText().equals("") && !nameField.equals("") && !prijmeniField.getText().equals("")){
            try {
                String query = "SELECT * FROM uzivatele WHERE Email = ?";
                PreparedStatement statement = db.getConnection().prepareStatement(query);
                statement.setString(1, emailField.getText());
                ResultSet q = statement.executeQuery();
                if (q.next()) { // Kontrola, zda ResultSet obsahuje nějaké řádky
                    String query1 = "UPDATE uzivatele SET jmeno = ?, prijmeni = ? WHERE email = ?";
                    statement = db.getConnection().prepareStatement(query1);
                    statement.setString(1, nameField.getText());
                    statement.setString(2, prijmeniField.getText());
                    statement.setString(3, emailField.getText());
                    statement.executeUpdate();
                    getAlert("Úspěšně upraven uživatel s emailovou adresou " + emailField.getText(), Alert.AlertType.INFORMATION);
                } else {
                    String query2 = "INSERT INTO uzivatele(Jmeno, prijmeni, email) VALUES (?, ?, ?)";
                    statement = db.getConnection().prepareStatement(query2);
                    statement.setString(1, nameField.getText());
                    statement.setString(2, prijmeniField.getText());
                    statement.setString(3, emailField.getText());
                    statement.executeUpdate();
                    getAlert("Úspěšně přidán uživatel s emailovou adresou " + emailField.getText(), Alert.AlertType.INFORMATION);
                }
                list.getItems().clear();
                refreshList();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            getAlert("Zadej všechny položky", Alert.AlertType.ERROR);
        }
        db.close();
    }
    public void getAlert(String title, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setHeaderText(title);
        alert.show();
    }
    public void deleteRow(ActionEvent event){
        try {
            db.connect();
            if(!emailField.getText().equals("")){
            String query = "SELECT * FROM uzivatele WHERE Email = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, emailField.getText());
            ResultSet q = statement.executeQuery();
            if(q.next()){
                query = "DELETE FROM uzivatele WHERE Email = ?";
                statement = db.getConnection().prepareStatement(query);
                statement.setString(1, emailField.getText());
                statement.executeUpdate();
            }else{
                getAlert("Uživatel s emailovou adresou " + emailField.getText() + " neexistuje.", Alert.AlertType.ERROR);
            }
            }else{
                getAlert("Zadejte prosím pole s Emailovou adresou", Alert.AlertType.ERROR);
            }
            list.getItems().clear();
            refreshList();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}