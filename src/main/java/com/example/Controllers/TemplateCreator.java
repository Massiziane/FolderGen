package com.example.Controllers;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TemplateCreator {

    @FXML
    private TextField templateNameField;

    @FXML
    private TreeView<String> treeView;

    @FXML
    public void initialize() {
        // Default root
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);
        treeView.setRoot(root);
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
        System.out.println("Save template");
    }
}