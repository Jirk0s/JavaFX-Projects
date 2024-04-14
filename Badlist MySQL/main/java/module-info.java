module com.example.badlist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.example.badlist to javafx.fxml;
    exports com.example.badlist;
}