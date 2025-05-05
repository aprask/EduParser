package io.apraskal.model;

import java.util.Date;

public class Student implements Comparable<Student> {
    private int studentId;
    private String fName;
    private String lName;
    private double grade;
    private Date examDate;
    private double studyHours;
    private double attendancePercentage;
    private double previousScore;
    private String subject;

    public Student() {}

    public Student(int studentId, String fName, String lName, double grade, Date examDate, double studyHours, double attendancePercentage, double previousScore, String subject) {
        this.studentId = studentId;
        this.fName = fName;
        this.lName = lName;
        this.grade = grade;
        this.examDate = examDate;
        this.studyHours = studyHours;
        this.attendancePercentage = attendancePercentage;
        this.previousScore = previousScore;
        this.subject = subject;
    }

    public double getPreviousScore() {
        return previousScore;
    }

    public void setPreviousScore(double previousScore) {
        this.previousScore = previousScore;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public double getStudyHours() {
        return studyHours;
    }

    public void setStudyHours(double studyHours) {
        this.studyHours = studyHours;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "{" +
                "studentId=" + studentId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", grade=" + grade +
                ", examDate=" + examDate +
                ", studyHours=" + studyHours +
                ", attendancePercentage=" + attendancePercentage +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
        public int compareTo(Student other) {
            return Double.compare(this.grade, other.grade);
        }

}
