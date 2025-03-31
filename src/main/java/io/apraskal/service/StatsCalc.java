package io.apraskal.service;

import java.lang.Exception;
import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;
import io.apraskal.service.data.ProcessDataManager;
import io.apraskal.model.*;
import java.util.*;

public class StatsCalc {
    private volatile static StatsCalc instance;

    private static Lock instanceCreationLock = new ReentrantLock();

    private StatsCalc() {}

    public static StatsCalc getInstance() {
        if (instance == null) {
            try {
                if (instanceCreationLock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        instance = new StatsCalc();
                    } finally {
                        instanceCreationLock.unlock();
                    }
                } else {
                    Thread.sleep(2000);
                    throw new RuntimeException("Could not acquire lock for stats calc instance");
                }
            } catch (Exception e) {
                throw new RuntimeException("Exeception occurred: " + e);
            }
        }
        return instance;
    }

    public static StatisticsResult calculateStatistics(Student[] students) {
        StatisticsResult statRes = new StatisticsResult();
        statRes.setMean(calculateMean(students));
        statRes.setMedian(calculateMedian(students));
        statRes.setMode(calculateMode(students));
        statRes.setSd(calculateSD(students, statRes.getMean()));
        statRes.setVariance(calculateVariance(statRes.getSd()));
        statRes.setRange(calculateRange(students));
        statRes.setIqr(calculateIQR(students));
        return statRes;
    }

    private static double calculateMean(Student[] students) {
        int sum = 0;
        for (int i = 0; i < students.length; i++) sum += students[i].getGrade();
        return sum/students.length;
    }

    private static Student[] sortStudents(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < students.length; j++) {
                if (students[minIdx].getGrade() > students[j].getGrade()) minIdx = j;
            }
            Student temp = students[minIdx];
            students[minIdx] = students[i];
            students[i] = temp;
            System.out.println(students[i]);
        }
        return students;
    }

    private static double calculateMedian(Student[] students) {
        sortStudents(students);
        if (students.length % 2 == 0) return students[students.length/2].getGrade();
        return students[(students.length+1)/2].getGrade();
    }

    private static double calculateMode(Student[] students) {
        int k = 0;
        HashMap<Double, Integer> modeMap = new HashMap<Double, Integer>();
        for (int i = 0; i < students.length; i++) modeMap.put(new Double(students[i].getGrade()), k++);
        Double currentKey = students[0].getGrade();
        for (Double key : modeMap.keySet()) {
            if (modeMap.get(currentKey) < modeMap.get(key)) currentKey = key;
        }
        return currentKey;
    }

    private static double calculateSD(Student[] students, double mean) {
        double sum = 0;
        for (int i = 0; i < students.length; i++) {
            sum += Math.pow(((students[i].getGrade())-mean), 2);
        }
        return Math.sqrt(sum/students.length);
    }

    private static double calculateRange(Student[] students) {
        sortStudents(students);
        return students[students.length-1].getGrade() - students[0].getGrade();
    }

    private static double calculateVariance(double standDev) {
        return Math.pow(standDev, 2);
    }

    private static double calculateIQR(Student[] students) {
        sortStudents(students);
        int medIdx = 0;
        if (students.length % 2 == 0) medIdx = students.length/2;
        else medIdx = (students.length+1)/2;
        Student[] lowerQ = Arrays.copyOfRange(students, 0, medIdx-1);
        Student[] upperQ = Arrays.copyOfRange(students, medIdx+1, students.length-1);
        double q1 = calculateMedian(lowerQ);
        double q3 = calculateMedian(upperQ);
        return q3-q1;
    }

}