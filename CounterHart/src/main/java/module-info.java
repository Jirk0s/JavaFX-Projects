module com.example.counterhart {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.counterhart to javafx.fxml;
    exports com.example.counterhart;
}