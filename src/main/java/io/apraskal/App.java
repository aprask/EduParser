package io.apraskal;

import io.apraskal.service.*;
import io.apraskal.model.*;
import io.apraskal.cache.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App
{
    public static void main(String[] args)
    {
        Path path1 = Paths.get("/mnt/c/Users/andre/OneDrive/Desktop/OOP_PROJECT/grade_analyzer/gradeanalyzer/src/main/java/io/apraskal/test.csv");
        Path path2 = Paths.get("/mnt/c/Users/andre/OneDrive/Desktop/OOP_PROJECT/grade_analyzer/gradeanalyzer/src/main/java/io/apraskal/test2.csv");
        FileUploadManager manager = FileUploadManager.getInstance();
        manager.addPathToQueue(path1);
        manager.addPathToQueue(path2);
        manager.parseInstance();
        manager.parseInstance();
        Question question1 = new FITB("SomethingCorrect", "Put something correct", 5);
        Question question2 = new FITB("SomethingAlsoCorrect", "Put something else correct", 15);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        for (int i = 0; i < questions.size(); i++) questions.get(i).displayQuestion();
        MemoryStorage mem = MemoryStorage.getInstance();
        mem.addExamPage(questions);
        // System.out.println(mem.getAllStudentPages());
        ProcessDataManager procMan = ProcessDataManager.getInstance();
        procMan.processStudentData(mem.getStudentPage(0));
        procMan.processExamData(mem.getExamPage(0));
    }
}
