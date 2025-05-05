package io.apraskal.service;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.apraskal.cache.MemoryStorage;
import io.apraskal.service.data.ProcessDataManager;

@SuppressWarnings("static-access")
public class ApplicationManager {
    private volatile static ApplicationManager instance;
    private volatile static FileUploadManager fileUploadManager = FileUploadManager.getInstance();
    private volatile static MemoryStorage mem = MemoryStorage.getInstance();
    private volatile static ProcessDataManager procDataManager = ProcessDataManager.getInstance();
    private volatile static ReportGenerator rep = ReportGenerator.getInstance();
    private volatile static StatsCalc calc = StatsCalc.getInstance();

    private static final Lock instanceCreationLock = new ReentrantLock();

    public ApplicationManager() {}

    public static ApplicationManager getInstance() {
        if (instance == null) {
            try {
                if (instanceCreationLock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        instance = new ApplicationManager();
                    } finally {
                        instanceCreationLock.unlock();
                    }
                } else {
                    Thread.sleep(2000);
                    throw new RuntimeException("Could not acquire lock for report gen instance");
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }


    public void run(Path[] readPath, Path[] writePath) {
        System.out.println("READ PATHS:");
        for (Path path : readPath) {
            System.out.println(" - " + path.toAbsolutePath().normalize());
        }
        for (Path path : readPath) {
            fileUploadManager.addPathToQueue(path);
        }
        System.out.println("QUEUE CONTENTS:");
        for (Path p : fileUploadManager.queue) {
            System.out.println(" - " + p);
        }
        while (!fileUploadManager.queue.isEmpty()) {
            System.out.println("PARSING");
            fileUploadManager.parseInstance();
        }
        System.out.println("SIZE OF MEM " + mem.getAllStudentPages().size());
        for (int i = 0; i < writePath.length; i++) {
            rep.createPDFReport(calc.calculateStatistics(procDataManager.processStudentData(mem.getStudentPage(i))), writePath[i]);
        }
    }

}