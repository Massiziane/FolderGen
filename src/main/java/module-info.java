module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports com.example.app;

    opens com.example.Controllers to javafx.fxml;
}