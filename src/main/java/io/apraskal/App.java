package io.apraskal;

import io.apraskal.service.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App
{
    public static void main(String[] args )
    {
        Path path = Paths.get("/mnt/c/Users/andre/OneDrive/Desktop/OOP_PROJECT/grade_analyzer/gradeanalyzer/src/main/java/io/apraskal/test.csv");
        // System.out.println("File name: " + path.getFileName());
        FileUploadManager manager = FileUploadManager.getInstance(path);
        manager.parseInstance();
    }
}
