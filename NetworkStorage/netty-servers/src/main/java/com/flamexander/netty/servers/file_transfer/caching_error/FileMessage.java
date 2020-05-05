package com.flamexander.netty.servers.file_transfer.caching_error;

import java.io.Serializable;

public class FileMessage implements Serializable {
    String filename;
    int partNumber;
    int partsCount;
    byte[] data;

    public FileMessage(String filename, int partNumber, int partsCount, byte[] data) {
        this.filename = filename;
        this.partNumber = partNumber;
        this.partsCount = partsCount;
        this.data = data;
    }
}
