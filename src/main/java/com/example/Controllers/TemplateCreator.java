package com.example.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
        System.out.println("Add folder");
    }

    @FXML
    private void handleRemoveFolder() {
        System.out.println("Remove folder");
    }

    @FXML
    private void handleSaveTemplate() {
        System.out.println("Save template");
    }
}