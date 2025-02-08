package io.apraskal.model;

import java.util.Hashmap;

public class Question {
    private HashMap<String, String[]> questionAndAnswers;
    private HashMap<String, Double> questionAndWeights;
    public Question() {
        this.questionAndAnswers = new HashMap<>();
        this.questionAndWeights = new HashMap<>();
    }
    public Question(
            HashMap<String, String[]> questionAndAnswers,
            HashMap<String, Double> questionAndWeights
        ) 
    {
        this.questionAndAnswers = questionAndAnswers != null ? questionAndAnswers : new HashMap<>();
        this.questionAndWeights = questionAndWeights != null ? questionAndWeights : new HashMap<>();
    }
    private HashMap<String, String[]> getQuestionAndAnswers() {
        return this.questionAndAnswers;
    }
    private HashMap<String, Double> getQuestionAndWeights() {
        return this.questionAndWeights;
    }
}