module com.example.cistamzda {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cistamzda to javafx.fxml;
    exports com.example.cistamzda;
}