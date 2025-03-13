package io.apraskal;

import io.apraskal.service.*;
import io.apraskal.model.*;
import io.apraskal.cache.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App
{
    public static void main(String[] args)
    {
        Path path1 = Paths.get("/mnt/c/Users/andre/OneDrive/Desktop/OOP_PROJECT/grade_analyzer/gradeanalyzer/src/main/java/io/apraskal/test.csv");
        Path path2 = Paths.get("/mnt/c/Users/andre/OneDrive/Desktop/OOP_PROJECT/grade_analyzer/gradeanalyzer/src/main/java/io/apraskal/test2.csv");
        Path pdfPath = Paths.get("/mnt/c/Users/andre/OneDrive/Desktop/OOP_PROJECT/grade_analyzer/gradeanalyzer/src/main/java/io/apraskal/generic_exam.pdf");
        FileUploadManager manager = FileUploadManager.getInstance();
        manager.addPathToQueue(pdfPath);
        // manager.addPathToQueue(path2);
        manager.parseInstance();
        // manager.parseInstance();
        // MemoryStorage mem = MemoryStorage.getInstance();
        // System.out.println(mem.getStudentPages());
        // ProcessDataManager proc = ProcessDataManager.getInstance();
        // proc.pageReplace(mem.getPages());
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
