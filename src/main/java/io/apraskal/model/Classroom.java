package io.apraskal.model;

import io.apraskal.model.*;
import java.util.*;

public class Classroom {
    private int jobId;
    private int classId;
    private List<Student> students;
    private Exam exam;

    public Classroom(ClassroomBuilder builder) {
        this.jobId = builder.jobId;
        this.classId = builder.classId;
        this.students = builder.students;
        this.exam = builder.exam;
    }

    @Override
    public String toString() {
        return "Class ID: " + this.classId;
    }

    public static class ClassroomBuilder {
        private int jobId;
        private int classId;
        private List<Student> students;
        private Exam exam;

        public ClassroomBuilder(int classId, List<Student> students, Exam exam) {
            this.classId = classId;
            this.students = students;
            this.exam = exam;
        }

        public Classroom buildClassroom() {
            return new Classroom(this);
        }
    }
}