package com.flamexander.netty.servers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TempApp {
    public static void main(String[] args) throws Exception {
        List<Long> sizes = Files.list(Paths.get("."))
                .filter(p -> !Files.isDirectory(p))
                .map(Path::toFile)
                .map(File::length)
                .collect(Collectors.toList());
        System.out.println(sizes);

        File[] filesInCurrentDir = new File(".").listFiles();
        for (File o : filesInCurrentDir) {
            System.out.println(o.length());
        }
    }
}
