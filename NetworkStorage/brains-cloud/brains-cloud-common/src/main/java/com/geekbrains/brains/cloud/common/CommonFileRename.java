package com.geekbrains.brains.cloud.common;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.nio.file.Paths;

public class CommonFileRename {
    public static void renameFileInLocation( String _storage_location, String _selectedFile, String newFileName ) {
        File oldFile = Paths.get(_storage_location, _selectedFile).toFile();
        if (newFileName != null) {
            File newName = new File(_storage_location + "/" + newFileName);
            if (oldFile.renameTo(newName)) {
                System.out.println("Файл успешно переименован");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Файл не переименован", ButtonType.OK);
                alert.showAndWait();
                System.out.println("Файл не удалось переименовать");
            }
        }
    }
}
