package io.apraskal.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import io.apraskal.model.StudentPage;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.lang.Exception;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import io.apraskal.cache.*;
import io.apraskal.model.*;
import io.apraskal.service.*;

public class ProcessDataManager {
    private static final int COL_CAP = 9;
    private volatile static ProcessDataManager instance;

    private static Lock instanceCreationLock = new ReentrantLock();
    
    private ProcessDataManager() {}

    public static ProcessDataManager getInstance() {
        if (instance == null) {
            try {
                if (instanceCreationLock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        instance = new ProcessDataManager();
                    } finally {
                        instanceCreationLock.unlock();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    public static void processStudentData(StudentPage studentPage) {
        String[][] fileArr = new String[studentPage.getData().size()][COL_CAP];
        for (int i = 1; i < studentPage.getData().size(); i++) {
            String[] dataArr = studentPage.getData().get(i).toArray(new String[studentPage.getData().get(i).size()]);
            for (int j = 0; j < COL_CAP; j++) fileArr[i][j] = dataArr[j];
        }
    }

    public static void processExamData(ExamPage examPage) {
        Question[] fileArr = new Question[examPage.getData().size()];
        for (int i = 0; i < examPage.getData().size(); i++) {
            fileArr[i] = examPage.getData().get(i);
        }
    }
}