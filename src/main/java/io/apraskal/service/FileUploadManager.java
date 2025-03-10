package io.apraskal.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.lang.Exception;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import io.apraskal.cache.*;

public class FileUploadManager {
    // TODO add a queue instead of kicking user out (not rn, later)
    private static FileUploadManager instance;
    private static Path path; // eventually make this a arr
    private static String data; // eventually make this a arr

    private static Lock lock = new ReentrantLock();

    private FileUploadManager(Path path) {
        this.path = path;
    }

    public static FileUploadManager getInstance(Path path) {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    instance = new FileUploadManager(path);
                    parseFile(extractExt(path.toString()));
                } finally {
                    lock.unlock();
                }
            } else {
                Thread.sleep(2000);
                throw new RuntimeException("Could not acquire lock for file manager instance");
            }
        } catch (Exception e) {
            lock.unlock();
            throw new RuntimeException("Exeception occurred: " + e);
        }
        return instance;
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

    private static void parseFile(String fileExt) {
        switch (fileExt) {
            case "csv":
                parseCsv();
                break;
            case "tsv":
                parseTsv();
                break;
            case "xlsx":
                parseXlsx();
                break;
            case "json":
                parseJson();
                break;
            case "xml":
                parseXml();
                break;
        }
    }

    private static void parseCsv() {
        String fileData = path.toString();
        try {
            List<String> fileDataList = Files.readAllLines(path);
            data = String.join(",", fileDataList);
            MemoryStorage mem = MemoryStorage.getInstance(data);
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e);
        }
    }

    private static void parseTsv() {
        
    }

    private static void parseXlsx() {
        
    }

    private static void parseJson() {
        
    }

    private static void parseXml() {
        
    }
}