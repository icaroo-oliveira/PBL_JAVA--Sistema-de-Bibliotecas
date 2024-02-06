module com.example.pbljava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pbljava to javafx.fxml;
    exports com.example.pbljava;
}