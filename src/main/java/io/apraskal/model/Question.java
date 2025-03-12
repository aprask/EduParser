package io.apraskal.model;

public abstract class Question {
    protected String questionText;
    protected double weight;

    public Question(String questionText, double weight) {
        this.questionText = questionText;
        this.weight = weight;
    }

    public void displayQuestion() {
        
    }
}