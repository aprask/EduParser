package io.apraskal.service.data;

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
import java.text.SimpleDateFormat;
import java.util.Date;


public class ProcessDataManager {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
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
        int studentCap = studentPage.getData().size();
        String[][] fileArr = new String[studentCap][COL_CAP];
        Student[] students = new Student[studentCap];
        for (int i = 1; i < studentPage.getData().size(); i++) {
            String[] dataArr = studentPage.getData().get(i).toArray(new String[studentPage.getData().get(i).size()]);
            for (int j = 0; j < COL_CAP; j++) fileArr[i][j] = dataArr[j];
            students[i] = transformStudent(fileArr[i]);
        }
    }

    private static Student transformStudent(String[] student) {
        if (student.length != 9) throw new RuntimeException("Incorrect array length");
        try {
            Date studentDate = dateFormat.parse(student[4]);
            Student studentObj = new Student(
                Integer.parseInt(student[0]),
                student[1],
                student[2],
                Double.parseDouble(student[3]),
                studentDate,
                Double.parseDouble(student[5]),
                Double.parseDouble(student[6]),
                Double.parseDouble(student[7]),
                student[8]
            );
            return studentObj;
        } catch (Exception e) {
            throw new RuntimeException("Exeception occurred: " + e);
        }
    }

    public static void processExamData(ExamPage examPage) {
        Question[] fileArr = new Question[examPage.getData().size()];
        for (int i = 0; i < examPage.getData().size(); i++) {
            fileArr[i] = examPage.getData().get(i);
        }
    }
}