package io.apraskal.service.data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.apraskal.model.Student;
import io.apraskal.model.StudentPage;

public class ProcessDataManager {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    private static final int COL_CAP = 9;
    private volatile static ProcessDataManager instance;

    private static final Lock instanceCreationLock = new ReentrantLock();
    
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
            } catch (InterruptedException e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    private static Student transformStudent(String[] student) {
        if (student.length != COL_CAP) throw new RuntimeException("Incorrect array length");
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
        } catch (NumberFormatException | ParseException e) {
            throw new RuntimeException("Exeception occurred: " + e);
        }
    }

    public static Student[] processStudentData(StudentPage studentPage) {
        int studentCap = studentPage.getData().size();
        String[][] fileArr = new String[studentCap][COL_CAP];
        Student[] students = new Student[studentCap-1];
        for (int i = 1; i < studentPage.getData().size(); i++) {
            String[] dataArr = studentPage.getData().get(i).toArray(new String[studentPage.getData().get(i).size()]);
            for (int j = 0; j < COL_CAP; j++) fileArr[i][j] = dataArr[j];
            Student transformedStudent = transformStudent(fileArr[i]);
            students[i-1] = transformedStudent;
        }
        return students;
    }
}