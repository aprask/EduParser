package io.apraskal.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.lang.Exception;
import java.lang.StringBuilder;
import java.util.*;
import io.apraskal.cache.*;
import io.apraskal.model.*;
import java.io.File;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import io.apraskal.service.*;

public class FileUploadManager {
    private volatile static MemoryStorage mem = MemoryStorage.getInstance();
    private volatile static FileUploadManager instance;
    protected static Queue<Path> queue = new LinkedList<>();

    private static Lock instanceCreationLock = new ReentrantLock();
    private static Lock parsingCsvLock = new ReentrantLock();

    private FileUploadManager() {}

    public static FileUploadManager getInstance() {
        if (instance == null) {
            try {
                if (instanceCreationLock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        instance = new FileUploadManager();
                    } finally {
                        instanceCreationLock.unlock();
                    }
                } else {
                    Thread.sleep(2000);
                    throw new RuntimeException("Could not acquire lock for file manager instance");
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    public static void addPathToQueue(final Path path) {
        queue.add(path);
    }

    public static void parseInstance() {
        System.out.println("Inside of parse instance with queue value: " + queue.peek());
        try {
            if (queue.peek() == null) return;
            Path filePath = queue.poll();
            System.out.println("Observing this path: " + filePath);
            String extension = extractExt(filePath.toString());
            System.out.println("Extension: " + extension);
            parseFile(extension, filePath);
        } catch (Exception e) {
            throw new RuntimeException("Exeception occurred: " + e);
        }
    }

    private static String extractExt(String fileName) {
        String[] directories = fileName.split("/");
        fileName = directories[directories.length - 1];
        char[] fileCharArr = fileName.toCharArray();
        boolean parseFileExt = false;
        StringBuilder ext = new StringBuilder();
        for (int i = 0; i < fileCharArr.length; i++) {
            if (parseFileExt) ext.append(String.valueOf(fileCharArr[i]));
            if (String.valueOf(fileCharArr[i]).equals(".")) parseFileExt = true;
        }
        return ext.toString();
    }

    private static void parseFile(String fileExt, Path fileName) {
        switch (fileExt) {
            case "csv":
                parseCsv(fileName);
                break;
            default:
                break;
        }
    }

    private static void parseCsv(Path fileName) {
        String stringPath = fileName.toString();
        List<List<String>> csv = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(stringPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                csv.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception reading CSV: " + e);
        }

        try {
            System.out.println("Adding to page table");
            mem.addStudentPage(csv);
            System.out.println("ADDED PAGE");
        } catch (Exception e) {
            throw new RuntimeException("Exception adding page: " + e);
        }
    }

}