package com.example.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Index {

    @FXML
    private ListView<String> templateList;

    @FXML
    private void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/templateCreator.fxml")
            );

            Scene scene = new Scene(loader.load(), 500, 400);

            Stage stage = new Stage();
            stage.setTitle("Create Template");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(templateList.getScene().getWindow());

            scene.getStylesheets().add(
                    getClass().getResource("/com/example/style/index.css").toExternalForm()
            );

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete clicked");
    }

    @FXML
    private void handleCreate() {
        System.out.println("Create clicked");
    }
}