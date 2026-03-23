package com.example.Controllers;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.model.FolderNode;
import com.example.model.Template;
import com.example.utils.FileUtil;
import com.google.gson.Gson;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Index {

    @FXML
    private ListView<String> templateList;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private void initialize() {
        loadTemplatesList();
        templateList.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> {
                if (newVal != null) {
                    showTemplatePreview(newVal);
                }
            }
        );
    }

    private void loadTemplatesList() {
        try {
            // load templates
            Path templatesDir = Paths.get("templates");
            
            if(!Files.exists(templatesDir)) {
                Files.createDirectories(templatesDir);
            }
            // clear list
            templateList.getItems().clear();
            // load templates
            try(var stream = Files.newDirectoryStream(templatesDir, "*.json")) {
                for (Path entry : stream) {
                    String name = entry.getFileName().toString().replace(".json", "");
                    templateList.getItems().add(name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

            loadTemplatesList();

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
        
        try {

            // choose template
            String selectedTemplate = templateList.getSelectionModel().getSelectedItem();

            if(selectedTemplate == null) {
                System.out.println("No template selected");
                return;
            }

            // load template
            Path filePath = Paths.get("templates/" + selectedTemplate + ".json");
            Template template = loadTemplates(filePath);
            
            // choosing destination
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Select Destination Path");

            File selectedDir = chooser.showDialog(templateList.getScene().getWindow());

            if(selectedDir == null) {
                return;
            }

            // generate folder structure
            FileUtil.createFolders(template.getRoot(), selectedDir.toPath());

            System.out.println("Folder structure generated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Template loadTemplates(Path filePath) throws IOException {
        Gson gson = new Gson();

        try( Reader reader = Files.newBufferedReader(filePath)) {
            return gson.fromJson(reader, Template.class);
        }
    }
    
    private TreeItem<String> convertModelToTree(FolderNode node) {

        TreeItem<String> item = new TreeItem<>(node.getName());

        for (FolderNode child : node.getChildren()) {
            item.getChildren().add(convertModelToTree(child));
        }

        return item;
    }
    private void showTemplatePreview(String templateName) {

    try {
            Path path = Paths.get("templates", templateName + ".json");

            Template template = loadTemplates(path);

            TreeItem<String> rootItem = convertModelToTree(template.getRoot());

            treeView.setRoot(rootItem);
            treeView.setShowRoot(true);

            expandAll(rootItem); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void expandAll(TreeItem<?> item) {
        if (item != null) {
            item.setExpanded(true);
            for (TreeItem<?> child : item.getChildren()) {
                expandAll(child);
            }
        }
    }
}