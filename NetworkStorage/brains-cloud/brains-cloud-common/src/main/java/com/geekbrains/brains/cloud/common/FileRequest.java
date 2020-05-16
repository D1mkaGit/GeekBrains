package com.geekbrains.brains.cloud.common;

public class FileRequest extends AbstractMessage {
    private final String filename;

    public FileRequest( String filename ) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
