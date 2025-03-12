package io.apraskal.model;

import io.apraskal.model.Question;
import java.util.*;

public class MCQ extends Question {
    private List<String> options;
    private int correctAnswerIdx;

    public MCQ(List<String> options, int correctAnswerIdx, String questionText, double weight) {
        super(questionText, weight);
        this.options = options;
        this.correctAnswerIdx = correctAnswerIdx;
    }

    @Override
    public void displayQuestion() {
        System.out.println("MCQ: " + questionText + " (Weight: " + weight + ")");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}