module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.app;

    opens com.example.Controllers to javafx.fxml;
}