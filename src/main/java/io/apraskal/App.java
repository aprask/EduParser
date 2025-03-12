package io.apraskal;

import io.apraskal.service.*;
import io.apraskal.model.*;
import io.apraskal.cache.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App
{
    public static void main(String[] args )
    {
        Path path = Paths.get("/mnt/c/Users/andre/OneDrive/Desktop/OOP_PROJECT/grade_analyzer/gradeanalyzer/src/main/java/io/apraskal/test.csv");
        // // System.out.println("File name: " + path.getFileName());
        // FileUploadManager manager = FileUploadManager.getInstance(path);
        // manager.parseInstance();
        // MemoryStorage mem = MemoryStorage.getInstance();
        // // System.out.println(mem.getPages());
        // ProcessDataManager proc = ProcessDataManager.getInstance(mem.getPages());
        // proc.transformData();

        // StudentInfo student = new StudentInfo(1,2,3,5.5,6);
        // StudentInfo[] students = new StudentInfo[1];
        // students[0] = student;

        // ExamInfo examInfo = new ExamInfo();
        // ExamInfo[] exams = new ExamInfo[1];
        // exams[0] = examInfo;

        // GradeScale gradeScale = new GradeScale();
        // GradeScale[] grades = new GradeScale[1];
        // grades[0] = gradeScale;

        // Classroom classRoom = new Classroom.ClassroomBuilder(1, students, exams, gradeScale).buildClassroom();
        // System.out.println(classRoom);

        
    }
}
