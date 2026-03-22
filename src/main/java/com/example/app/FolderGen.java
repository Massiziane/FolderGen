package com.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FolderGen extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/index.fxml")
        );

        Scene scene = new Scene(loader.load(), 700, 450);
        // Add style
        scene.getStylesheets().add(
            getClass().getResource("/com/example/style/index.css").toExternalForm()
        );

        stage.setTitle("Folder Structure Generator");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.show();
    }

}