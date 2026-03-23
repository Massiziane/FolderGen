package com.example.Controllers;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.example.model.FolderNode;
import com.example.model.Template;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.stage.Stage;

public class TemplateCreator {

    @FXML
    private TextField templateNameField;

    @FXML
    private TreeView<String> treeView;

    private String currentFileName = null;

    @FXML
    public void initialize() {
        // Default root
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);
        treeView.setRoot(root);

        treeView.setEditable(true);
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());
    }

    @FXML
    private void handleAddFolder() {
        
        // input dialog
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Folder");
        dialog.setHeaderText("Enter folder name:");
        dialog.setContentText("Folder name:");

        // Show the dialog and wait for the user to close it
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && !result.get().isEmpty()) {

            String folderName = result.get().trim();

            // create new tree item
            TreeItem<String> newItem = new TreeItem<>(folderName);
            
            // get selected item
            TreeItem<String> selected = treeView.getSelectionModel().getSelectedItem();

            if (selected != null) {
                // add new item to selected item
                selected.getChildren().add(newItem);
                selected.setExpanded(true);
            } else {
                // add new item to root
                treeView.getRoot().getChildren().add(newItem);
            }
        }
    }

    @FXML
    private void handleRemoveFolder() {
        // Get selected Item
        TreeItem<String> selected = treeView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }

        // prevent deleting the root folder
        if (selected == treeView.getRoot()) {
            System.out.println("Cannot delete root folder");
            return;
        }

        // get parent
        TreeItem<String> parent = selected.getParent();

        if(parent != null) {
            parent.getChildren().remove(selected);
        }
    }

    @FXML
    private void handleSaveTemplate() {

        try {
            String templateName = templateNameField.getText().trim();

            if(templateName.isEmpty()) {
                System.out.println("Template name is empty");
                return;
            }

            // convert tree to model
            FolderNode rootNode = convertTreeToModel(treeView.getRoot());

            Template template = new Template(templateName, rootNode);

            // create Gson
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // ensure templates folder exists
            Path folderPath = Paths.get("templates");
            Files.createDirectories(folderPath);

            // File path
            Path filePath = folderPath.resolve(templateName + ".json");
            
            // write to gson file
            try(Writer writer = Files.newBufferedWriter(filePath)) {
                gson.toJson(template, writer);
            }

            System.out.println("Templated saved: " + filePath);
            // close window
            Stage stage = (Stage) templateNameField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

     }

    private FolderNode convertTreeToModel(TreeItem<String> item) {
        // create node from current item(folder)
        FolderNode node = new FolderNode(item.getValue());

        // add children recursively
        for (TreeItem<String> child : item.getChildren()) {
            node.addChild(convertTreeToModel(child));
        }
        return node;
    } 
    
    public void loadTemplate(Template template, String fileName) {

        templateNameField.setText(template.getName());

        TreeItem<String> rootItem = convertModelToTree(template.getRoot());
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);

        this.currentFileName = fileName;
    }

    private TreeItem<String> convertModelToTree(FolderNode node) {

        TreeItem<String> item = new TreeItem<>(node.getName());

        for (FolderNode child : node.getChildren()) {
            item.getChildren().add(convertModelToTree(child));
        }

        return item;
    }
}