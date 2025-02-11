package io.apraskal.model;

import java.util.UUID;

public class StudentInfo {
    public enum GradeLevel {
        FRESHMAN,
        SOPHOMORE,
        JUNIORS,
        SENIOR
    }
    private String firstName;
    private String lastName;
    private int age;
    private GradeLevel level;
    private Exam exam;

    public StudentInfo() {}

    public StudentInfo(String firstName, String lastName, int age, GradeLevel level, Exam exam) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.level = level;
        this.exam = exam;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GradeLevel getLevel() {
        return level;
    }

    public void setLevel(GradeLevel level) {
        this.level = level;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
