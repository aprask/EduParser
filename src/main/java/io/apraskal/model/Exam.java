package io.apraskal.model;

import java.util.Date;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import io.apraskal.model.Question;
import io.apraskal.model.StudentInfo;

public class Exam {
    private final UUID examID = UUID.randomUUID();
    private String examName;
    private List<StudentInfo> students;
    private Date date;
    private List<Question> questions;
    // TODO find a way to set scale

    public Exam() {

    }

    public Exam(String examName, Date date) {
        this.examName = examName;
        this.students = new ArrayList<>();
        this.date = date;
        this.questions = new ArrayList<>();
    }

    public UUID getExamID() {
        return this.examID;
    }

    public String getExamName() {
        return this.examName;
    }

    public List<StudentInfo> getStudents() {
        return this.students;
    }

    public Date getDate() {
        return this.date;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setExamName(String name) {
        this.examName = name;
    }

    public void setStudents(List<StudentInfo> students) {
        this.students = students;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}