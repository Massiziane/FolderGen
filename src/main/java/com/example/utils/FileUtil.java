package com.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.model.FolderNode;

public class FileUtil {
    private void createFolders(FolderNode node, Path parentPath) throws IOException {
        
        // create current folder
        Path currentPath = parentPath.resolve(node.getName());
        Files.createDirectories(currentPath);

        // Recursively create subfolders
        for (FolderNode child : node.getChildren()) {
            createFolders(child, currentPath);
        }
    }
}
