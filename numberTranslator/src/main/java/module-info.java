module com.example.numbertranslator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.numbertranslator to javafx.fxml;
    exports com.example.numbertranslator;
}