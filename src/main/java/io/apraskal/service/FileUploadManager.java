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
import io.apraskal.model.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.io.File;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument; 
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.apache.pdfbox.pdmodel.PDPage;

public class FileUploadManager {
    private volatile static MemoryStorage mem;
    private volatile static FileUploadManager instance;
    private volatile static LinkedBlockingQueue<Path> queue = new LinkedBlockingQueue<>();
    private volatile static ConcurrentHashMap<Integer, ConcurrentHashMap<String, List<Question>>> exam = new ConcurrentHashMap<>();

    private static Lock instanceCreationLock = new ReentrantLock();

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
        if (!queue.contains(path)) queue.add(path);
    }

    public static void parseInstance() {
        System.out.println("Inside of parse instance with queue value: " + queue.peek());
        try {
            if (queue.peek() == null) return;
            Path filePath = queue.poll();
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
            case "tsv":
                break;
            case "xlsx":
                break;
            case "json":
                break;
            case "xml":
                break;
            case "pdf":
                parsePdf(fileName);
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
            while((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                csv.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e);
        }

        try {
            mem = MemoryStorage.getInstance();
            mem.addStudentPage(csv);
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e);
        }
    }

    private static void parsePdf(Path fileName) {
        try {
            File pdfFile = fileName.toFile();
            PDDocument pdf = Loader.loadPDF(pdfFile);
            System.out.println(pdf.getDocument());
            List<PDPage> pdfPages = new ArrayList<>();
            List<InputStream> pdfData = new ArrayList<>();
            for (int i = 0; i < pdf.getNumberOfPages(); i++) {
                pdfPages.add(pdf.getPage(i));
                pdfData.add(pdf.getPage(i).getContents());
                getRawPdfData(pdfData.get(i));
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e);
        }
    }

    private static void getRawPdfData(InputStream data) {
        try {
            int byteData = data.read();
            List<Character> collectedData = new ArrayList<>();
            while (byteData != -1) {
                collectedData.add((char) byteData);
                byteData = data.read();
            }
            cleanPdfData(collectedData);
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e);
        }
    }

    private static void cleanPdfData(List<Character> rawData) {
        for (int i = 0; i < rawData.size(); i++) {
            System.out.println(rawData.get(i));
        }
    }

}