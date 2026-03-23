package com.example.model;

public class Template {

    private String name;
    private FolderNode root;

    public Template(String name, FolderNode root) {
        this.name = name;
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public FolderNode getRoot() {
        return root;
    }
}