package io.apraskal.cli;

import io.apraskal.cli.CLIController;
import io.apraskal.model.Origin;
import io.apraskal.service.ApplicationManager;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.lang.Exception;
import java.nio.file.Paths;
import java.nio.file.Path;

public class CLIRunner {

    private volatile static CLIRunner instance;

    private static ApplicationManager appManager;

    private static CLIController controller;

    private static Lock instanceCreationLock = new ReentrantLock();

    private static String OUT_FILE = "result.pdf";

    public CLIRunner() {
        appManager = ApplicationManager.getInstance();
        controller = new CLIController();
    }

    public static CLIRunner getInstance() {
        if (instance == null) {
            try {
                if (instanceCreationLock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        instance = new CLIRunner();
                    } finally {
                        instanceCreationLock.unlock();
                    }
                } else {
                    Thread.sleep(2000);
                    throw new RuntimeException("Could not acquire lock for CLIRunner instance");
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    public void runCLI() {
        try {
            Origin origin = controller.runProgram();
            if (origin == null) {
                System.out.println("Invalid path origin");
                System.exit(1);
            }
            Path readPath = Paths.get("/", origin.getKArgs()).resolve(origin.getInitArg());
            Path writePath = Paths.get("/", origin.getKArgs()).resolve(OUT_FILE);
            appManager.run(new Path[] { readPath }, new Path[] { writePath });
            System.out.println("####################################################");
            System.out.println("####################### END ########################");
            System.out.println("####################################################");
        } catch (Exception e) {
            System.out.println("Encountered general exception: " + e.getMessage());
        }
    }
}