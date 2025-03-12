package io.apraskal.model;

import io.apraskal.model.Question;
import java.util.*;

public class FITB extends Question {
    private String correctAnswer;

    public FITB(String correctAnswer, String questionText, double weight) {
        super(questionText, weight);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void displayQuestion() {
        System.out.println("FITB: " + questionText + " (Weight: " + weight + ")");
    }
}