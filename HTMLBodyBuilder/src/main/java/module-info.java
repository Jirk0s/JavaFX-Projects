module com.example.htmlbodybuilder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.htmlbodybuilder to javafx.fxml;
    exports com.example.htmlbodybuilder;
}