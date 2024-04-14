module com.example.hrubamzda2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hrubamzda2 to javafx.fxml;
    exports com.example.hrubamzda2;
}