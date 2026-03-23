module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;

    exports com.example.app;

    opens com.example.Controllers to javafx.fxml;
    opens com.example.model to com.google.gson;
}