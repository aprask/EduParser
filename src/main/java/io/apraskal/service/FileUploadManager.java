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
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import io.apraskal.cache.*;

public class FileUploadManager {
    // TODO add a queue instead of kicking user out (not rn, later)
    private static FileUploadManager instance;
    private static Path path; // eventually make this a arr

    private static Lock instanceCreationLock = new ReentrantLock();
    private static Lock dataParsingLock = new ReentrantLock();

    private FileUploadManager(Path path) {
        this.path = path;
    }

    public static FileUploadManager getInstance(Path path) {
        try {
            if (instanceCreationLock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    instance = new FileUploadManager(path);
                } finally {
                    instanceCreationLock.unlock();
                }
            } else {
                Thread.sleep(2000);
                throw new RuntimeException("Could not acquire lock for file manager instance");
            }
        } catch (Exception e) {
            instanceCreationLock.unlock();
            throw new RuntimeException("Exeception occurred: " + e);
        }
        return instance;
    }

    public static void parseInstance() {
        try {
            if (dataParsingLock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    parseFile(extractExt(path.toString()));
                } finally {
                    dataParsingLock.unlock();
                }
            } else {
                Thread.sleep(2000);
                throw new RuntimeException("Could not acquire lock for file manager instance");
            }
        } catch (Exception e) {
            dataParsingLock.unlock();
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
            default:
                break;
        }
    }

    private static void parseCsv() {
        String stringPath = path.toString();
        List<List<String>> csv = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(stringPath))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                csv.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e);
        }

        try {
            MemoryStorage mem = MemoryStorage.getInstance(csv);
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e);
        }
    }

    private static void parseTsv() {
        String stringPath = path.toString();
        List<List<String>> tsv = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(stringPath))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] values = line.split("\t");
                tsv.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e);
        }

        try {
            MemoryStorage mem = MemoryStorage.getInstance(tsv);
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e);
        }
    }

    private static void parseXlsx() {
        
    }

    private static void parseJson() {
        
    }

    private static void parseXml() {
        
    }
}