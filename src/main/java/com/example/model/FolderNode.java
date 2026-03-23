package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class FolderNode {

    private String name;
    private List<FolderNode> children;

    public FolderNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<FolderNode> getChildren() {
        return children;
    }

    public void addChild(FolderNode child) {
        children.add(child);
    }
}