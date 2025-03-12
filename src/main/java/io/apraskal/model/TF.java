package io.apraskal.model;

import io.apraskal.model.Question;
import java.util.*;

public class TF extends Question {
    private boolean correctAnswer;

    public TF(boolean correctAnswer, String questionText, double weight) {
        super(questionText, weight);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void displayQuestion() {
        System.out.println("TF: " + questionText + " (Weight: " + weight + ")");
    }
}