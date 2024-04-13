module com.example.morzeovka {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.morzeovka to javafx.fxml;
    exports com.example.morzeovka;
}