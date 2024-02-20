module com.example.pbljava {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.pbljava to javafx.fxml,javafx.graphics;
    exports com.example.pbljava;
    exports com.example.pbljava.controller;
    opens com.example.pbljava.controller to javafx.fxml,javafx.graphics;
    opens com.example.pbljava.Model;
    exports com.example.pbljava.components;
    opens com.example.pbljava.components to javafx.fxml, javafx.graphics;
}