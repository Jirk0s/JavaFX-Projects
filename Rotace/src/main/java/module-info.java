module com.example.rotace {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rotace to javafx.fxml;
    exports com.example.rotace;
}