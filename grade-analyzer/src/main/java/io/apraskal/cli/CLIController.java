package io.apraskal.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import io.apraskal.model.Origin;

public class CLIController implements CLICommands {
    private final Scanner SCANNER = new Scanner(System.in);
    private final String ALLOWED_EXT = ".csv";
    private final int COL_CAP = 9;

    public CLIController() {

    }

    @Override
    public Origin runProgram() {
        System.out.println("####################################################");
        System.out.println("####################################################");
        System.out.println("############### Welcome to EduParser ###############");
        System.out.println("####################################################");
        System.out.println("####################################################\n");
        System.out.println("Please verify your file columns:");
        System.out.println("student_id\tfirst_name\tlast_name\tgrade\texam_date\tstudy_hours\n");
        System.out.println("Please note that the exam date is in the format: yyyy-mm-dd\n");
        String os = System.getProperty("os.name").toLowerCase();
        String path = null;
        while (true) {
            System.out.println("Type -1 to exit program");
            System.out.println("OS DETECTED: " + os);
            if (os.contains("win")) {
                System.out.println("\nExample File Path based on your OS: C:\\Users\\You\\Documents\\file.csv\n");
            } else {
                System.out.println("\nExample File Path: /home/you/Documents/file.csv\n");
            }
            System.out.print("Enter the absolute path to a *.csv file: ");
            path = SCANNER.nextLine().trim();
            if (path.equals("-1")) System.exit(0);
            if (!validateFilePath(path)) {
                System.out.println("Invalid file. Please try again.\n");
                continue;
            }
            break; // valid path
        }
        return processPath(path);
    }

    @Override
    public boolean validateFilePath(String pathStr) {
        try {
            Path path = Paths.get(pathStr);
            if (!Files.exists(path)) {
                System.out.println("File does not exist.");
                return false;
            }

            if (!path.toString().toLowerCase().endsWith(ALLOWED_EXT)) {
                System.out.println("Invalid file type. Only *.csv files are allowed.");
                return false;
            }

            if (!Files.isReadable(path)) {
                System.out.println("File is not readable. Check permissions.");
                return false;
            }

            String headerLine = Files.lines(path).findFirst().orElse("");
            String[] columns = headerLine.split(",");

            if (columns.length != COL_CAP) {
                System.out.println("Invalid file structure. Expected 9 columns, found: " + columns.length);
                System.out.println("Header: " + headerLine);
                return false;
            }

            return true;
        } catch (InvalidPathException e) {
            System.out.println("Invalid path syntax: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Failed to read the file: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Encountered general exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Origin processPath(String pathStr) {
        try {
            Path fullPath = Paths.get(pathStr).normalize().toAbsolutePath();
            String os = System.getProperty("os.name").toLowerCase();
            Origin origin = new Origin.OriginBuilder()
                .setOs(os)
                .setFullPath(fullPath)
                .build();
            System.out.println("Host OS: " + origin.getOs());
            System.out.println("Initial Arg (file): " + origin.getInitArg());
            System.out.println("Path: " + origin.getFullPath());
            return origin;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return null;
        }
    }
}