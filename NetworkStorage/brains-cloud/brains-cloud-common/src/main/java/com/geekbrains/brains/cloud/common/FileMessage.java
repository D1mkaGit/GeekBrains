package com.geekbrains.brains.cloud.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMessage extends AbstractMessage {
    private final String filename;
    private final byte[] data;

    public FileMessage( Path path ) throws IOException {
        filename = path.getFileName().toString();
        data = Files.readAllBytes(path);
    }

    public String getFilename() {
        return filename;
    }

    public byte[] getData() {
        return data;
    }
}
